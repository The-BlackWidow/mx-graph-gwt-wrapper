package com.subex.client.mxgraph;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.core.client.JavaScriptObject;

public class Canvas<T> extends ContentPanel
{
	private JavaScriptObject jsObject;
	private boolean widgetAttached;

	public Canvas( final String id )
	{		
		setId(id);
		createCanvas( getId() );
		addWidgetListener( new WidgetListener()
		{
			public void widgetAttached( ComponentEvent ce )
			{
				jsObject = createCanvas( getId() );
				widgetAttached = true;
			}
		} );
	}
	
	public Node addNode( String id, String label, int xPos, int yPos, int width, int height )
	{
		Node node = new Node( id, label, xPos, yPos, width, height );
		return node;
	};
	
	public Connection addConnection( Node source, Node target )
	{
		Connection connection = new Connection( source, target );
		return connection;
	};

	public static native JavaScriptObject setStyle( JavaScriptObject cell, String styleName, String styleValue ) /*-{
		return $wnd.setStyle(cell, styleName, styleValue);
	}-*/;
	
	private static native JavaScriptObject createCanvas( String id ) /*-{
		return $wnd.createCanvas(id);
	}-*/;
	
	public native void zoomIn() /*-{
		$wnd.executeEditorAction('zoomIn');
	}-*/;
	
	public native void zoomOut() /*-{
		$wnd.executeEditorAction('zoomOut');
	}-*/;
	
	public native void actualSize() /*-{
		$wnd.executeEditorAction('actualSize');
	}-*/;
	
	public native void fit() /*-{
		$wnd.executeEditorAction('fit');
	}-*/;
	
	public native void showInNewWindow() /*-{
		$wnd.executeEditorAction('show');
	}-*/;
	
	public native void print() /*-{
		$wnd.executeEditorAction('print');
	}-*/;
}
