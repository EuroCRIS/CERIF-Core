package org.eurocris.cerif2.owl;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tools {
	
	protected final static Logger log = LoggerFactory.getLogger( Tools.class.getName() );

	public Tools() {
	}

	public static void main( final String[] args ) {
		final Tools tools = new Tools();
		try {
			final Model model = tools.readIn( args );
		} catch ( final IOException e ) {
			log.error( "When processing", e );
		}
	}

	private Model readIn( final String[] dirs ) throws IOException {
		final Model model = new Model();
		for ( final String dir : dirs ) {
			final File moduleBaseDir = new File( dir );
			if ( moduleBaseDir.isDirectory() ) {
				try ( final DirectoryStream<Path> datatypes = Files.newDirectoryStream( moduleBaseDir.toPath().resolve( "datatypes" ), "*.md" ) ) {
					for ( final Path datatypeFilePath : datatypes ) {
						model.readInDatatypeFile( new StructuredFile( datatypeFilePath ) );
					}
				}
				try ( final DirectoryStream<Path> entities = Files.newDirectoryStream( moduleBaseDir.toPath().resolve( "entities" ), "*.md" ) ) {
					for ( final Path entityFilePath : entities ) {
						model.readInEntityFile( new StructuredFile( entityFilePath ) );
					}
				}
			} else {
				throw new IllegalArgumentException( dir + " does not resolve to a directory" );
			}
		}
		log.info( "Model: {1}", model );
		return model;
	}
	
}
