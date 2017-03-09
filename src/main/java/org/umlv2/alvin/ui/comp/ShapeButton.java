package org.umlv2.alvin.ui.comp;

import org.umlv2.alvin.ui.drawpane.BaseDrawComp;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.BaseLineShape;
import org.umlv2.alvin.bean.shape.data.seri.line.BaseCallLineShape;
import org.umlv2.alvin.ui.drawpane.DrawPane;

public class ShapeButton extends JLabel implements Icon, BaseDrawComp {

    private static final long serialVersionUID = 1L;

    private BaseDataShape shape;
    private static int icon_width = 75;
    private DrawPane drawPanel;

    public ShapeButton(DrawPane drawPanel, String type) {
        this.drawPanel = drawPanel;
        this.shape = drawPanel.getShapeFactory().createMenuShapeByType(type);
        setIcon(this);
        setToolTipText(shape.getText());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.blue);
        Rectangle2D rect = new Rectangle2D.Double(0, 0, icon_width, icon_width);
        g2.draw(rect);
        if (shape instanceof BaseCallLineShape) {
            double w = 30;
            double h = ((BaseCallLineShape) shape).getY2() - shape.getY();
            shape.setX((icon_width - w) / 2);
            shape.setY((icon_width - h) / 2);
            ((BaseCallLineShape) shape).setX2(shape.getX());
            ((BaseCallLineShape) shape).setY2(shape.getY() + h);
        } else if (shape instanceof BaseLineShape) {
            shape.setX((icon_width - 30) / 2);
            shape.setY((icon_width - shape.getH()) / 2);
            ((BaseLineShape) shape).setX2(shape.getX() + 30);
            ((BaseLineShape) shape).setY2(shape.getY());
        } else {
            shape.setX((icon_width - shape.getW()) / 2);
            shape.setY((icon_width - shape.getH()) / 2);
        }
        shape.draw(g2);

        rect = new Rectangle2D.Double(0, 0, rect.getWidth() - 2,
                rect.getHeight() - 2);
    }

    public int getIconWidth() {
        return icon_width;
    }

    public int getIconHeight() {
        return icon_width;
    }

    public BaseDataShape getShape() {
        return this.drawPanel.getShapeFactory().createShapeFactory(shape.getType());
    }

}
