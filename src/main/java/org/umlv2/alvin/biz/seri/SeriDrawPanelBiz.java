package org.umlv2.alvin.biz.seri;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import org.umlv2.alvin.bean.shape.data.BaseLineShape;
import org.umlv2.alvin.bean.shape.data.seri.line.BaseSeriLineShape;
import org.umlv2.alvin.bean.shape.data.seri.line.SeriMessageLineShape;
import org.umlv2.alvin.biz.BaseDrawPanelBiz;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;

/**
 * �
 *
 * @author Administrator
 *
 */
public class SeriDrawPanelBiz extends BaseDrawPanelBiz {

    protected void resizeShape(MouseEvent e) {
        if (!(UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape().getCtrl() instanceof BaseSeriLineShape)) {
            UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape().getCtrl().contrlLines();
            return;
        }
        aotuConnection(e);
        Application.repaint();
    }

    protected void drawLineStart(MouseEvent e) {
        UMLCore.getMainFrame().getDrawPane().clearSelect();
        SeriMessageLineShape line = new SeriMessageLineShape();
        line.setSelect(true);
        line.setText("");
        line.setX(mousePoint.getX());
        line.setY(mousePoint.getY());
        Application.anchor.setLocation(line.getX(), line.getY());
        Application.repaint();
        UMLCore.getMainFrame().getDrawPane().addShape(line);
        UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(line);
    }

    protected void drawLineFinish(MouseEvent e) {
        drawLineDrag(e);
        BaseLineShape line = (BaseLineShape) UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape();
        if (Math.abs(line.getX2() - line.getX()) < 2
                && Math.abs(line.getY2() - line.getY()) < 2) {
            UMLCore.getMainFrame().getDrawPane().getShapes().remove(UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape());
            UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(null);
            Application.repaint();
            return;
        }
        Application.repaint();
    }

    protected void drawLineDrag(MouseEvent e) {
        Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
        if (UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape() == null) {
            return;
        }
        BaseLineShape line = (BaseLineShape) UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape();
        line.setX2(e.getPoint().getX() - rect.getX());
        line.setY2(e.getPoint().getY() - rect.getY());
    }
}
