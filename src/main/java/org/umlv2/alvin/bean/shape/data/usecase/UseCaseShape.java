package org.umlv2.alvin.bean.shape.data.usecase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import org.umlv2.alvin.bean.shape.ShapeHelper;

/**
 *
 */
public class UseCaseShape extends BaseUseCaseShape {

    private static final long serialVersionUID = 1L;

    public UseCaseShape() {
        type = ShapeHelper.USECASE_CASE;
        editable = true;
        conectable = true;
        text = "Usercase";
        this.w = 100;
        this.h = 50;
    }

    protected void drawShape(Graphics2D g) {
        shape = new Ellipse2D.Double(x, y, w, h);
        g.setColor(Color.white);
        g.fill(shape);
        g.setColor(borderColor);
        g.draw(shape);
    }

}
