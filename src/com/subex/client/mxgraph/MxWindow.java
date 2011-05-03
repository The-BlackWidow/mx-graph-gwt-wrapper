package com.subex.client.mxgraph;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

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

	public String getContent() {
		return contentId;
	}	
	
	public void show() {
		Window.alert("hi666666");
		showNative(title, new ComboBox().getElement(), xPos, yPos, width, height);
	}
	
	public native JavaScriptObject showNative(String title, Element contentId, int xPos, int yPos, int width, int height) /*-{
		var content = document.createElement('div');
		content.id = contentId;
		var wnd = new $wnd.mxWindow(title, contentId, xPos, yPos, width, height);
		wnd.setMaximizable(true);
		wnd.setScrollable(true);
		wnd.setResizable(true);
		wnd.setVisible(true);
		
		return wnd;
	}-*/;

}
