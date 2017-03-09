package org.umlv2.alvin.bean.shape.data.seri.line;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import org.umlv2.alvin.bean.shape.ShapeHelper;

/**
 * 
 */
public class SeriMessageLineShape extends BaseSeriLineShape {

	private static final long serialVersionUID = 1L;

	public SeriMessageLineShape() {
		type = ShapeHelper.SERI_MESSAGE;
		text = "SeriMsg" ;
	}

	
	protected void drawLine(Graphics2D g) {
		shape = new Line2D.Double(x, y, x2, y2);
		g.setColor(borderColor);
		g.draw(shape);
	}

	
}
