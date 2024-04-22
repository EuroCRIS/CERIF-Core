package org.eurocris.cerif2.owl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tools {
	
	protected final static Logger log = LoggerFactory.getLogger( Tools.class );

	public Tools() {
	}

	public static void main( final String[] args ) {
		final Tools tools = new Tools();
		try {
			for ( final String dir : args ) {
				tools.readInAndProcess( dir );
			}
		} catch ( final IOException | ParseException | OWLOntologyStorageException | OWLOntologyCreationException | URISyntaxException e ) {
			log.error( "When processing", e );
		}
	}

	private void readInAndProcess( final String dir ) throws IOException, ParseException, OWLOntologyCreationException, OWLOntologyStorageException, URISyntaxException {
		// FIXME work out the URI from the context the tool runs in
		final URI docsBaseURI = new URI( "https://github.com/EuroCRIS/CERIF-Core/blob/main" );
		
		final File moduleBaseDir = new File( dir );
		final Model model = new Model( moduleBaseDir.getName() );
		if ( moduleBaseDir.isDirectory() ) {
			try ( final DirectoryStream<Path> datatypes = Files.newDirectoryStream( moduleBaseDir.toPath().resolve( "datatypes" ), "*.md" ) ) {
				for ( final Path datatypeFilePath : datatypes ) {
					model.readInDatatypeFile( new StructuredFile( datatypeFilePath, new URI( docsBaseURI + "/datatypes/" + datatypeFilePath.getFileName().toString() ) ) );
				}
			}
			try ( final DirectoryStream<Path> entities = Files.newDirectoryStream( moduleBaseDir.toPath().resolve( "entities" ), "*.md" ) ) {
				for ( final Path entityFilePath : entities ) {
					model.readInEntityFile( new StructuredFile( entityFilePath, new URI( docsBaseURI + "/entities/" + entityFilePath.getFileName().toString() ) ) );
				}
			}
		} else {
			throw new IllegalArgumentException( dir + " does not resolve to a directory" );
		}
		model.save();
	}
	
}
