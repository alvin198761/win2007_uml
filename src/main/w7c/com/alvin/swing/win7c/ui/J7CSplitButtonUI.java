/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.ui;

import com.alvin.swing.win7c.component.J7CSplitButton;
import static com.alvin.swing.win7c.ui.I7CColor.COLOR_TOOLBAR_FRAME;
import static com.alvin.swing.win7c.ui.I7CColor.c1_up;
import static com.alvin.swing.win7c.ui.I7CColor.c2_up;
import static com.alvin.swing.win7c.ui.I7CColor.c3_up;
import static com.alvin.swing.win7c.ui.I7CColor.c4_up;
import static com.alvin.swing.win7c.ui.I7CColor.frameColor_up;
import static com.alvin.swing.win7c.ui.I7CColor.half_composite;
import java.awt.AlphaComposite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Administrator
 */
public class J7CSplitButtonUI extends BasicButtonUI {

    protected float alpha = 1f;//不透明

    protected Image arrow_bottom;
    protected Image arrow_right;
    protected Point mousePoint = new Point(0, 0);

    public J7CSplitButtonUI() {
        try {
            arrow_bottom = new javax.swing.ImageIcon(getClass().getResource("/arrow_bottom.png")).getImage();
            arrow_right = new javax.swing.ImageIcon(getClass().getResource("/arrow_right.png")).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        initComponent(c);
    }

    protected void initComponent(final JComponent c) {
        c.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                new Thread() {
                    public void run() {
                        float a = 0f;
                        while (a <= 1f) {
                            alpha = a;
                            c.repaint();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                            }
                            a += 0.1;
                        }
                    }
                }.start();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mousePoint.setLocation(e.getPoint());
                int sx = 0;
                int sy = 0;
                sx = c.getWidth() - arrow_right.getWidth(null);
                sy = c.getHeight() - arrow_right.getHeight(null);
                RoundRectangle2D leftBox = new RoundRectangle2D.Float(1, 1, sx, c.getHeight() - 2, 3, 3);
                RoundRectangle2D rightBox = new RoundRectangle2D.Float(sx + 3, 1, c.getWidth() - sx + 2, c.getHeight() - 2, 1, 3);
                J7CSplitButton btn = (J7CSplitButton) c;
                if (leftBox.contains(e.getPoint())) {
                    for (ActionListener al : btn.getActionListeners()) {
                        ActionEvent aevt = new ActionEvent(btn, e.getID(), btn.getActionCommand());
                        al.actionPerformed(aevt);
                    }
                } else if (rightBox.contains(e.getPoint())) {
                    ActionEvent aevt = new ActionEvent(btn, e.getID(), btn.getActionCommand());
                    btn.getRightAction().actionPerformed(aevt);
                }
            }
        });
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        J7CSplitButton btn = (J7CSplitButton) c;
        ButtonModel model = btn.getModel();
        int sx = 0;
        int sy = 0;
        sx = btn.getWidth() - arrow_right.getWidth(null);
        sy = btn.getHeight() - arrow_right.getHeight(null);

        Graphics2D g2 = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2.setComposite(composite);

        //绘制边框
        if (btn.isRolloverEnabled() && model.isRollover()) {
            //左边的按钮
            g2.setColor(frameColor_up);
            g2.drawRoundRect(1, 1, sx, btn.getHeight() - 2, 3, 3);
            //渐变背景
            g2.setPaint(new GradientPaint(2, 2, c1_up, 1, btn.getHeight() / 3, c2_up));
            g2.fillRect(2, 2, sx, btn.getHeight() / 3);
            //渐变二段
            g2.setPaint(new GradientPaint(1, btn.getHeight() / 3, c3_up, 1, btn.getHeight(), c4_up));
            g2.fillRect(2, btn.getHeight() / 3, sx, btn.getHeight() / 3 * 2 - 1);
            /////右边的按钮
            g2.setColor(frameColor_up);
            g2.drawRoundRect(sx + 3, 1, btn.getWidth() - sx - 5, btn.getHeight() - 2, 3, 3);
            //渐变背景
            g2.setPaint(new GradientPaint(sx + 3, 2, c1_up, 1, btn.getHeight() / 3, c2_up));
            g2.fillRect(sx + 3, 2, btn.getWidth() - sx, btn.getHeight() / 3);
            //渐变二段
            g2.setPaint(new GradientPaint(sx + 3, btn.getHeight() / 3, c3_up, 1, btn.getHeight(), c4_up));
            g2.fillRect(sx + 3, btn.getHeight() / 3, btn.getWidth() - sx, btn.getHeight() / 3 * 2 - 1);
            //分割的线条
            composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2.setComposite(composite);
            g.setColor(COLOR_TOOLBAR_FRAME);
            g.drawLine(sx + 1, 3, sx + 1, btn.getHeight() - 4);
            g.drawLine(sx + 2, 3, sx + 2, btn.getHeight() - 4);
        }
        g2.drawImage(arrow_right, sx + 3, sy >> 1, null);
        super.paint(g, c);
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        J7CSplitButton btn = (J7CSplitButton) b;
        Graphics2D g2 = (Graphics2D) g;
        int sx = 0;
        int sy = 0;
        sx = btn.getWidth() - arrow_right.getWidth(null);
        sy = btn.getHeight() - arrow_right.getHeight(null);
        g2.setComposite(half_composite);
        g2.setColor(frameColor_up.darker());
        RoundRectangle2D leftBox = new RoundRectangle2D.Float(1, 1, sx, btn.getHeight() - 2, 3, 3);
        RoundRectangle2D rightBox = new RoundRectangle2D.Float(sx + 3, 1, btn.getWidth() - sx + 2, btn.getHeight() - 2, 1, 3);
        if (leftBox.contains(mousePoint)) {
            g2.fill(leftBox);
        } else {
            g2.fill(rightBox);
        }
    }

}
