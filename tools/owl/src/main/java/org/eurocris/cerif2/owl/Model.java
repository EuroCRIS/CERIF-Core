package org.eurocris.cerif2.owl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Model {

	protected final Logger log = LoggerFactory.getLogger( getClass().getName() );

	public void readInDatatypeFile( final StructuredFile file ) {
		log.info( "Parsing {1} as a datatype", file );
	}

	public void readInEntityFile( final StructuredFile file ) {
		log.info( "Parsing {1} as an entity", file );
	}

}
