/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import java.awt.AlphaComposite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JPopupMenu;

/**
 *
 * @author Administrator
 */
public class J7CToolbarShowMenuButton extends J7CToolbarButton {

    protected Image arrow_bottom;

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    protected int orientation = X_AXIS;
    protected JPopupMenu showMenu;

    public J7CToolbarShowMenuButton() {
    }

    public J7CToolbarShowMenuButton(Action a) {
        super(a);
    }

    public J7CToolbarShowMenuButton(Icon icon) {
        super(icon);
    }

    public J7CToolbarShowMenuButton(String text) {
        super(text);
    }

    public J7CToolbarShowMenuButton(String text, Icon icon) {
        super(text, icon);
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        repaint();
    }

    @Override
    protected void initUI() {
        super.initUI();
        try {
            arrow_bottom = new javax.swing.ImageIcon(getClass().getResource("/arrow_bottom.png")).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    @Override
    protected void paintButton(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2.setComposite(composite);

//        int 
        //绘制边框
        if (this.mouseEnterd) {
            g2.setColor(frameColor_up);
            g2.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 2, 3, 3);
            //渐变背景
            g2.setPaint(new GradientPaint(2, 2, c1_up, 1, getHeight() / 3, c2_up));
            g2.fillRect(2, 2, getWidth() - 5, getHeight() / 3);
            //渐变二段
            g2.setPaint(new GradientPaint(1, getHeight() / 3, c3_up, 1, getHeight(), c4_up));
            g2.fillRect(2, getHeight() / 3, getWidth() - 5, getHeight() / 3 * 2 - 1);
        }
        if (this.orientation == X_AXIS) {
            int x = getWidth() - 5 - arrow_bottom.getWidth(this);
            int y = (getHeight() - arrow_bottom.getHeight(this)) >> 1;
            g2.drawImage(arrow_bottom, x, y, this);
        } else {
            int x = (getWidth() - arrow_bottom.getWidth(this)) >> 1;
            int y = getHeight() - 5 - arrow_bottom.getHeight(this);
            g2.drawImage(arrow_bottom, x, y, this);
        }
        //按钮被按下的效果
        if (this.mousePressd) {
            g2.setComposite(half_composite);
            g2.setColor(frameColor_up.darker());
            g2.fillRoundRect(1, 1, getWidth() - 4, getHeight() - 2, 3, 3);
            g2.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 2, 3, 3);
        }
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(composite);
        if (borderType == FRAME_AROUND) {
            g.setColor(COLOR_TOOLBAR_FRAME);
            g.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 2, 3, 3);
        } else if (borderType == FRAME_RIGHT) {
            g.setColor(COLOR_TOOLBAR_FRAME);
            g.drawLine(getWidth() - 1, 1, getWidth() - 1, getHeight() - 2);
        }
    }

}
