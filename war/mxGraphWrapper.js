var constantsCache = new Object();

var parent;
var graph;
var editor;

function createCanvas(id) {
	var container = document.getElementById(id);
	if (mxClient.IS_IE) {
		new mxDivResizer(container);
	}

	editor = new mxEditor();
	graph = editor.graph;
	var model = graph.getModel();

	graph.setDropEnabled(false);

	editor.setGraphContainer(container);
	var config = mxUtils.load('editors/config/keyhandler-commons.xml')
			.getDocumentElement();
	editor.configure(config);
	parent = graph.getDefaultParent();
	var style = graph.getStylesheet().getDefaultEdgeStyle();
	style[mxConstants.STYLE_ROUNDED] = true;
	style[mxConstants.STYLE_EDGE] = mxEdgeStyle.ElbowConnector;

	initializeConstantsCache();
	return graph;
}

function createOutline(parent) {
	var element = document.createElement('div');
	element.id = 'outlineContainer';
	element.style.position = 'absolute';
	element.style.overflow = 'hidden';
	element.style.top = '40px';
	element.style.right = '0px';
	element.style.width = '200px';
	element.style.height = '140px';
	element.style.borderStyle = 'solid';
	element.style.corderColor = 'black';
	parent.appendChild(element);
	if (mxClient.IS_IE) {
		new mxDivResizer(element);
	}
	return new mxOutline(graph, element);
}

function createNode(id, label, xPos, yPos, width, height) {
	var style = new Object();
	graph.getStylesheet().putCellStyle('style' + id, style);
	return graph.insertVertex(parent, id, label, xPos, yPos, width, height,
			'style' + id);
}

function createConnection(id, label, source, target) {
	return graph.insertEdge(parent, id, label, source, target);
}

function setStyle(cell, constName, constValue) {
	var styleName = resolveConstants(constName);
	var styleValue = constValue;

	if (resolveConstants(constValue))
		styleValue = resolveConstants(constValue);

	graph.setCellStyles(styleName, styleValue, [ cell ]);
}

function executeEditorAction(action) {
	editor.execute(action);
}

