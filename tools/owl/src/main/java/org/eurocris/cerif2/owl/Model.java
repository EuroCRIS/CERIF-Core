package org.eurocris.cerif2.owl;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.util.ast.Node;

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

	public Model( final String moduleName ) throws OWLOntologyCreationException {
		super();
		this.baseIRI = IRI.create( IRI_BASE + "/", moduleName + "/" );
		log.info( "Starting model for module '" + moduleName + "', IRI " + baseIRI );
		this.ont = man.createOntology( baseIRI );
	}

	public void readInDatatypeFile( final StructuredFile file ) {
		final Section mainSection = file.getMainSection();
		datatypes.add( mainSection );
	}

	public void readInEntityFile( final StructuredFile file ) {
		final Section mainSection = file.getMainSection();
		entities.add( mainSection );

		final String owlClassName = file.getPath().getFileName().toString().replaceFirst( "\\.md$", "" );
		final IRI classIRI = baseIRI.resolve( owlClassName );
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
		} else {
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
	}

	public void save() throws OWLOntologyStorageException {
		ont.saveOntology( System.out );
	}
	
}
