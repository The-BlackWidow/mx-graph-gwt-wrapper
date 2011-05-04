package com.subex.client.mxgraph;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.core.client.JavaScriptObject;

public class MxOutline extends ContentPanel {
	
	private JavaScriptObject jsObject;
	
	public MxOutline() {
		setBodyBorder(true);
		setId("outlineContainer");
		setStyleAttribute("position", "absolute");
		setStyleAttribute("overflow", "hidden");
		setStyleAttribute("top", "36px");
		setStyleAttribute("right", "0px");
		setStyleAttribute("width", "200px");
		setStyleAttribute("height", "140px");
		setStyleAttribute("border-style", "solid");
		setStyleAttribute("border-color", "black");
//position:absolute;:;:;:0px;:200px;height:140px;background:transparent;border-style:solid;border-color:black;
		addWidgetListener( new WidgetListener()
		{
			public void widgetAttached( ComponentEvent ce )
			{
				jsObject = createMxOutline(getElement());
			}
		});
	}

	private native JavaScriptObject createMxOutline(JavaScriptObject element) /*-{
		return $wnd.createOutline(element);
	}-*/;
}
