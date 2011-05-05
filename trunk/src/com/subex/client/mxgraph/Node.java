package com.subex.client.mxgraph;

import com.google.gwt.core.client.JavaScriptObject;

public class Node extends CanvasItem {
	Node(String id, String label, int xPos, int yPos, int width, int height) {
		super.id = id;
		setJsObject(createNode(id, label, xPos, yPos, width, height));
	}

	public void setStyle(String styleName, String styleValue) {
		Canvas.setStyle(getJsObject(), styleName, styleValue);
	}

	private static native JavaScriptObject createNode(String id, String label, int xPos, int yPos, int width, int height) /*-{
		return $wnd.createNode( id, label, xPos, yPos, width, height );
	}-*/;
}
