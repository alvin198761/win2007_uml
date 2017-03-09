package org.umlv2.alvin.biz.usecase;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.usecase.UseCaseSysBorderShape;
import org.umlv2.alvin.bean.shape.data.usecase.line.UseCaseLineShape;
import org.umlv2.alvin.biz.BaseDragBiz;
import org.umlv2.alvin.ui.drawpane.BaseDrawComp;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;

import org.umlv2.alvin.sys.ObjectUtil;

/**
 * *
 *
 * @author ��ֲ��
 *
 */
public class UseCaseDrageBiz extends BaseDragBiz {

    protected void sourceToTarget(MouseEvent e, final Component c,
            final Rectangle2D rect) {
        try {
            BaseDataShape copyShape = UMLCore.getMainFrame().getDrawPane().getShapeFactory().createShapeFactory(shape.getType());
            UMLCore.getMainFrame().getDrawPane().addShape(copyShape);
            Point p2 = (Point) e.getPoint().clone();
            SwingUtilities.convertPointToScreen(p2, c);
            SwingUtilities.convertPointFromScreen(p2, UMLCore.getMainFrame().getDrawPane());
            double x = p2.x - copyShape.getW() / 2 - rect.getX();
            double y = p2.y - copyShape.getH() / 2 - rect.getY();
            copyShape.setX(x);
            copyShape.setY(y);
            if (copyShape instanceof UseCaseSysBorderShape) {
                copyShape.setY(p2.y - 12.5 - rect.getY());
            }

            UMLCore.getMainFrame().getGlassPane().setVisible(false);
            setImage(null);
            Application.repaint();
            UMLCore.getMainFrame().getDrawPane().setSaveed(false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    protected void targetToTarget(MouseEvent e, final Component c,
            final Rectangle2D rect) {
        Point p2 = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p2, c);
        SwingUtilities.convertPointFromScreen(p2, UMLCore.getMainFrame().getDrawPane());
        double x = p2.x - shape.getW() / 2;
        double y = p2.y - shape.getH() / 2;
        shape.setX(x - rect.getX());
        shape.setY(y - rect.getY());
        if (shape.isSubSystem()) {
            shape.setY(0);
        }

        BaseDataShape sourceShape = ((BaseDrawComp) c).getShape();
        if (sourceShape == null) {
            return;
        }

        sourceShape.setX(shape.getX());
        sourceShape.setY(shape.getY());
        if (sourceShape instanceof UseCaseSysBorderShape) {
            sourceShape.setY(p2.y - 12.5 - rect.getY());
        }

        sourceShape.draw(UMLCore.getMainFrame().getDrawPane().getImageGraphics2D());
        sourceShape.contrlLines();
        Application.repaint();
        UMLCore.getMainFrame().getGlassPane().setVisible(false);
        setImage(null);
       UMLCore.getMainFrame().getDrawPane().setSaveed(false);
    }

    protected void shapeMouseDown(MouseEvent e) {
        Component c = e.getComponent();
        BaseDataShape sourceShape = ((BaseDrawComp) c).getShape();
        if (sourceShape == null) {
            return;
        }

        if (sourceShape instanceof UseCaseLineShape) {
            return;
        }

        try {
            this.shape = (BaseDataShape) ObjectUtil.cloneObject(sourceShape);
            shape.setSelect(false);
        } catch (Exception e1) {
            e1.printStackTrace();
            this.shape = null;
            return;
        }
        // � 
        BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        c.paint(g);
        UMLCore.getMainFrame().getGlassPane().setVisible(true);
        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        SwingUtilities.convertPointFromScreen(p, UMLCore.getMainFrame().getGlassPane());

        setPoint(p);
        setImage(shape);
        UMLCore.getMainFrame().getGlassPane().repaint();
    }

    @Override
    protected void paintShape(Graphics g) {
        int y = 0;
        int x = (int) (location.getX() - (dragged.getW() / 2));
        if (dragged.isSubSystem()) {
            Point p = (Point) UMLCore.getMainFrame().getDrawPane().getLocationOnScreen().clone();
            SwingUtilities.convertPointFromScreen(p, UMLCore.getMainFrame().getGlassPane());
            Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
            y = (int) p.getY() + 1 + (int) rect.getY();
        } else if (dragged instanceof UseCaseSysBorderShape) {
            y = (int) (location.getY() - 12.5);
        } else {
            y = (int) (location.getY() - (dragged.getH() / 2));
        }
        //  
        Graphics2D g2 = (Graphics2D) g;
        // ����� ����
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(composite);
        dragged.setX(x);
        dragged.setY(y);
        dragged.draw(g2);
    }

}
