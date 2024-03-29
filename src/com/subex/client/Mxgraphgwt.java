package com.subex.client;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.subex.client.mxgraph.Canvas;
import com.subex.client.mxgraph.CanvasConstants;
import com.subex.client.mxgraph.CanvasIntializeListener;
import com.subex.client.mxgraph.MxWindow;
import com.subex.client.mxgraph.Node;

@SuppressWarnings("unchecked")
public class Mxgraphgwt implements EntryPoint {

	Canvas canvas;
	MxWindow window;
	ContentPanel mainPanel;
	Node n1;
	int id = 1;
	private TextArea description;
	private NumberField height;
	private NumberField width;
	private NumberField yPos;
	private NumberField xPos;
	private TextField<String> firstName;

	public void onModuleLoad() {
		mainPanel = new ContentPanel();
		drawCanvas();
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

		Button removeBtn = new Button("Remove");
		removeBtn.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (window != null) {
					Node n2 = createNode(Integer.toString(id++), firstName.getValue(), xPos.getValue().intValue(), yPos.getValue().intValue(), width.getValue().intValue(), height.getValue().intValue());
					n2.setTooltip(description.getValue());
					canvas.addConnection(n1, n2);
					window.destroy();
					window = null;
				}
			}
		});
		toolBar.add(removeBtn);

		return toolBar;
	}

	private void showWindow() {
		window = new MxWindow();
		window.setTitle("sourish");
		window.setXPos(300);
		window.setYPos(300);
		window.setHeight(200);
		window.setWidth(355);
		window.setContentId("ssg");

		window.show();
		window.setMaximizable(true);

		LayoutContainer content = getContent(window);
		window.attach(content);
	}

	private LayoutContainer getContent(final MxWindow window) {
		FormData formData = new FormData("-20");
		FormPanel simple = new FormPanel();
		simple.setFrame(true);
		simple.setWidth(350);

		firstName = new TextField<String>();
		firstName.setFieldLabel("Name");
		firstName.setAllowBlank(false);
		simple.add(firstName, formData);
		
		xPos = new NumberField();
		xPos.setFieldLabel("X Pos");
		xPos.setAllowBlank(false);
		simple.add(xPos, formData);		
		
		yPos = new NumberField();
		yPos.setFieldLabel("Y Pos");
		yPos.setAllowBlank(false);
		simple.add(yPos, formData);	
		
		width = new NumberField();
		width.setFieldLabel("Width");
		width.setAllowBlank(false);
		simple.add(width, formData);	
		
		height = new NumberField();
		height.setFieldLabel("Height");
		height.setAllowBlank(false);
		simple.add(height, formData);	

		description = new TextArea();
		description.setPreventScrollbars(true);
		description.setFieldLabel("Tooltip");
		simple.add(description, formData);

		return simple;
	}

	private void drawCanvas() {
		canvas = new Canvas("canvas", new CanvasIntializeListener() {
			public void onCanvasInitialized() {
				n1 = createNode(Integer.toString(id++), "hello", 100, 100, 100, 90);
				canvas.addOutline(mainPanel.getElement());
			}

		});

	}

	private Node createNode(String id, String label, int xPos, int yPos, int width, int height) {
		Node temp = canvas.addNode(id, label, xPos, yPos, width, height);
		temp.setTooltip("Sourish");
		temp.setStyle(CanvasConstants.STYLE_SHAPE, CanvasConstants.SHAPE_LABEL);
		temp.setStyle(CanvasConstants.STYLE_ROUNDED, "true");
		temp.setStyle(CanvasConstants.STYLE_ALIGN, CanvasConstants.ALIGN_CENTER);
		temp.setStyle(CanvasConstants.STYLE_VERTICAL_ALIGN, CanvasConstants.ALIGN_BOTTOM);
		temp.setStyle(CanvasConstants.STYLE_IMAGE_ALIGN, CanvasConstants.ALIGN_CENTER);
		temp.setStyle(CanvasConstants.STYLE_IMAGE_VERTICAL_ALIGN, CanvasConstants.ALIGN_TOP);
		temp.setStyle(CanvasConstants.STYLE_IMAGE, "http://mysticmedusa.com/wp-content/uploads/2009/07/33585.jpg");
		return temp;
	}
}