function initializeConstantsCache() {
	constantsCache['default_hotspot'] = mxConstants.DEFAULT_HOTSPOT
	constantsCache['min_hotspot_size'] = mxConstants.MIN_HOTSPOT_SIZE
	constantsCache['max_hotspot_size'] = mxConstants.MAX_HOTSPOT_SIZE
	constantsCache['rendering_hint_exact'] = mxConstants.RENDERING_HINT_EXACT
	constantsCache['rendering_hint_faster'] = mxConstants.RENDERING_HINT_FASTER
	constantsCache['rendering_hint_fastest'] = mxConstants.RENDERING_HINT_FASTEST
	constantsCache['dialect_svg'] = mxConstants.DIALECT_SVG
	constantsCache['dialect_vml'] = mxConstants.DIALECT_VML
	constantsCache['dialect_mixedhtml'] = mxConstants.DIALECT_MIXEDHTML
	constantsCache['dialect_preferhtml'] = mxConstants.DIALECT_PREFERHTML
	constantsCache['dialect_stricthtml'] = mxConstants.DIALECT_STRICTHTML
	constantsCache['ns_svg'] = mxConstants.NS_SVG
	constantsCache['ns_xhtml'] = mxConstants.NS_XHTML
	constantsCache['ns_xlink'] = mxConstants.NS_XLINK
	constantsCache['shadowcolor'] = mxConstants.SHADOWCOLOR
	constantsCache['svg_transform'] = mxConstants.SVG_TRANSFORM
	constantsCache['nodetype_element'] = mxConstants.NODETYPE_ELEMENT
	constantsCache['nodetype_attribute'] = mxConstants.NODETYPE_ATTRIBUTE
	constantsCache['nodetype_text'] = mxConstants.NODETYPE_TEXT
	constantsCache['nodetype_cdata'] = mxConstants.NODETYPE_CDATA
	constantsCache['nodetype_entity_reference'] = mxConstants.NODETYPE_ENTITY_REFERENCE
	constantsCache['nodetype_entity'] = mxConstants.NODETYPE_ENTITY
	constantsCache['nodetype_processing_instruction'] = mxConstants.NODETYPE_PROCESSING_INSTRUCTION
	constantsCache['nodetype_comment'] = mxConstants.NODETYPE_COMMENT
	constantsCache['nodetype_document'] = mxConstants.NODETYPE_DOCUMENT
	constantsCache['nodetype_documenttype'] = mxConstants.NODETYPE_DOCUMENTTYPE
	constantsCache['nodetype_document_fragment'] = mxConstants.NODETYPE_DOCUMENT_FRAGMENT
	constantsCache['nodetype_notation'] = mxConstants.NODETYPE_NOTATION
	constantsCache['tooltip_vertical_offset'] = mxConstants.TOOLTIP_VERTICAL_OFFSET
	constantsCache['default_valid_color'] = mxConstants.DEFAULT_VALID_COLOR
	constantsCache['default_invalid_color'] = mxConstants.DEFAULT_INVALID_COLOR
	constantsCache['highlight_strokewidth'] = mxConstants.HIGHLIGHT_STROKEWIDTH
	constantsCache['cursor_movable_vertex'] = mxConstants.CURSOR_MOVABLE_VERTEX
	constantsCache['cursor_movable_edge'] = mxConstants.CURSOR_MOVABLE_EDGE
	constantsCache['cursor_label_handle'] = mxConstants.CURSOR_LABEL_HANDLE
	constantsCache['cursor_bend_handle'] = mxConstants.CURSOR_BEND_HANDLE
	constantsCache['cursor_connect'] = mxConstants.CURSOR_CONNECT
	constantsCache['highlight_color'] = mxConstants.HIGHLIGHT_COLOR
	constantsCache['target_highlight_color'] = mxConstants.TARGET_HIGHLIGHT_COLOR
	constantsCache['invalid_connect_target_color'] = mxConstants.INVALID_CONNECT_TARGET_COLOR
	constantsCache['drop_target_color'] = mxConstants.DROP_TARGET_COLOR
	constantsCache['valid_color'] = mxConstants.VALID_COLOR
	constantsCache['invalid_color'] = mxConstants.INVALID_COLOR
	constantsCache['edge_selection_color'] = mxConstants.EDGE_SELECTION_COLOR
	constantsCache['vertex_selection_color'] = mxConstants.VERTEX_SELECTION_COLOR
	constantsCache['vertex_selection_strokewidth'] = mxConstants.VERTEX_SELECTION_STROKEWIDTH
	constantsCache['edge_selection_strokewidth'] = mxConstants.EDGE_SELECTION_STROKEWIDTH
	constantsCache['selection_dashed'] = mxConstants.SELECTION_DASHED
	constantsCache['selection_dashed'] = mxConstants.SELECTION_DASHED
	constantsCache['guide_color'] = mxConstants.GUIDE_COLOR
	constantsCache['guide_color'] = mxConstants.GUIDE_COLOR
	constantsCache['guide_strokewidth'] = mxConstants.GUIDE_STROKEWIDTH
	constantsCache['outline_color'] = mxConstants.OUTLINE_COLOR
	constantsCache['outline_strokewidth'] = mxConstants.OUTLINE_STROKEWIDTH
	constantsCache['handle_size'] = mxConstants.HANDLE_SIZE
	constantsCache['label_handle_size'] = mxConstants.LABEL_HANDLE_SIZE
	constantsCache['handle_fillcolor'] = mxConstants.HANDLE_FILLCOLOR
	constantsCache['handle_strokecolor'] = mxConstants.HANDLE_STROKECOLOR
	constantsCache['label_handle_fillcolor'] = mxConstants.LABEL_HANDLE_FILLCOLOR
	constantsCache['connect_handle_fillcolor'] = mxConstants.CONNECT_HANDLE_FILLCOLOR
	constantsCache['locked_handle_fillcolor'] = mxConstants.LOCKED_HANDLE_FILLCOLOR
	constantsCache['outline_handle_fillcolor'] = mxConstants.OUTLINE_HANDLE_FILLCOLOR
	constantsCache['outline_handle_strokecolor'] = mxConstants.OUTLINE_HANDLE_STROKECOLOR
	constantsCache['default_fontfamily'] = mxConstants.DEFAULT_FONTFAMILY
	constantsCache['default_fontsize'] = mxConstants.DEFAULT_FONTSIZE
	constantsCache['default_startsize'] = mxConstants.DEFAULT_STARTSIZE
	constantsCache['default_markersize'] = mxConstants.DEFAULT_MARKERSIZE
	constantsCache['default_imagesize'] = mxConstants.DEFAULT_IMAGESIZE
	constantsCache['entity_segment'] = mxConstants.ENTITY_SEGMENT
	constantsCache['rectangle_rounding_factor'] = mxConstants.RECTANGLE_ROUNDING_FACTOR
	constantsCache['line_arcsize'] = mxConstants.LINE_ARCSIZE
	constantsCache['arrow_spacing'] = mxConstants.ARROW_SPACING
	constantsCache['arrow_width'] = mxConstants.ARROW_WIDTH
	constantsCache['arrow_size'] = mxConstants.ARROW_SIZE
	constantsCache['page_format_a4_portrait'] = mxConstants.PAGE_FORMAT_A4_PORTRAIT
	constantsCache['page_format_a4_portrait'] = mxConstants.PAGE_FORMAT_A4_PORTRAIT
	constantsCache['page_format_letter_portrait'] = mxConstants.PAGE_FORMAT_LETTER_PORTRAIT
	constantsCache['page_format_letter_portrait'] = mxConstants.PAGE_FORMAT_LETTER_PORTRAIT
	constantsCache['none'] = mxConstants.NONE
	constantsCache['style_perimeter'] = mxConstants.STYLE_PERIMETER
	constantsCache['style_source_port'] = mxConstants.STYLE_SOURCE_PORT
	constantsCache['style_target_port'] = mxConstants.STYLE_TARGET_PORT
	constantsCache['style_port_constraint'] = mxConstants.STYLE_PORT_CONSTRAINT
	constantsCache['style_opacity'] = mxConstants.STYLE_OPACITY
	constantsCache['style_text_opacity'] = mxConstants.STYLE_TEXT_OPACITY
	constantsCache['style_overflow'] = mxConstants.STYLE_OVERFLOW
	constantsCache['style_orthogonal'] = mxConstants.STYLE_ORTHOGONAL
	constantsCache['style_exit_x'] = mxConstants.STYLE_EXIT_X
	constantsCache['style_exit_y'] = mxConstants.STYLE_EXIT_Y
	constantsCache['style_exit_perimeter'] = mxConstants.STYLE_EXIT_PERIMETER
	constantsCache['style_entry_x'] = mxConstants.STYLE_ENTRY_X
	constantsCache['style_entry_y'] = mxConstants.STYLE_ENTRY_Y
	constantsCache['style_entry_perimeter'] = mxConstants.STYLE_ENTRY_PERIMETER
	constantsCache['style_white_space'] = mxConstants.STYLE_WHITE_SPACE
	constantsCache['style_rotation'] = mxConstants.STYLE_ROTATION
	constantsCache['style_fillcolor'] = mxConstants.STYLE_FILLCOLOR
	constantsCache['style_gradientcolor'] = mxConstants.STYLE_GRADIENTCOLOR
	constantsCache['style_gradient_direction'] = mxConstants.STYLE_GRADIENT_DIRECTION
	constantsCache['style_strokecolor'] = mxConstants.STYLE_STROKECOLOR
	constantsCache['style_separatorcolor'] = mxConstants.STYLE_SEPARATORCOLOR
	constantsCache['style_strokewidth'] = mxConstants.STYLE_STROKEWIDTH
	constantsCache['style_align'] = mxConstants.STYLE_ALIGN
	constantsCache['style_vertical_align'] = mxConstants.STYLE_VERTICAL_ALIGN
	constantsCache['style_label_position'] = mxConstants.STYLE_LABEL_POSITION
	constantsCache['style_vertical_label_position'] = mxConstants.STYLE_VERTICAL_LABEL_POSITION
	constantsCache['style_image_align'] = mxConstants.STYLE_IMAGE_ALIGN
	constantsCache['style_image_vertical_align'] = mxConstants.STYLE_IMAGE_VERTICAL_ALIGN
	constantsCache['style_glass'] = mxConstants.STYLE_GLASS
	constantsCache['style_image'] = mxConstants.STYLE_IMAGE
	constantsCache['style_image_width'] = mxConstants.STYLE_IMAGE_WIDTH
	constantsCache['style_image_height'] = mxConstants.STYLE_IMAGE_HEIGHT
	constantsCache['style_image_background'] = mxConstants.STYLE_IMAGE_BACKGROUND
	constantsCache['style_image_border'] = mxConstants.STYLE_IMAGE_BORDER
	constantsCache['style_image_fliph'] = mxConstants.STYLE_IMAGE_FLIPH
	constantsCache['style_image_flipv'] = mxConstants.STYLE_IMAGE_FLIPV
	constantsCache['style_nolabel'] = mxConstants.STYLE_NOLABEL
	constantsCache['style_noedgestyle'] = mxConstants.STYLE_NOEDGESTYLE
	constantsCache['style_label_backgroundcolor'] = mxConstants.STYLE_LABEL_BACKGROUNDCOLOR
	constantsCache['style_label_bordercolor'] = mxConstants.STYLE_LABEL_BORDERCOLOR
	constantsCache['style_indicator_shape'] = mxConstants.STYLE_INDICATOR_SHAPE
	constantsCache['style_indicator_image'] = mxConstants.STYLE_INDICATOR_IMAGE
	constantsCache['style_indicator_color'] = mxConstants.STYLE_INDICATOR_COLOR
	constantsCache['style_indicator_strokecolor'] = mxConstants.STYLE_INDICATOR_STROKECOLOR
	constantsCache['style_indicator_gradientcolor'] = mxConstants.STYLE_INDICATOR_GRADIENTCOLOR
	constantsCache['style_indicator_spacing'] = mxConstants.STYLE_INDICATOR_SPACING
	constantsCache['style_indicator_width'] = mxConstants.STYLE_INDICATOR_WIDTH
	constantsCache['style_indicator_height'] = mxConstants.STYLE_INDICATOR_HEIGHT
	constantsCache['style_indicator_direction'] = mxConstants.STYLE_INDICATOR_DIRECTION
	constantsCache['style_shadow'] = mxConstants.STYLE_SHADOW
	constantsCache['style_segment'] = mxConstants.STYLE_SEGMENT
	constantsCache['style_endarrow'] = mxConstants.STYLE_ENDARROW
	constantsCache['style_startarrow'] = mxConstants.STYLE_STARTARROW
	constantsCache['style_endsize'] = mxConstants.STYLE_ENDSIZE
	constantsCache['style_startsize'] = mxConstants.STYLE_STARTSIZE
	constantsCache['style_dashed'] = mxConstants.STYLE_DASHED
	constantsCache['style_rounded'] = mxConstants.STYLE_ROUNDED
	constantsCache['style_smooth'] = mxConstants.STYLE_SMOOTH
	constantsCache['style_source_perimeter_spacing'] = mxConstants.STYLE_SOURCE_PERIMETER_SPACING
	constantsCache['style_target_perimeter_spacing'] = mxConstants.STYLE_TARGET_PERIMETER_SPACING
	constantsCache['style_perimeter_spacing'] = mxConstants.STYLE_PERIMETER_SPACING
	constantsCache['style_spacing'] = mxConstants.STYLE_SPACING
	constantsCache['style_spacing_top'] = mxConstants.STYLE_SPACING_TOP
	constantsCache['style_spacing_left'] = mxConstants.STYLE_SPACING_LEFT
	constantsCache['style_spacing_bottom'] = mxConstants.STYLE_SPACING_BOTTOM
	constantsCache['style_spacing_right'] = mxConstants.STYLE_SPACING_RIGHT
	constantsCache['style_horizontal'] = mxConstants.STYLE_HORIZONTAL
	constantsCache['style_direction'] = mxConstants.STYLE_DIRECTION
	constantsCache['style_elbow'] = mxConstants.STYLE_ELBOW
	constantsCache['style_fontcolor'] = mxConstants.STYLE_FONTCOLOR
	constantsCache['style_fontfamily'] = mxConstants.STYLE_FONTFAMILY
	constantsCache['style_fontsize'] = mxConstants.STYLE_FONTSIZE
	constantsCache['style_fontstyle'] = mxConstants.STYLE_FONTSTYLE
	constantsCache['style_autosize'] = mxConstants.STYLE_AUTOSIZE
	constantsCache['style_foldable'] = mxConstants.STYLE_FOLDABLE
	constantsCache['style_editable'] = mxConstants.STYLE_EDITABLE
	constantsCache['style_bendable'] = mxConstants.STYLE_BENDABLE
	constantsCache['style_movable'] = mxConstants.STYLE_MOVABLE
	constantsCache['style_resizable'] = mxConstants.STYLE_RESIZABLE
	constantsCache['style_cloneable'] = mxConstants.STYLE_CLONEABLE
	constantsCache['style_deletable'] = mxConstants.STYLE_DELETABLE
	constantsCache['style_shape'] = mxConstants.STYLE_SHAPE
	constantsCache['style_edge'] = mxConstants.STYLE_EDGE
	constantsCache['style_loop'] = mxConstants.STYLE_LOOP
	constantsCache['style_routing_center_x'] = mxConstants.STYLE_ROUTING_CENTER_X
	constantsCache['style_routing_center_y'] = mxConstants.STYLE_ROUTING_CENTER_Y
	constantsCache['font_bold'] = mxConstants.FONT_BOLD
	constantsCache['font_italic'] = mxConstants.FONT_ITALIC
	constantsCache['font_underline'] = mxConstants.FONT_UNDERLINE
	constantsCache['font_shadow'] = mxConstants.FONT_SHADOW
	constantsCache['shape_rectangle'] = mxConstants.SHAPE_RECTANGLE
	constantsCache['shape_ellipse'] = mxConstants.SHAPE_ELLIPSE
	constantsCache['shape_double_ellipse'] = mxConstants.SHAPE_DOUBLE_ELLIPSE
	constantsCache['shape_rhombus'] = mxConstants.SHAPE_RHOMBUS
	constantsCache['shape_line'] = mxConstants.SHAPE_LINE
	constantsCache['shape_image'] = mxConstants.SHAPE_IMAGE
	constantsCache['shape_arrow'] = mxConstants.SHAPE_ARROW
	constantsCache['shape_label'] = mxConstants.SHAPE_LABEL
	constantsCache['shape_cylinder'] = mxConstants.SHAPE_CYLINDER
	constantsCache['shape_swimlane'] = mxConstants.SHAPE_SWIMLANE
	constantsCache['shape_connector'] = mxConstants.SHAPE_CONNECTOR
	constantsCache['shape_actor'] = mxConstants.SHAPE_ACTOR
	constantsCache['shape_cloud'] = mxConstants.SHAPE_CLOUD
	constantsCache['shape_triangle'] = mxConstants.SHAPE_TRIANGLE
	constantsCache['shape_hexagon'] = mxConstants.SHAPE_HEXAGON
	constantsCache['arrow_classic'] = mxConstants.ARROW_CLASSIC
	constantsCache['arrow_block'] = mxConstants.ARROW_BLOCK
	constantsCache['arrow_open'] = mxConstants.ARROW_OPEN
	constantsCache['arrow_oval'] = mxConstants.ARROW_OVAL
	constantsCache['arrow_diamond'] = mxConstants.ARROW_DIAMOND
	constantsCache['align_left'] = mxConstants.ALIGN_LEFT
	constantsCache['align_center'] = mxConstants.ALIGN_CENTER
	constantsCache['align_right'] = mxConstants.ALIGN_RIGHT
	constantsCache['align_top'] = mxConstants.ALIGN_TOP
	constantsCache['align_middle'] = mxConstants.ALIGN_MIDDLE
	constantsCache['align_bottom'] = mxConstants.ALIGN_BOTTOM
	constantsCache['direction_north'] = mxConstants.DIRECTION_NORTH
	constantsCache['direction_south'] = mxConstants.DIRECTION_SOUTH
	constantsCache['direction_east'] = mxConstants.DIRECTION_EAST
	constantsCache['direction_west'] = mxConstants.DIRECTION_WEST
	constantsCache['direction_mask_none'] = mxConstants.DIRECTION_MASK_NONE
	constantsCache['direction_mask_west'] = mxConstants.DIRECTION_MASK_WEST
	constantsCache['direction_mask_north'] = mxConstants.DIRECTION_MASK_NORTH
	constantsCache['direction_mask_south'] = mxConstants.DIRECTION_MASK_SOUTH
	constantsCache['direction_mask_east'] = mxConstants.DIRECTION_MASK_EAST
	constantsCache['direction_mask_all'] = mxConstants.DIRECTION_MASK_ALL
	constantsCache['elbow_vertical'] = mxConstants.ELBOW_VERTICAL
	constantsCache['elbow_horizontal'] = mxConstants.ELBOW_HORIZONTAL
	constantsCache['edgestyle_elbow'] = mxConstants.EDGESTYLE_ELBOW
	constantsCache['edgestyle_entity_relation'] = mxConstants.EDGESTYLE_ENTITY_RELATION
	constantsCache['edgestyle_loop'] = mxConstants.EDGESTYLE_LOOP
	constantsCache['edgestyle_sidetoside'] = mxConstants.EDGESTYLE_SIDETOSIDE
	constantsCache['edgestyle_toptobottom'] = mxConstants.EDGESTYLE_TOPTOBOTTOM
	constantsCache['edgestyle_orthogonal'] = mxConstants.EDGESTYLE_ORTHOGONAL
	constantsCache['perimeter_ellipse'] = mxConstants.PERIMETER_ELLIPSE
	constantsCache['perimeter_rectangle'] = mxConstants.PERIMETER_RECTANGLE
	constantsCache['perimeter_rhombus'] = mxConstants.PERIMETER_RHOMBUS
	constantsCache['perimeter_triangle'] = mxConstants.PERIMETER_TRIANGLE

}

function resolveConstants(constName) {
	return constantsCache[constName];
}