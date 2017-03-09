package org.umlv2.alvin.bean.shape.ctrl;

import java.awt.geom.Point2D;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.BaseLineShape;

/**
 * 
 */
public class LineResizeShape extends CtrlResizeShape {

	private static final long serialVersionUID = 1L;

	public LineResizeShape(BaseDataShape ctrl, String way) {
		super(ctrl, way);
	}

	protected void contrl13(Point2D p) {
		BaseLineShape line = (BaseLineShape) this.ctrl;
		line.setX2(p.getX());
		line.setY2(p.getY());
	}

	protected void contrl8(Point2D p) {
		BaseLineShape line = (BaseLineShape) this.ctrl;
		line.setX(p.getX());
		line.setY(p.getY());
	}

}
