package com.subex.client.mxgraph;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class MxWindow extends CanvasItem {
	private int height;
	private int width;
	private String title;
	private int xPos;
	private int yPos;
	private String contentId;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int pos) {
		xPos = pos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int pos) {
		yPos = pos;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentId() {
		return contentId;
	}

	public void show() {
		setJsObject(showNative(title, getContentId(), xPos, yPos, width, height));
	}

	public void attach(LayoutContainer content) {
		Element element = com.google.gwt.dom.client.Document.get().getElementById(getContentId());
		content.render((com.google.gwt.user.client.Element) element);
	}

	public native void setMaximizable(boolean isMaximizable) /*-{
		var node = this.@com.subex.client.mxgraph.CanvasItem::getJsObject()();
		node.setMaximizable(isMaximizable);
	}-*/;

	public native void setScrollable(boolean isScrollable) /*-{
		var node = this.@com.subex.client.mxgraph.CanvasItem::getJsObject()();
		node.setScrollable(isScrollable );
	}-*/;

	public native void setResizable(boolean isResizable) /*-{
		var node = this.@com.subex.client.mxgraph.CanvasItem::getJsObject()();
		node.setResizable(isResizable );
	}-*/;

	public native void destroy() /*-{
		var node = this.@com.subex.client.mxgraph.CanvasItem::getJsObject()();
		node.destroy();
	}-*/;

	public native JavaScriptObject showNative(String title, String contentId, int xPos, int yPos, int width, int height) /*-{
		var content = document.createElement('div');
		content.id = contentId;
		var wnd = new $wnd.mxWindow(title, content, xPos, yPos, width, height);
		wnd.setVisible(true);
		return wnd;
	}-*/;

}
