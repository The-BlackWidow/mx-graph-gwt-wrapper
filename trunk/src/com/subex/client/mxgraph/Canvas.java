package com.subex.client.mxgraph;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;

public class Canvas<T> extends ContentPanel {

	private Map<String, CanvasItem> canvasItems = new HashMap<String, CanvasItem>();
	private JavaScriptObject jsObject;
	private boolean widgetAttached;

	public Canvas(final String id, final CanvasIntializeListener listener) {
		setId(id);
		// createCanvas( getId() );
		addWidgetListener(new WidgetListener() {
			public void widgetAttached(ComponentEvent ce) {
				jsObject = createCanvas(getId());
				widgetAttached = true;
				if (listener != null)
					listener.onCanvasInitialized();
				registerTooltip(Canvas.this);
				registerClick(Canvas.this);
				registerdDoubleClick(Canvas.this);
			}
		});
	}

	public Node addNode(String id, String label, int xPos, int yPos, int width, int height) {
		Node node = new Node(id, label, xPos, yPos, width, height);
		canvasItems.put(node.getId(), node);
		return node;
	};

	public Connection addConnection(Node source, Node target) {
		Connection connection = new Connection(source, target);
		canvasItems.put(connection.getId(), connection);
		return connection;
	};

	public static native JavaScriptObject setStyle(JavaScriptObject cell, String styleName, String styleValue) /*-{
		return $wnd.setStyle(cell, styleName, styleValue);
	}-*/;

	private static native JavaScriptObject createCanvas(String id) /*-{
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

	public native JavaScriptObject addOutline(JavaScriptObject parent) /*-{
		return $wnd.createOutline(parent);
	}-*/;

	private native void registerTooltip(Canvas<T> canvas) /*-{
		$wnd.graph.getTooltipForCell = function(cell)
		{
			return canvas.@com.subex.client.mxgraph.Canvas::getTooltip(Ljava/lang/String;)(cell.id);
		}
	}-*/;
	
	@SuppressWarnings("unused")
	private String getTooltip(String id)
	{
		return canvasItems.get(id).getTooltip();
	}
	
	private native void registerClick(Canvas<T> canvas) /*-{
		$wnd.graph.addListener($wnd.mxEvent.CLICK, function(sender, evt)
		{
			var cell = evt.getProperty('cell');

			if (cell != null)
			{
				canvas.@com.subex.client.mxgraph.Canvas::onClick(Ljava/lang/String;)(cell.id);
			}
			else
			{
				canvas.@com.subex.client.mxgraph.Canvas::onCanvasClick()();
			}
		});
	}-*/;
	
	@SuppressWarnings("unused")
	private void onClick(String id)
	{
		
	}
	
	@SuppressWarnings("unused")
	private void onCanvasClick()
	{
	}
	
	private native void registerdDoubleClick(Canvas<T> canvas) /*-{
		$wnd.graph.addListener($wnd.mxEvent.DOUBLE_CLICK, function(sender, evt)
		{
			var cell = evt.getProperty('cell');

			if (cell != null)
			{
				canvas.@com.subex.client.mxgraph.Canvas::onDoubleClick(Ljava/lang/String;)(cell.id);
			}
		});
	}-*/;
	
	@SuppressWarnings("unused")
	private void onDoubleClick(String id)
	{
		Window.alert(id);
	}
}
