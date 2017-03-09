package org.umlv2.alvin.bean.shape.data.seri.line;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.umlv2.alvin.bean.shape.ShapeHelper;

/**
 */
public class SeriAsynchronousMessageLineShape extends SeriMessageLineShape {

	private static final long serialVersionUID = 1L;

	public SeriAsynchronousMessageLineShape() {
		type = ShapeHelper.SERI_MESSAGE_ASY;
		text =  "SeriAsyMsg" ;
	}

	protected void drawArrow(Graphics2D g, double x, double y, double x2,
			double y2) {
		double d = Math.abs(Point2D.distance(x2, y2, x, y));
		double xb = x2 + arrowLen * ((x - x2) - (y - y2) / 2) / d;
		double yb = y2 + arrowLen * ((y - y2) + (x - x2) / 2) / d;
		g.draw(new Line2D.Double(x2, y2, xb, yb));
	}

}
