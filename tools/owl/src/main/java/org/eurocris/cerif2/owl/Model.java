package org.eurocris.cerif2.owl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.CaseUtils;
import org.jetbrains.annotations.NotNull;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.XSDVocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplString;

public class Model {

	protected final Logger log = LoggerFactory.getLogger( getClass().getName() );

	private final List<Section> datatypes = new ArrayList<>();

	private final Map<String, Future<? extends OWLEntity>> datatypeByName = new HashMap<>();

	private final List<Section> entities = new ArrayList<>();
	
	private final Map<String, Future<? extends OWLEntity>> entityByName = new HashMap<>();
	
	private final Map<String, Map<String, Relationship>> relationshipByEntityAndRelationshipName = new HashMap<>();

	public static final IRI IRI_BASE = IRI.create( "https://w3id.org/cerif2/" );

	public static final String DEFAULT_LANGUAGE_CODE = "en";

	private final IRI baseIRI;

	private final OWLOntology ont;

	private final OWLOntologyManager man = OWLManager.createOWLOntologyManager();

	private final OWLDataFactory dataFactory = man.getOWLDataFactory();

	private final OWLDatatype dateDatatype;
	
	private final ExecutorService es = Executors.newCachedThreadPool();
	
	public Model( final String moduleName ) throws OWLOntologyCreationException, IOException, ParseException {
		super();
		this.baseIRI = IRI.create( IRI_BASE.toString(), moduleName + "/" );
		log.info( "Starting model for module '" + moduleName + "', IRI " + baseIRI );
		this.ont = man.createOntology( baseIRI );

		final IRI dateDatatypeIRI = baseIRI.resolve( "datatypes" ).resolve( "Date" );
		this.dateDatatype = dataFactory.getOWLDatatype( dateDatatypeIRI );

		datatypeByName.put( "String", CompletableFuture.supplyAsync( () -> dataFactory.getStringOWLDatatype(), es ) );
		datatypeByName.put( "Multilingual_String", CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( OWL2Datatype.RDF_PLAIN_LITERAL.getIRI() ), es ) );
		datatypeByName.put( "Decimal", CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( OWL2Datatype.XSD_DECIMAL.getIRI() ), es ) );
		datatypeByName.put( "Boolean", CompletableFuture.supplyAsync( () -> dataFactory.getBooleanOWLDatatype(), es ) );
		datatypeByName.put( "URI", CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( OWL2Datatype.XSD_ANY_URI.getIRI() ), es ) );
		datatypeByName.put( "Date", CompletableFuture.supplyAsync( () -> dateDatatype, es ) );
		if ( moduleName.equals( "CERIF-Core" ) ) {
			final OWLDataUnionOf owlDataUnionOf = dataFactory.getOWLDataUnionOf( dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.DATE.getIRI() ) ),
					dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.G_YEAR_MONTH.getIRI() ) ),
					dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.G_YEAR.getIRI() ) ) );
			ont.add( dataFactory.getOWLDatatypeDefinitionAxiom( dateDatatype, owlDataUnionOf ) );
		}
	}

	public void readInDatatypeFile( final StructuredFile file ) throws ParseException {
		final Section mainSection = file.getMainSection();
		datatypes.add( mainSection );

		final Section componentsSection = mainSection.getSubsectionByTitle( "Components" );
		if ( componentsSection != null ) {
			// corresponds to an XSD complexType -> owl:Class
			final String owlClassName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
			final IRI classIRI = baseIRI.resolve( "datatypes" ).resolve( owlClassName );
			datatypeByName.put( owlClassName, createClass( mainSection, owlClassName, classIRI, "Components", false ) );
		} else {
			// corresponds to an XSD simpleType -> owl:Datatype
			final String datatypeName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
			datatypeByName.put( datatypeName, CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( baseIRI.resolve( "datatypes" ).resolve( datatypeName ) ) ) );
		}
	}

	public void readInEntityFile( final StructuredFile file ) throws ParseException {
		final Section mainSection = file.getMainSection();
		entities.add( mainSection );

		final String owlClassName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
		final IRI classIRI = baseIRI.resolve( owlClassName );
		entityByName.put( owlClassName, createClass( mainSection, owlClassName, classIRI, "Attributes", true ) );
	}
	
	public void markDoneReading() {
		// TODO
	}

	private static <T> T nvl( T a, T b ) {
		return ( a != null ) ? a : b;
	}
	
	/**
	 * Represents data of a relationship.
	 */
	public abstract class Relationship {

		private final String relName;
		
		private final IRI iri;

		private final IRI domainClassIRI;
		
		private final String rangeClassName;
		
		private final String inversePropropertyName;
		
		private final boolean ordered;
		
		private final String definitionLine;
		
		protected OWLObjectProperty owlObjectProperty = null;
		
		public Relationship( final String relName, final IRI domainClassIRI, final String rangeClassName, final String inversePropropertyName, final boolean ordered, final String definitionLine ) {
			super();
			this.relName = relName;
			this.iri = domainClassIRI.resolve( "#" + relName );
			this.domainClassIRI = domainClassIRI;
			this.rangeClassName = rangeClassName;
			this.inversePropropertyName = inversePropropertyName;
			this.ordered = ordered;
			this.definitionLine = definitionLine;
		}

		protected abstract void handle() throws InterruptedException, ExecutionException;

		public String getRelName() {
			return relName;
		}

		public IRI getIri() {
			return iri;
		}

		public IRI getDomainClassIRI() {
			return domainClassIRI;
		}

		public String getRangeClassName() {
			return rangeClassName;
		}

		public String getInversePropropertyName() {
			return inversePropropertyName;
		}

		public boolean isOrdered() {
			return ordered;
		}
		
		public String getDefinitionLine() {
			return definitionLine;
		}
		
		public OWLObjectProperty getOwlObjectProperty() {
			return owlObjectProperty;
		}
		
		public String toString() {
			return domainClassIRI + " --( " + relName + " )--> " + rangeClassName;
		}
		
		public void index() {
			final String classNameFragment = domainClassIRI.getFragment();
			final Map<String, Relationship> secondStageMap1 = new HashMap<>();
			final Map<String, Relationship> secondStageMap = nvl( relationshipByEntityAndRelationshipName.putIfAbsent( classNameFragment, secondStageMap1 ), secondStageMap1 );
			secondStageMap.put( relName, this );
		}
		
		public void deindex() {
			relationshipByEntityAndRelationshipName.get( domainClassIRI.getFragment() ).remove( relName, this );
		}

	}

	static interface ThrowingSupplier<T, E extends Exception> {
		T get() throws E;
	}
	
	<T, E extends Exception> Supplier<T> handlingSupplierWrapper(
			  ThrowingSupplier<T, E> throwingSupplier, Class<E> exceptionClass) {
			    return () -> {
			        try {
			            return throwingSupplier.get();
			        } catch ( final Exception ex ) {
			            try {
			                final E exCast = exceptionClass.cast( ex );
			                log.warn( "Exception occured", exCast );
			                return null;
			            } catch ( final ClassCastException ccEx ) {
			                throw new RuntimeException( ex );
			            }
			        }
			    };
			}
	
	protected Future<OWLClass> createClass( final Section mainSection, final String owlClassName, final IRI classIRI, final String attributesSectionTitle, final boolean definitionObligatory )
			throws ParseException {
		log.info( "Declaring class '" + owlClassName + "', IRI " + classIRI );
		final OWLClass owlClass = dataFactory.getOWLClass( classIRI );
		ont.add( dataFactory.getOWLDeclarationAxiom( owlClass ) );

		return new FutureTask<OWLClass>( new Callable<OWLClass>() {

			@Override
			public OWLClass call() throws Exception {
				final String classTitle = mainSection.getTitle().trim();
				final OWLLiteral classTitleLiteral = dataFactory.getOWLLiteral( classTitle + "@" + DEFAULT_LANGUAGE_CODE, OWL2Datatype.RDF_PLAIN_LITERAL );
				ont.add( dataFactory.getOWLAnnotationAssertionAxiom( owlClass.getIRI(), dataFactory.getRDFSLabel( classTitleLiteral ) ) );

				final Section definitionSection = mainSection.getSubsectionByTitle( "Definition" );
				if ( definitionSection != null ) {
					final StringBuilder sb = new StringBuilder();
					for ( final Node x : definitionSection.getContents() ) {
						sb.append( x.getChars().toString() );
						sb.append( "\n" );
					}
					final OWLLiteral definitionLiteral = dataFactory.getOWLLiteral( sb.toString().trim() + "@" + DEFAULT_LANGUAGE_CODE, OWL2Datatype.RDF_PLAIN_LITERAL );
					ont.add( dataFactory.getOWLAnnotationAssertionAxiom( owlClass.getIRI(), dataFactory.getRDFSComment( definitionLiteral ) ) );
				} else if ( definitionObligatory ) {
					throw new IllegalStateException( "Class " + owlClassName + ": definition not found (no subsection titled \"Definition\")" );
				}

				final Section specializationOfSection = mainSection.getSubsectionByTitle( "Specialization of" );
				if ( specializationOfSection != null ) {
					Link x = (Link) specializationOfSection.getContents().get( 0 ).getChildOfType( Link.class );
					while ( x != null ) {
						final String linkUrl = x.getUrl().toString();
						final String linkUrlBase = linkUrl.replaceAll( ".*/", "" ).replaceFirst( "\\.md$", "" );
						final IRI superclassIRI = baseIRI.resolve( linkUrlBase );
						final OWLClass owlSuperclass = dataFactory.getOWLClass( superclassIRI );
						ont.add( dataFactory.getOWLSubClassOfAxiom( owlClass, owlSuperclass ) );
						x = (Link) x.getNextAny( Link.class );
					}
				}

				final Section attributesSection = mainSection.getSubsectionByTitle( attributesSectionTitle );
				if ( attributesSection != null ) {
					final List<Node> children = attributesSection.getContents();
					int i = 0;
					for ( final Node node : children ) {
						++i;
						if ( node instanceof BulletList ) {
							final @NotNull ReversiblePeekingIterable<Node> children2 = node.getChildren();
							int j = 0;
							for ( final Node node2 : children2 ) {
								++j;
								final BasedSequence text2x = node2.getChars().trim();
								final BasedSequence text2 = ( text2x.startsWith( "- " ) || text2x.startsWith( "* " ) ) ? text2x.subSequence( 2 ) : text2x;
								final String text2lc = text2x.toString().toLowerCase();
								log.info( "Node " + node2 + ": " + text2 );
								if ( j == 1 && ( text2lc.startsWith( "beside" ) || text2lc.startsWith( "those " ) || text2lc.startsWith( "none besides those of " ) || text2lc.startsWith( "just those of " ) ) ) {
									// ignore
								} else if ( text2.startsWith( "FIXME" ) ) {
										// ignore
								} else {
									try {
										processAttributeNode( classIRI, owlClass, node2, text2 );
									} catch ( final Exception e ) {
										log.warn( "When processing attribute", e );
									}
								}
							}
						} else {
							@NotNull
							BasedSequence text = node.getChars().trim();
							log.info( "Node " + node + ": " + text );
							if ( i == 1 && ( text.startsWith( "Beside" ) || text.startsWith( "Those " ) || text.startsWith( "None besides those of " ) || text.startsWith( "Just those of " ) ) ) {
								// ignore
							} else if ( text.startsWith( "FIXME" ) ) {
								// ignore
							} else {
								try {
									processAttributeNode( classIRI, owlClass, node, text );
								} catch ( final Exception e ) {
									log.warn( "When processing attribute", e );
								}
							}
						}
					}
				}
				
				final Section relationshipsSection = mainSection.getSubsectionByTitle( "Relationships" );
				if ( relationshipsSection != null ) {
					final List<Node> children = relationshipsSection.getContents();
					int i = 0;
					for ( final Node node : children ) {
						++i;
						BasedSequence text = node.getChars().trim();
						log.info( "Node " + node + ": " + text );
						if ( i == 1 && ( text.startsWith( "Beside" ) || text.startsWith( "Those " ) || text.startsWith( "None besides those of " ) || text.startsWith( "Just those of " ) ) ) {
							// ignore
						} else if ( text.startsWith( "FIXME" ) ) {
							// ignore
						} else if ( text.isNotBlank() ) {
							try {
								processRelationshipNode( classIRI, owlClass, node, text );
							} catch ( final Exception e ) {
								log.warn( "When processing relationship", e );
							}
						}
					}					
				}
				
				return owlClass;
			}
			
		} )
		{ 
			// implement on-demand evaluation within the current thread
			public OWLClass get() throws InterruptedException, ExecutionException {
				synchronized ( this ) {
					if (! isDone() ) {
						es.submit( this ).get();
					}
					return super.get();
				}
			}
		};
	}

	public void save() throws OWLOntologyStorageException {
		log.info( "About to write the ontology" );
		synchronized ( this ) {
			for ( final Map.Entry<String, Future<? extends OWLEntity>> x : datatypeByName.entrySet() ) {
				try {
					log.debug( "Getting datatype " + x.getKey() );
					x.getValue().get();
				} catch ( InterruptedException e ) {
					throw new RuntimeException( e );
				} catch ( ExecutionException e ) {
					log.warn( "When getting datatype " + x.getKey(), e );
				}
			}
		}
		synchronized ( this ) {
			for ( final Map.Entry<String, Future<? extends OWLEntity>> x : entityByName.entrySet() ) {
				try {
					log.debug( "Getting entity " + x.getKey() );
					x.getValue().get();
				} catch ( InterruptedException e ) {
					throw new RuntimeException( e );
				} catch ( ExecutionException e ) {
					log.warn( "When getting entity " + x.getKey(), e );
				}
			}			
		}
		synchronized ( this ) {
			for ( final Map<String, Relationship> x : relationshipByEntityAndRelationshipName.values() ) {
				for ( final Relationship y : x.values() ) {
					try {
						y.handle();
					} catch ( final Exception e ) {
						log.warn( "When handling relationship " + y.getIri(), e );
					}
				}
			}
		}
		final OWLDocumentFormat format = new TurtleDocumentFormat();
		ont.saveOntology( format, System.out );
	}

	static final Pattern getEntityNamePattern = Pattern.compile( "\\(\\.\\./entities/([^.]*)\\.md(#[^)]*)?\\)" );

	protected void processRelationshipNode( final IRI classIRI, final OWLClass owlClass, final Node node, BasedSequence text ) throws ParseException {
		if (!( text.startsWith( "<a name=\"" ) && text.endsWith( "</a>" ) )) {
			throw new ParseException( "Not having an enveloping <a name=\"...\"> ... </a> around relationship description", node );
		}
		final String relName1 = text.toString().replaceFirst( "<a name=\"([^\"]*)\">.*", "$1" );
		final String relName = CaseUtils.toCamelCase( relName1.replaceFirst( "^rel__", "" ), false, ' ', '-', '_', '.' );
		if ( relName.equals( relName1 ) ) {
			throw new ParseException( "Relationship name '" + relName1 + "' not starting with 'rel__'", node );
		}
		text = text.subSequence( text.indexOf( ">" ) + 1, text.length() - "</a>".length() );
		log.info( "Class " + classIRI + ", relationship " + relName );
		final String txt = text.toString().trim();
		
		final Matcher entityNameMatcher = getEntityNamePattern.matcher( txt );
		if ( ! entityNameMatcher.find() ) {
			throw new ParseException( "No reference to the relationship inverse", node );
		}
		final String inverseEntityName1 = entityNameMatcher.group(1);
		final String inverseFragment1 = entityNameMatcher.group(2);
		if ( ! entityNameMatcher.find() ) {
			throw new ParseException( "No reference to the range class", node );
		}
		final String rangeClassName = entityNameMatcher.group(1);
		if ( ! inverseEntityName1.startsWith( rangeClassName ) ) {
			throw new ParseException( "Inverse property's entity '" + inverseEntityName1 + "' not pointing to the range class '" + rangeClassName + "'", node );
		}
		final String fragmentPrefix = "#user-content-rel__";
		if ( ! inverseFragment1.startsWith( fragmentPrefix ) ) {
			throw new ParseException( "Inverse property fragment '" + inverseFragment1 + "' not starting with '" + fragmentPrefix + "'", node );
		}
		final String inversePropertyName = CaseUtils.toCamelCase( inverseFragment1.substring( fragmentPrefix.length() ), false, ' ', '-', '_', '.' );
		final boolean ordered = false;
		final Relationship relationship = new Relationship( relName, classIRI, rangeClassName, inversePropertyName, ordered, txt ) {
			protected void handle() throws InterruptedException, ExecutionException {
				final Future<? extends OWLEntity> rangeClassFuture = entityByName.get( getRangeClassName() );
				final OWLClass rangeClass = (OWLClass) rangeClassFuture.get();

				this.owlObjectProperty = dataFactory.getOWLObjectProperty( getIri() );
				ont.add( dataFactory.getOWLDeclarationAxiom( owlObjectProperty ) );
				final OWLLiteral definitionLiteral = dataFactory.getOWLLiteral( getDefinitionLine() + "@" + DEFAULT_LANGUAGE_CODE, OWL2Datatype.RDF_PLAIN_LITERAL );
				ont.add( dataFactory.getOWLAnnotationAssertionAxiom( owlObjectProperty.getIRI(), dataFactory.getRDFSComment( definitionLiteral ) ) );
				ont.add( dataFactory.getOWLObjectPropertyDomainAxiom( owlObjectProperty, owlClass ) );
				ont.add( dataFactory.getOWLObjectPropertyRangeAxiom( owlObjectProperty, rangeClass ) );
				
				final Map<String, Relationship> inverseRangeClassRelationshipsMap = relationshipByEntityAndRelationshipName.get( getRangeClassName() );
				if ( inverseRangeClassRelationshipsMap != null ) {
					final Relationship inverseRelationship = inverseRangeClassRelationshipsMap.get( inversePropertyName );
					if ( inverseRelationship != null ) {
						
						final OWLObjectProperty inverseProperty = inverseRelationship.getOwlObjectProperty();
						if ( inverseProperty != null ) {
							ont.add( dataFactory.getOWLInverseObjectPropertiesAxiom( owlObjectProperty, inverseProperty ) );
							ont.add( dataFactory.getOWLInverseObjectPropertiesAxiom( inverseProperty, owlObjectProperty ) );
						}
					}
				} else {
					throw new IllegalArgumentException( "No inverse relationships for entity " + getRangeClassName() + "; known are just " + relationshipByEntityAndRelationshipName.keySet() );
				}
			}
		};
		log.info( "New relationship " + relationship );
		relationship.index();
	}
	
	protected void processAttributeNode( final IRI classIRI, final OWLClass owlClass, final Node node, BasedSequence text ) throws ParseException {
		if ( text.startsWith( "<a name=\"" ) && text.endsWith( "</a>" ) ) {
			text = text.subSequence( text.indexOf( ">" ) + 1, text.length() - "</a>".length() );
		}
		final int colonPosition = text.indexOf( ':' );
		if ( colonPosition < 0 ) {
			throw new ParseException( "No colon specified", node );
		}
		final String attributeText = text.subSequence( 0, colonPosition ).trim().toString();
		final BasedSequence rest = text.subSequence( colonPosition + 1 ).trim();
		final int endashPosition = rest.indexOf( '–' );
		final BasedSequence datatypeSpec = ( endashPosition > 0 ) ? rest.subSequence( 0, endashPosition ).trim() : rest.trim();
		final String datatypeLinkTarget = datatypeSpec.toString().replaceAll( "[^(]*\\(([^)]*)\\)[^)]*", "$1" );
		final String attributeName = ( attributeText.contains( " " ) || Character.isUpperCase( attributeText.charAt( 0 ) ) ) ? CaseUtils.toCamelCase( attributeText, false, ' ', '-', '_', '.' ) : attributeText;
		log.info( "Attribute " + attributeName + ", datatype " + datatypeLinkTarget );
		if ( !datatypeLinkTarget.startsWith( "../datatypes/" ) ) {
			throw new ParseException( "Not referencing ../datatypes/", node );
		}
		if ( datatypeLinkTarget.contains( " " ) ) {
			throw new ParseException( "Missing endash", node );
		}
		final IRI attributeIRI = classIRI.resolve( "#" + attributeName );
		final String datatypeName = datatypeLinkTarget.replaceFirst( "\\.\\./datatypes/(.*)\\.md", "$1" );
		@SuppressWarnings( "unchecked" )
		final Future<OWLEntity> owlDatatypeFuture = (Future<OWLEntity>) datatypeByName.get( datatypeName );
		if ( owlDatatypeFuture == null ) {
			throw new IllegalStateException( "Unknown datatype " + datatypeName );
		} else {
			try {
				final OWLEntity owlDatatype = owlDatatypeFuture.get();
				if ( owlDatatype instanceof OWLClass ) {
					final OWLClass owlClass2 = (OWLClass) owlDatatype;
					final OWLObjectProperty owlObjectProperty = dataFactory.getOWLObjectProperty( attributeIRI );
					ont.add( dataFactory.getOWLDeclarationAxiom( owlObjectProperty ) );
					ont.add( dataFactory.getOWLObjectPropertyDomainAxiom( owlObjectProperty, owlClass ) );
					ont.add( dataFactory.getOWLObjectPropertyRangeAxiom( owlObjectProperty, owlClass2 ) );
				} else if ( owlDatatype instanceof OWLDatatype ) {
					final OWLDatatype datatype = (OWLDatatype) owlDatatype;
					final OWLDataProperty owlDataProperty = dataFactory.getOWLDataProperty( attributeIRI );
					ont.add( dataFactory.getOWLDeclarationAxiom( owlDataProperty ) );
					ont.add( dataFactory.getOWLDataPropertyDomainAxiom( owlDataProperty, owlClass ) );
					ont.add( dataFactory.getOWLDataPropertyRangeAxiom( owlDataProperty, datatype ) );
				} else {
					throw new IllegalStateException( "Unknown construct " + owlDatatype );
				}
			} catch ( final ExecutionException e ) {
				throw new RuntimeException( e.getCause() );
			} catch ( final InterruptedException e ) {
				Thread.currentThread().interrupt();
			}
		}

		if ( endashPosition > 0 ) {
			final OWLAnnotationValue value = new OWLLiteralImplString( rest.subSequence( endashPosition + 1 ).toString().trim() );
			ont.add( dataFactory.getOWLAnnotationAssertionAxiom( dataFactory.getRDFSLabel(), attributeIRI, value ) );
		}
	}

}
