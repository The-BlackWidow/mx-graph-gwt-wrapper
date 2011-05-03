package com.subex.client;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Document;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.subex.client.mxgraph.Canvas;
import com.subex.client.mxgraph.CanvasConstants;
import com.subex.client.mxgraph.MxWindow;
import com.subex.client.mxgraph.Node;

@SuppressWarnings("unchecked")
public class Mxgraphgwt implements EntryPoint {

	Canvas canvas;

	public void onModuleLoad() {
		ContentPanel mainPanel = new ContentPanel();
		ContentPanel canvas = new ContentPanel();
		canvas.setId("canvas");
		canvas.addWidgetListener(new WidgetListener() {
			public void widgetAttached(ComponentEvent ce) {
				drawCanvas();
			}

		});
		canvas.setHeading("canvas");
		canvas.setStyleAttribute("overflow", "scroll");

		mainPanel.setBottomComponent(getToolbar());
		mainPanel.setHeight("600");
		mainPanel.add(canvas);
		RootPanel.get().add(mainPanel);
	}

	private Component getToolbar() {
		ToolBar toolBar = new ToolBar();
		toolBar.setId("toolbar");
		toolBar.setHeight(20);
		Button zoomIn = new Button("+");
		zoomIn.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				canvas.zoomIn();
			}
		});
		toolBar.add(zoomIn);

		Button zoomOut = new Button("-");
		zoomOut.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				canvas.zoomOut();
			}
		});
		toolBar.add(zoomOut);

		Button zoomReset = new Button("1:1");
		zoomReset.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				canvas.actualSize();
			}
		});
		toolBar.add(zoomReset);

		Button fit = new Button("Fit");
		fit.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				canvas.fit();
			}
		});
		toolBar.add(fit);

		Button show = new Button("show");
		show.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				canvas.showInNewWindow();
			}
		});
		toolBar.add(show);

		Button print = new Button("print");
		print.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				canvas.print();
			}
		});
		toolBar.add(print);

		Button addBtn = new Button("Add");
		addBtn.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				showWindow();
			}
		});
		toolBar.add(addBtn);

		return toolBar;
	}

	private void showWindow() {
		MxWindow window = new MxWindow();
		window.setTitle("sourish");
		window.setXPos(300);
		window.setYPos(300);
		window.setHeight(200);
		window.setWidth(200);
		window.setContentId("ssg");
		
		window.show();
		
		com.google.gwt.dom.client.Document.get().getElementById("ssg").appendChild(new ComboBox().getElement());
	}

	private ContentPanel getContent() {
		ContentPanel panel = new ContentPanel();
		panel.setId("panel");
		panel.add(new TextField());
		
		return panel;
	}

	private void drawCanvas() {
		canvas = new Canvas("canvas");

		Node n1 = canvas.addNode("1", "hello", 100, 100, 100, 90);
		n1.setStyle(CanvasConstants.STYLE_SHAPE, CanvasConstants.SHAPE_LABEL);
		n1.setStyle(CanvasConstants.STYLE_ROUNDED, "true");
		n1.setStyle(CanvasConstants.STYLE_ALIGN, CanvasConstants.ALIGN_CENTER);
		n1.setStyle(CanvasConstants.STYLE_VERTICAL_ALIGN, CanvasConstants.ALIGN_BOTTOM);
		n1.setStyle(CanvasConstants.STYLE_IMAGE_ALIGN, CanvasConstants.ALIGN_CENTER);
		n1.setStyle(CanvasConstants.STYLE_IMAGE_VERTICAL_ALIGN, CanvasConstants.ALIGN_TOP);
		n1.setStyle(CanvasConstants.STYLE_IMAGE, "http://mysticmedusa.com/wp-content/uploads/2009/07/33585.jpg");
		Node n2 = canvas.addNode("2", "hello2", 500, 100, 100, 90);
		canvas.addConnection(n1, n2);
	}
}
