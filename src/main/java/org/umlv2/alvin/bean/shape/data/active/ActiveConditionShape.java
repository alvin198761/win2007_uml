package org.umlv2.alvin.bean.shape.data.active;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.util.Map.Entry;

import org.umlv2.alvin.bean.shape.ShapeHelper;
import org.umlv2.alvin.bean.shape.ctrl.CtrlResizeShape;

/**
 *
 *
 */
public class ActiveConditionShape extends BaseActiveShape {

    private static final long serialVersionUID = 1L;
    private static final int absWidth = 100;
    private static final int absHeight = 50;

    public ActiveConditionShape() {
        type = ShapeHelper.ACTIVE_CONDITION;
        w = absWidth;
        h = absHeight;
        text = "Decision";
    }

    protected void endInit() {
        for (Entry<String, CtrlResizeShape> entry : resizeMap.entrySet()) {
            entry.getValue().setEnable(false);
        }
    }

    protected void drawShape(Graphics2D g) {
        double hx = this.x + (w / 2);
        double hy = this.y + (h / 2);
        Polygon p = new Polygon();
        p.addPoint((int) hx, (int) y);
        p.addPoint((int) x, (int) hy);
        p.addPoint((int) hx, (int) (y + h));
        p.addPoint((int) (x + w), (int) hy);
        shape = new GeneralPath(p);
        g.setColor(Color.white);
        g.fill(shape);
        g.setColor(borderColor);
        g.draw(shape);
    }

}
