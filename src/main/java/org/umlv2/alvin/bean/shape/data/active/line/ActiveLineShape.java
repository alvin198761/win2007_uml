package org.umlv2.alvin.bean.shape.data.active.line;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.umlv2.alvin.bean.shape.ShapeHelper;
import org.umlv2.alvin.bean.shape.data.BaseLineShape;


public class ActiveLineShape extends BaseLineShape {

    private static final long serialVersionUID = 1L;

    public ActiveLineShape() {
        type = ShapeHelper.ACTIVE_LINE;
        text = "Line";
    }

    protected void drawText(Graphics2D g) {

    }

    protected void drawResize(Graphics2D g) {
        return;
    }

    protected void init() {
        this.ctrlable = false;
    }

    protected void drawLine(Graphics2D g) {
        shape = new Line2D.Double(x, y, x2, y2);
        g.setColor(borderColor);
        g.draw(shape);
    }

    /**
     */
    public void changePoint() {
        if (beginShape == null || endShape == null) {
            return;
        }
        Point2D sP = new Point2D.Double(beginShape.getX() + beginShape.getW()
                / 2, beginShape.getY() + beginShape.getH());
        Point2D eP = new Point2D.Double(endShape.getX() + endShape.getW() / 2,
                endShape.getY() + endShape.getH());
        if ((eP.getX() > sP.getX())
                && (Math.abs(eP.getX() - sP.getX()) > Math.abs(eP.getY()
                        - sP.getY()))) {
            beginShape.removeLine(this);
            beginShape.addBeginLine(this, ShapeHelper.WAY_RIGHT);

            endShape.removeLine(this);
            endShape.addEndLine(this, ShapeHelper.WAY_LEFT);

        } else if ((eP.getX() < sP.getX())
                && (Math.abs(eP.getX() - sP.getX()) > Math.abs(eP.getY()
                        - sP.getY()))) {
            beginShape.removeLine(this);
            beginShape.addBeginLine(this, ShapeHelper.WAY_LEFT);

            endShape.removeLine(this);
            endShape.addEndLine(this, ShapeHelper.WAY_RIGHT);
        } else if ((eP.getY() > sP.getY())
                && (Math.abs(eP.getX() - sP.getX()) < Math.abs(eP.getY()
                        - sP.getY()))) {
            // 225��< p <315��
            beginShape.removeLine(this);
            beginShape.addBeginLine(this, ShapeHelper.WAY_BOTTOM);

            endShape.removeLine(this);
            endShape.addEndLine(this, ShapeHelper.WAY_TOP);
        } else if ((eP.getY() < sP.getY())
                && (Math.abs(eP.getX() - sP.getX()) < Math.abs(eP.getY()
                        - sP.getY()))) {
            // 45��< p <135��
            beginShape.removeLine(this);
            beginShape.addBeginLine(this, ShapeHelper.WAY_TOP);

            endShape.removeLine(this);
            endShape.addEndLine(this, ShapeHelper.WAY_BOTTOM);
        } else {
            System.out.println("不支持");
        }
    }

}
