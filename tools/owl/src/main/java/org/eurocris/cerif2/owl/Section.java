package org.eurocris.cerif2.owl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.ThematicBreak;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;

public class Section {
	
	private final Heading heading;
	
	private final String title;
	
	private final List<Node> contents;
	
	private final Map<String, Section> subsectionsMap = new HashMap<>();
	
	private final List<Section> subsectionsList = new LinkedList<>();

	public static Section create( final ReversiblePeekingIterator<Node> iterator ) throws ParseException {
		if ( iterator.hasNext() ) {
			final Node firstNode = iterator.next();
			if ( firstNode instanceof Heading ) {
				final List<Node> contents = new ArrayList<Node>();
				while ( iterator.hasNext() ) {
					final Node nextNode = iterator.peek();
					if ( nextNode instanceof Heading ) break;
					iterator.next();
					if (!( nextNode instanceof ThematicBreak )) {
						contents.add( nextNode );
					}
				}
				final Section section = new Section( (Heading) firstNode, contents );

				while ( iterator.hasNext() ) {
					final Node nextNode = iterator.peek();				
					final Heading nextHeading = (Heading) nextNode;
					if ( nextHeading.getLevel() <= section.getLevel() ) break;
					final Section subsection = create( iterator );
					section.addSubsection( subsection );
				}
				
				return section;
			} else {
				throw new ParseException( "Heading expected", firstNode );
			}
		} else {
			return null;
		}
	}
	
	private Section( final Heading heading, final List<Node> contents ) {
		this.heading = heading;
		this.title = heading.getText().toString();
		this.contents = new ArrayList<>( contents );
	}

	private void addSubsection( final Section subsection ) {
		subsectionsList.add( subsection );
		subsectionsMap.put( subsection.getTitle(), subsection );
	}
	
	public Heading getHeading() {
		return heading;
	}
	
	public String getTitle() {
		return title;
	}

	public int getLevel() {
		return heading.getLevel();
	}
	
	public List<Node> getContents() {
		return Collections.unmodifiableList( contents );
	}
	
	public List<Section> getSubsections() {
		return Collections.unmodifiableList( subsectionsList );
	}
	
	public Section getSubsectionByTitle( final String title ) {
		return subsectionsMap.get( title );
	}

}
