package org.eurocris.cerif2.owl;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.TransformerException;

public class Tools {
	
	protected final static Logger log = LoggerFactory.getLogger( Tools.class );

	protected final static String OUTPUT_FILE_PATH = "serializations/RDF/core";

    public Tools() {
	}

	public static void main( final String[] args ) {
		final Tools tools = new Tools();
		try {
			for ( final String dir : args ) {
				tools.readInAndProcess( dir );
			}
		} catch ( final IOException | ParseException | OWLOntologyStorageException | OWLOntologyCreationException | TransformerException e ) {
			log.error( "When processing", e );
        }
		log.info( "Done" );
    }

	public final IRI iriBase = IRI.create( "https://w3id.org/cerif2/" );

	private void readInAndProcess( final String dir ) throws IOException, ParseException, OWLOntologyCreationException, OWLOntologyStorageException, TransformerException {
		final Path path = Paths.get( dir ).toAbsolutePath().normalize();
		if ( ! Files.isDirectory( path ) ) {
			throw new IllegalArgumentException(dir + " does not resolve to a directory");
		}
		try ( final CERIF2Model model = new CERIF2Model( path ) ) {
			try (final DirectoryStream<Path> datatypes = Files.newDirectoryStream(path.resolve("datatypes"), "*.md")) {
				for (final Path datatypeFilePath : datatypes) {
					model.readInDatatypeFile(new StructuredFile(datatypeFilePath));
				}
			}
			try (final DirectoryStream<Path> entities = Files.newDirectoryStream(path.resolve("entities"), "*.md")) {
				for (final Path entityFilePath : entities) {
					model.readInEntityFile(new StructuredFile(entityFilePath));
				}
			}
			model.save(Tools.OUTPUT_FILE_PATH);
            model.generateShaclShapes(Tools.OUTPUT_FILE_PATH);
		}
	}
	
}
