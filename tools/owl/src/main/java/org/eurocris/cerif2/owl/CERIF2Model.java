package org.eurocris.cerif2.owl;

import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import net.sf.saxon.BasicTransformerFactory;
import org.apache.commons.text.CaseUtils;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.util.Models;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.jetbrains.annotations.NotNull;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.XSDVocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplString;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CERIF2Model implements AutoCloseable {

	protected final Logger log = LoggerFactory.getLogger( getClass().getName() );

	private final List<Section> datatypes = new ArrayList<>();

	private final Map<IRI, Future<? extends OWLEntity>> datatypeByIRI = new HashMap<>();

	private final List<Section> entities = new ArrayList<>();
	
	private final Map<String, Future<? extends OWLEntity>> entityByName = new HashMap<>();
	
	private final Map<String, Map<String, Relationship>> relationshipByEntityAndRelationshipName = new HashMap<>();

	public static final String DEFAULT_LANGUAGE_CODE = "en";

	private final IRI baseIRI;

	private final OWLOntology ont;

	private final OWLOntologyManager man = OWLManager.createOWLOntologyManager();

	private final OWLDataFactory dataFactory = man.getOWLDataFactory();

	private final ExecutorService es = Executors.newCachedThreadPool();

	private final static String CONTROL_FILE_NAME = "cerif2.ttl";

	private final static String CERIF_CORE_URI = "https://w3id.org/cerif2/";

	private final static String DOAP_PREFIX = "http://usefulinc.com/ns/doap#";

	private final static org.eclipse.rdf4j.model.IRI DOAP_PROJECT = Values.iri( DOAP_PREFIX, "Project" );

	private final static org.eclipse.rdf4j.model.IRI DOAP_NAME = Values.iri( DOAP_PREFIX, "name" );

	public CERIF2Model( final Path path ) throws OWLOntologyCreationException, IOException, ParseException {
		super();
		final Path controlFilePath = path.resolve( CONTROL_FILE_NAME );
		try (final InputStream in = Files.newInputStream( controlFilePath ) ) {
			final Model m = Rio.parse( in, "", RDFFormat.TURTLE );
			final Iterator<Resource> mainSubjectsIt = m.filter(null, RDF.TYPE, DOAP_PROJECT).subjects().iterator();
			if ( ! mainSubjectsIt.hasNext() ) {
				throw new IllegalArgumentException( "No rdf:type statement found in " + controlFilePath );
			}
			final Resource mainIRI = mainSubjectsIt.next();
			this.baseIRI = IRI.create( mainIRI.toString() );
			if ( mainSubjectsIt.hasNext() ) {
				throw new IllegalArgumentException( "More than one rdf:type statement found in " + controlFilePath );
			}

			final String moduleName = Models.objectString( m.filter( mainIRI, DOAP_NAME, null ) ).orElse( "-" );
			log.info( "Starting model for module '" + moduleName + "', IRI " + baseIRI );
			this.ont = man.createOntology( baseIRI );
			if ( mainIRI.toString().equals( CERIF_CORE_URI ) ) {
				initializeBasicCoreDatatypes();
			} else {
				// FIXME this is a mock, should be replaced with properly handling dependencies
				log.info( "Reading in the CERIF Core first" );
				final CERIF2Model cerifCore = new CERIF2Model( path.getParent().resolve( "CERIF-Core" ) );
				dependencies.put( "https://github.com/euroCRIS/CERIF-Core/blob/main", cerifCore );
				dependencies.put( "https://github.com/EuroCRIS/CERIF-Core/blob/main", cerifCore );
				log.info( "Finished reading the CERIF Core" );
				log.info( "" );
				log.info( "================================================================================" );
				log.info( "" );
			}
			try (final DirectoryStream<Path> datatypes = Files.newDirectoryStream(path.resolve("datatypes"), "*.md")) {
				for (final Path datatypeFilePath : datatypes) {
					readInDatatypeFile(new StructuredFile(datatypeFilePath));
				}
			}
			try (final DirectoryStream<Path> entities = Files.newDirectoryStream(path.resolve("entities"), "*.md")) {
				for (final Path entityFilePath : entities) {
					readInEntityFile(new StructuredFile(entityFilePath));
				}
			}
		}
	}

	private void initializeBasicCoreDatatypes() {
		final IRI coreDatatypesIRIBase = baseIRI.resolve("datatypes");
		final IRI dateDatatypeIRI = coreDatatypesIRIBase.resolve( "Date" );
		final OWLDatatype dateDatatype = dataFactory.getOWLDatatype( dateDatatypeIRI );

		datatypeByIRI.put( coreDatatypesIRIBase.resolve("String"), CompletableFuture.supplyAsync( () -> dataFactory.getStringOWLDatatype(), es ) );
		datatypeByIRI.put( coreDatatypesIRIBase.resolve("Multilingual_String"), CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( OWL2Datatype.RDF_PLAIN_LITERAL.getIRI() ), es ) );
		datatypeByIRI.put( coreDatatypesIRIBase.resolve("Decimal"), CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( OWL2Datatype.XSD_DECIMAL.getIRI() ), es ) );
		datatypeByIRI.put( coreDatatypesIRIBase.resolve("Boolean"), CompletableFuture.supplyAsync( () -> dataFactory.getBooleanOWLDatatype(), es ) );
		datatypeByIRI.put( coreDatatypesIRIBase.resolve("URI"), CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( OWL2Datatype.XSD_ANY_URI.getIRI() ), es ) );
		datatypeByIRI.put( coreDatatypesIRIBase.resolve("Date"), CompletableFuture.supplyAsync( () -> dateDatatype, es ) );
		// FIXME check why this doesn't get serialized
		final OWLDataUnionOf owlDataUnionOf = dataFactory.getOWLDataUnionOf( dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.DATE.getIRI() ) ),
				dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.G_YEAR_MONTH.getIRI() ) ),
				dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.G_YEAR.getIRI() ) ) );
		ont.add( dataFactory.getOWLDatatypeDefinitionAxiom( dateDatatype, owlDataUnionOf ) );
	}

	public void readInDatatypeFile( final StructuredFile file ) throws ParseException {
		final Section mainSection = file.getMainSection();
		datatypes.add( mainSection );

		final Section componentsSection = mainSection.getSubsectionByTitle( "Components" );
		if ( componentsSection != null ) {
			// corresponds to an XSD complexType -> owl:Class
			final String owlClassName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
			final IRI classIRI = baseIRI.resolve( "datatypes" ).resolve( owlClassName );
			datatypeByIRI.put( classIRI, createClass( mainSection, owlClassName, classIRI, "Components", false ) );
		} else {
			// corresponds to an XSD simpleType -> owl:Datatype
			final String datatypeName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
			final IRI datatypeIRI = baseIRI.resolve( "datatypes" ).resolve( datatypeName );
			datatypeByIRI.put( datatypeIRI, CompletableFuture.supplyAsync( () -> dataFactory.getOWLDatatype( datatypeIRI ) ) );
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

	@Override
	public void close() {
		es.shutdown();
		for ( final CERIF2Model module : dependencies.values() ) {
			module.close();
		}
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

	public void save( final String outputFilePath ) throws OWLOntologyStorageException, TransformerException, IOException {
		log.info( "About to write the ontology" );
		synchronized ( this ) {
			for ( final Map.Entry<IRI, Future<? extends OWLEntity>> x : datatypeByIRI.entrySet() ) {
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
		ont.saveOntology( new TurtleDocumentFormat(), new FileDocumentTarget( new File( outputFilePath + ".ttl" ) ) );
		log.info( "Wrote " + outputFilePath + ".ttl" );
		ont.saveOntology( new RDFXMLDocumentFormat(), new FileDocumentTarget( new File( outputFilePath + ".owl" ) ) );
		log.info( "Wrote " + outputFilePath + ".owl" );

		// Here is where we will put the files split up by class names
		final Path perClassDirectory = Path.of( outputFilePath ).resolveSibling( "per-class" );
		// Create if it does not exist
		Files.createDirectories( perClassDirectory );
		// Erase first
		try ( final Stream<Path> paths = Files.walk( perClassDirectory ) ) {
			paths.sorted( Comparator.reverseOrder() ).map( Path::toFile ).forEach( File::delete );
		}

		// Do the splitting
		final TransformerFactory tf = new BasicTransformerFactory();
		final Transformer transformer = tf.newTransformer( new StreamSource( this.getClass().getResourceAsStream( "/split-up.xslt" ) ) );
		transformer.setParameter( "baseUri", baseIRI.toString() );
		final File xmlOutputFile = new File( outputFilePath + ".xml" );
		transformer.transform( new StreamSource( outputFilePath + ".owl" ), new StreamResult( xmlOutputFile ) );
		log.info( "XSLT transformed into " + outputFilePath + ".xml" );
		xmlOutputFile.delete();

		// And transform each split-up file back to Turtle
		Files.list( perClassDirectory ).forEach(
			( Path path ) -> {
				try {
					try ( final InputStream is = Files.newInputStream( path ) ) {
						final Model model = Rio.parse( is, "", RDFFormat.RDFXML );
						final Path path2 = path.resolveSibling( path.getFileName().toString().replaceFirst( "\\.owl$", ".ttl" ) );
						log.info( "Read " + path + ", writing " + path2 );
						try ( final OutputStream os = Files.newOutputStream( path2 ) ) {
							Rio.write( model, os, RDFFormat.TURTLE );
						}
					}
				} catch ( final IOException e ) {
					throw new RuntimeException( e );
				}
			}
		);
	}

	static final Pattern entityNamePattern = Pattern.compile( "\\(\\.\\./entities/([^.]*)\\.md(#[^)]*)?\\)" );

	protected void processRelationshipNode( final IRI classIRI, final OWLClass owlClass, final Node node, final BasedSequence text ) throws ParseException {
		if (!( text.startsWith( "<a name=\"" ) && text.toString().contains( "</a>" ) )) {
			throw new ParseException( "Not having an enveloping <a name=\"...\"> ... </a> around relationship title", node );
		}
		final int colonIndex = text.indexOf( " : " );
		if ( colonIndex < 0 ) {
			throw new ParseException( "No separator ' : ' for the description of the relationship", node );
		}
		final int slashIndex1 = text.indexOf( " / " );
		final int slashIndex = ( slashIndex1 >= 0 ) ? slashIndex1 : colonIndex;		
		final String firstPart = text.subSequence( 0, slashIndex ).toString().trim();
		final String secondPart = text.subSequence(  slashIndex + 3, colonIndex ).toString().trim();
		final String theRest = text.subSequence( colonIndex + 3 ).toString().trim();
		log.info( "First part = '" + firstPart + "'; second part='" + secondPart + "'" );

		final String relName1 = firstPart.replaceFirst( "<a name=\"([^\"]*)\">.*", "$1" );
		final String relName = CaseUtils.toCamelCase( relName1.replaceFirst( "^rel__", "" ), false, ' ', '-', '_', '.' );
		if ( relName.equals( relName1 ) ) {
			throw new ParseException( "Relationship name '" + relName1 + "' not starting with 'rel__'", node );
		}
		log.info( "Class " + classIRI + ", relationship " + relName );

		final String inversePropertyName;
		final String rangeClassName;
		final Matcher entityNameMatcher = entityNamePattern.matcher( secondPart );
		if ( entityNameMatcher.find() ) {
			rangeClassName = entityNameMatcher.group(1);
			final String inverseFragment1 = entityNameMatcher.group(2);
			final String fragmentPrefix = "#user-content-rel__";
			if ( ! inverseFragment1.startsWith( fragmentPrefix ) ) {
				throw new ParseException( "Inverse property fragment '" + inverseFragment1 + "' not starting with '" + fragmentPrefix + "'", node );
			}
			inversePropertyName = CaseUtils.toCamelCase( inverseFragment1.substring( fragmentPrefix.length() ), false, ' ', '-', '_', '.' );
		} else {
			rangeClassName = owlClass.getIRI().getFragment();
			final String invRelName1 = secondPart.replaceFirst( "<a name=\"([^\"]*)\">.*", "$1" );
			final String invRelName = CaseUtils.toCamelCase( invRelName1.replaceFirst( "^rel__", "" ), false, ' ', '-', '_', '.' );
			if ( invRelName.equals( invRelName1 ) ) {
				throw new ParseException( "Inverse relationship name '" + invRelName1 + "' not starting with 'rel__'", node );
			}			
			inversePropertyName = CaseUtils.toCamelCase( invRelName, false, ' ', '-', '_', '.' );
		}
		final boolean ordered = false;
		final Relationship relationship = new Relationship( relName, classIRI, rangeClassName, inversePropertyName, ordered, theRest ) {
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
		final int colonPosition = text.indexOf( ':' );
		if ( colonPosition < 0 ) {
			throw new ParseException( "No colon specified", node );
		}
		final String attributeText = text.subSequence( 0, colonPosition ).trim().toString();
		final BasedSequence rest = text.subSequence( colonPosition + 1 ).trim();
		final int endashPosition = rest.indexOf( '–' );
		final BasedSequence datatypeSpec = ( endashPosition > 0 ) ? rest.subSequence( 0, endashPosition ).trim() : rest.trim();
		final String datatypeLinkTarget = datatypeSpec.toString().replaceAll( "[^(]*\\(([^)]*)\\).*", "$1" );
		final String attributeName1 = attributeText.replaceFirst( "<a name=\"[^\"]*\">([^<]*)</a>", "$1" );
		final String attributeName = ( attributeName1.contains( " " ) || Character.isUpperCase( attributeName1.charAt( 0 ) ) ) ? CaseUtils.toCamelCase( attributeName1, false, ' ', '-', '_', '.' ) : attributeName1;
		log.info( "Attribute " + attributeName + ", datatype " + datatypeLinkTarget );
		if ( !datatypeLinkTarget.contains( "/datatypes/" ) ) {
			throw new ParseException( "Not referencing any **/datatypes/", node );
		}
		if ( datatypeLinkTarget.contains( " " ) ) {
			throw new ParseException( "Missing endash", node );
		}
		final IRI attributeIRI = classIRI.resolve( "#" + attributeName );
		final Pattern p1 = Pattern.compile( "^\\.\\./datatypes/(.*)\\.md$" );
		final Matcher m1 = p1.matcher( datatypeLinkTarget );
		if ( m1.matches() ) { // This module's datatype
			final String datatypeName = m1.group(1);
			final OWLEntity owlDatatype = getDatatypeByName( datatypeName );
			if ( owlDatatype == null ) {
				throw new IllegalStateException( "Unknown datatype " + datatypeName );
			}
            addAttribute( owlClass, owlDatatype, attributeIRI );
		} else {
			final Pattern p2 = Pattern.compile( "^(.*?)/datatypes/(.*).md$" );
			final Matcher m2 = p2.matcher( datatypeLinkTarget );
			if ( m2.matches() ) { // Some other module's datatype
				final String datatypeModuleURI = m2.group(1);
				final CERIF2Model module = locateByGithubUri( datatypeModuleURI );
				if ( module == null ) {
					throw new IllegalStateException( "Unknown module with URI " + datatypeModuleURI );
				}
				final String datatypeName = m2.group(2);
				final OWLEntity owlDatatype = module.getDatatypeByName( datatypeName );;
				if ( owlDatatype == null ) {
					throw new IllegalStateException( "Unknown datatype " + datatypeName );
				}
				addAttribute( owlClass, owlDatatype, attributeIRI );
			} else {
				throw new IllegalStateException( "Don't know how to process datatype " + datatypeLinkTarget );
			}
		}

		if ( endashPosition > 0 ) {
			final OWLAnnotationValue value = new OWLLiteralImplString( rest.subSequence( endashPosition + 1 ).toString().trim() );
			ont.add( dataFactory.getOWLAnnotationAssertionAxiom( dataFactory.getRDFSLabel(), attributeIRI, value ) );
		}
	}

	private void addAttribute( final OWLClass owlClass, final OWLEntity owlDatatype, final IRI attributeIRI ) {
		if (owlDatatype instanceof OWLClass) {
			final OWLClass owlClass2 = (OWLClass) owlDatatype;
			final OWLObjectProperty owlObjectProperty = dataFactory.getOWLObjectProperty(attributeIRI);
			ont.add(dataFactory.getOWLDeclarationAxiom(owlObjectProperty));
			ont.add(dataFactory.getOWLObjectPropertyDomainAxiom(owlObjectProperty, owlClass));
			ont.add(dataFactory.getOWLObjectPropertyRangeAxiom(owlObjectProperty, owlClass2));
		} else if (owlDatatype instanceof OWLDatatype) {
			final OWLDatatype datatype = (OWLDatatype) owlDatatype;
			final OWLDataProperty owlDataProperty = dataFactory.getOWLDataProperty(attributeIRI);
			ont.add(dataFactory.getOWLDeclarationAxiom(owlDataProperty));
			ont.add(dataFactory.getOWLDataPropertyDomainAxiom(owlDataProperty, owlClass));
			ont.add(dataFactory.getOWLDataPropertyRangeAxiom(owlDataProperty, datatype));
		} else {
			throw new IllegalStateException("Unknown construct " + owlDatatype);
		}
	}

	public OWLEntity getDatatypeByName(final String datatypeName ) {
		final IRI datatypeIRI = baseIRI.resolve(datatypeName);
		@SuppressWarnings("unchecked") final Future<OWLEntity> owlDatatypeFuture = (Future<OWLEntity>) datatypeByIRI.get(datatypeIRI);
		if (owlDatatypeFuture != null) {
			try {
				return owlDatatypeFuture.get();
			} catch (final ExecutionException e) {
				throw new RuntimeException(e.getCause());
			} catch (final InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return null;
	}

	private final Map<String, CERIF2Model> dependencies = new HashMap<String, CERIF2Model>();

	public CERIF2Model locateByGithubUri(final String cerifModuleURI ) {
		return dependencies.get( cerifModuleURI );
	}

}
