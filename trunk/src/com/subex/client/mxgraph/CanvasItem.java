package com.subex.client.mxgraph;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class CanvasItem
{
	private JavaScriptObject jsObject;
	protected String id;

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public void setJsObject( JavaScriptObject jsObject )
	{
		this.jsObject = jsObject;
	}

	public JavaScriptObject getJsObject()
	{
		return jsObject;
	}
}
