/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.ui.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import static javafx.scene.paint.Color.color;
import javax.swing.Icon;

/**
 *
 * @author Administrator
 */
public class EllipseIcon implements Icon {

    private Image icon;
    private int size;
    private Color color;

    public EllipseIcon(Image icon,   Color color) {
        this.icon = icon;
        this.size = icon.getWidth(null);
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        int centerX = getIconWidth() >> 1;
        int centerY = getIconWidth() >> 1;
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, getIconWidth() - 1, getIconHeight() - 1);

        //draw body 
        g2d.setColor(color);
        GradientPaint paint = new GradientPaint(centerX, centerY, color, centerX, centerY + centerY * 2, color.brighter().brighter());
        g2d.setPaint(paint);
        g2d.fill(circle);

        //draw image 
        g2d.setClip(circle);
        g2d.drawImage(icon, centerX - (icon.getWidth(null) >> 1), centerY - (icon.getHeight(null) >> 1), null);
        g2d.setClip(null);

        //draw highlight. 
//        Shape highlightArea = createHighlightShape(centerX, centerY, radius);
//        g2d.setColor(new Color(255, 255, 255, 150));
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

}
