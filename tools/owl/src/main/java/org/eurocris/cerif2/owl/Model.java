package org.eurocris.cerif2.owl;

import java.util.ArrayList;
import java.util.List;

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
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.XSDVocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplString;

public class Model {

	protected final Logger log = LoggerFactory.getLogger( getClass().getName() );

	private final List<Section> datatypes = new ArrayList<>();
	
	private final List<Section> entities = new ArrayList<>();

    public static final IRI IRI_BASE = IRI.create( "https://w3id.org/cerif2/" );
    
    public static final String DEFAULT_LANGUAGE_CODE = "en";
	
    private final IRI baseIRI;

    private final OWLOntology ont;

    private final OWLOntologyManager man = OWLManager.createOWLOntologyManager();

    private final OWLDataFactory dataFactory = man.getOWLDataFactory();

	private final OWLDatatype dateDatatype;

	public Model( final String moduleName ) throws OWLOntologyCreationException {
		super();
		this.baseIRI = IRI.create( IRI_BASE.toString(), moduleName + "/" );
		log.info( "Starting model for module '" + moduleName + "', IRI " + baseIRI );
		this.ont = man.createOntology( baseIRI );
		
		final IRI dateDatatypeIRI = baseIRI.resolve( "Date__Datatype" );
		this.dateDatatype = dataFactory.getOWLDatatype( dateDatatypeIRI );
		final OWLDataUnionOf owlDataUnionOf = dataFactory.getOWLDataUnionOf(
				dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.DATE.getIRI() ) ),
				dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.G_YEAR_MONTH.getIRI() ) ),
				dataFactory.getOWLDatatype( dataFactory.getOWLDatatype( XSDVocabulary.G_YEAR.getIRI() ) )
		);
		if ( moduleName.equals( "CERIF-Core" ) ) {
			ont.add( dataFactory.getOWLDatatypeDefinitionAxiom(dateDatatype, owlDataUnionOf ) );
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
			createClass( mainSection, owlClassName, classIRI, "Components", false );
		} else {
			// corresponds to an XSD simpleType -> owl:Datatype

		}
	}

	public void readInEntityFile( final StructuredFile file ) throws ParseException {
		final Section mainSection = file.getMainSection();
		entities.add( mainSection );

		final String owlClassName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
		final IRI classIRI = baseIRI.resolve( owlClassName );
		createClass( mainSection, owlClassName, classIRI, "Attributes", true );
	}

	protected void createClass( final Section mainSection, final String owlClassName, final IRI classIRI, final String attributesSectionTitle, final boolean definitionObligatory ) throws ParseException {
		log.info( "Declaring class '" + owlClassName + "', IRI " + classIRI );
		final OWLClass owlClass = dataFactory.getOWLClass( classIRI );
		ont.add( dataFactory.getOWLDeclarationAxiom( owlClass ) );
		
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
			Link x = (Link) specializationOfSection.getContents().get(0).getChildOfType( Link.class );
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
				@NotNull BasedSequence text = node.getChars().trim();
				log.info( "Node " + node + ": " + text );
				if ( i == 1 && ( text.startsWith( "Beside" ) || text.startsWith( "Those " ) || text.startsWith( "None besides those of " ) || text.startsWith( "Just those of " ) ) ) {
					// ignore
				} else if ( text.startsWith( "FIXME" ) ) {
					// ignore
				} else {
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
					final String attributeName = CaseUtils.toCamelCase( attributeText, false, ' ', '-', '_', '.' );
					log.info( "Attribute " + attributeName + ", datatype " + datatypeLinkTarget );
					if (! datatypeLinkTarget.startsWith( "../datatypes/" ) ) {
						throw new ParseException( "Not referencing ../datatypes/", node );
					}
					if ( datatypeLinkTarget.contains( " " ) ) {
						throw new ParseException( "Missing endash", node );
					}
					final String datatypeName = datatypeLinkTarget.replaceFirst( "\\.\\./datatypes/(.*)\\.md", "$1" );
					final OWLDatatype datatype;
					switch ( datatypeName ) {
					case "String":
						datatype = dataFactory.getStringOWLDatatype();
						break;
					case "Multilingual_String":
						datatype = dataFactory.getOWLDatatype( OWL2Datatype.RDF_PLAIN_LITERAL.getIRI() );
						break;
					case "Decimal":
						datatype = dataFactory.getOWLDatatype( OWL2Datatype.XSD_DECIMAL.getIRI() );
						break;
					case "Boolean":
						datatype = dataFactory.getBooleanOWLDatatype();
						break;
					case "URI":
						datatype = dataFactory.getOWLDatatype( OWL2Datatype.XSD_ANY_URI.getIRI() );
						break;
					case "Date":
						datatype = dateDatatype;
						break;
					default:
						datatype = dataFactory.getOWLDatatype( baseIRI.resolve( "datatypes/" + datatypeName ) );
					}
					
					final IRI attributeIRI = classIRI.resolve( "#" + attributeName );
					final OWLDataProperty owlDataProperty = dataFactory.getOWLDataProperty( attributeIRI );
					final OWLDeclarationAxiom owlAttributeDecl = dataFactory.getOWLDeclarationAxiom( owlDataProperty );
					ont.add( owlAttributeDecl );
					ont.add( dataFactory.getOWLDataPropertyDomainAxiom( owlDataProperty, owlClass ) );
					ont.add( dataFactory.getOWLDataPropertyRangeAxiom( owlDataProperty, datatype ) );
					
					if ( endashPosition > 0 ) {
						final OWLAnnotationValue value = new OWLLiteralImplString( rest.subSequence( endashPosition + 1 ).toString().trim() );
						ont.add( dataFactory.getOWLAnnotationAssertionAxiom( dataFactory.getRDFSLabel(), attributeIRI, value ) );
					}
				}
			}
		}
	}

	public void save() throws OWLOntologyStorageException {
		final OWLDocumentFormat format = new TurtleDocumentFormat();
		ont.saveOntology( format, System.out );
	}
	
}
