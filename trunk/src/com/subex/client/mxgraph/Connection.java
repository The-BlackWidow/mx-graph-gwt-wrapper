package com.subex.client.mxgraph;

import com.google.gwt.core.client.JavaScriptObject;

public class Connection extends CanvasItem {
	public Connection(Node source, Node target) {
		id = source.id + "_" + target.id;
		setJsObject(createConnection(id, "", source.getJsObject(), target.getJsObject()));
	}

	private static native JavaScriptObject createConnection(String id, String label, JavaScriptObject source, JavaScriptObject target) /*-{
		return $wnd.createConnection( id, label, source, target );
	}-*/;
}
