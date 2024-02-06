package org.eurocris.cerif2.owl;

import com.vladsch.flexmark.util.ast.Node;

public class ParseException extends Exception {

	private static final long serialVersionUID = 2761622547753887110L;

	private final Node node;

	public ParseException( final String message, final Node node ) {
		super( message );
		this.node = node;
	}
	
	@Override
	public String getMessage() {
		final StringBuilder sb = new StringBuilder( super.getMessage() );
		sb.append( " on " );
		node.getAstExtra( sb );
		return sb.toString();
	}

}
