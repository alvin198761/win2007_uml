package org.umlv2.alvin.biz.active;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.BaseLineShape;
import org.umlv2.alvin.bean.shape.data.active.line.ActiveLineShape;
import org.umlv2.alvin.biz.BaseDrawPanelBiz;
import org.umlv2.alvin.action.ActionManager;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;

/**
 * �
 *
 * @author Administrator
 *
 */
public class ActiveDrawPanelBiz extends BaseDrawPanelBiz {

    protected void resizeShape(MouseEvent e) {
        UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape().getCtrl().contrlLines();
    }

    protected void drawLineStart(MouseEvent e) {
        UMLCore.getMainFrame().getDrawPane().clearSelect();
        BaseDataShape lineHoverShape;
        for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
            shape.containsLineHover(mousePoint);
        }

        lineHoverShape = getLineHoverShape();
        if (lineHoverShape == null) {
            ActionManager.fireActionEnabled();
            return;
        }

        ActiveLineShape line = new ActiveLineShape();
        line.setSelect(true);
        line.setX(lineHoverShape.getBounds2D().getCenterX());
        line.setY(lineHoverShape.getBounds2D().getCenterY());
        line.setBeginShape(lineHoverShape);
        Application.anchor.setLocation(line.getX(), line.getY());
        Application.repaint();
        UMLCore.getMainFrame().getDrawPane().addShape(line);
        UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(line);
    }

    protected void drawLineFinish(MouseEvent e) {
        if (UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape() == null) {
            return;
        }

        BaseDataShape shape = getLineHoverShape();
        if (shape == null) {
            UMLCore.getMainFrame().getDrawPane().getShapes().remove(UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape());
            Application.repaint();
            UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(null);
            return;
        } else {
            BaseLineShape line = (BaseLineShape) UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape();
            line.setEndShape(shape);
            if (line.getEndShape() == null) {
                UMLCore.getMainFrame().getDrawPane().getShapes().remove(UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape());
                Application.repaint();
                UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(null);
                return;
            }
            Application.repaint();
        }
    }

    protected void drawLineDrag(MouseEvent e) {
        Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
        Point2D p = new Point2D.Double(e.getPoint().getX() - rect.getX(), e
                .getPoint().getY() - rect.getY());
        BaseLineShape line = (BaseLineShape) UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape();
        if (line == null) {
            return;
        }
        line.setX2(e.getPoint().getX() - rect.getX());
        line.setY2(e.getPoint().getY() - rect.getY());
        for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
            shape.containsLineHover(p);
        }
    }

}
