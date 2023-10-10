package org.eurocris.cerif2.owl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class StructuredFile {
	
	private static final MutableDataSet options = new MutableDataSet();
	static {
	    // uncomment to set optional extensions
	    options.set( Parser.EXTENSIONS, Arrays.asList( TablesExtension.create(), StrikethroughExtension.create() ) );
	
	    // uncomment to convert soft-breaks to hard breaks
	    //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");
	}
	
    private static final Parser parser = Parser.builder(options).build();

	protected final Logger log = LoggerFactory.getLogger( getClass().getName() );

	private final Path path;
	
	private final Section section;
	
	public StructuredFile( final Path path ) throws IOException, ParseException {
		this.path = path;
		final Document document = parser.parseReader( Files.newBufferedReader( path ) );
		this.section = Section.create( document.getChildIterator() );
		if ( section.getLevel() != 1 ) {
			throw new ParseException( "File not starting with heading of level 1", document.getFirstChild() );
		}
		
		log.debug( "Reading " + path.toString() + " with title \"" + section.getTitle() + "\"" );
	}

	public Path getPath() {
		return path;
	}

	public Section getMainSection() {
		return section;
	}

	@Override
	public String toString() {
		return path.toString() + ", title " + section.getTitle();
	}
	
}
