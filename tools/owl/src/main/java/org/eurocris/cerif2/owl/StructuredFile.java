package org.eurocris.cerif2.owl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StructuredFile implements Iterable<Section> {

	private final Path path;
	
	private final String title;
	
	private final Map<String, Section> sectionsMap = new HashMap<>();;
	
	public StructuredFile( final Path path ) throws IOException {
		this.path = path;
		final Iterator<String> i = Files.lines( path ).iterator();
		if (! i.hasNext() ) throw new IllegalArgumentException( "Empty file" );
		final String line1 = i.next();
		if (! line1.startsWith( "# " ) ) throw new IllegalArgumentException( "First line does not start with # and a space" );
		this.title = line1.substring( 2 );
		if (! i.hasNext() ) throw new IllegalArgumentException( "File contained just one line" );
		final String line2 = i.next();
		if (! line2.isEmpty() ) throw new IllegalArgumentException( "The second line is not empty" );
		readInSections( i, sectionsMap );
	}

	private static void readInSections( final Iterator<String> i, final Map<String, Section> sectionsMap2 ) {
		// TODO
	}

	public Path getPath() {
		return path;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public Iterator<Section> iterator() {
		return sectionsMap.values().iterator();
	}

}
