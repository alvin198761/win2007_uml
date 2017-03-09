/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.ui;

import com.alvin.swing.win7c.component.J7CToolbarToggerButton;
import static com.alvin.swing.win7c.component.J7CToolbarToggerButton.FRAME_AROUND;
import static com.alvin.swing.win7c.component.J7CToolbarToggerButton.FRAME_RIGHT;
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
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

/**
 *
 * @author Administrator
 */
public class J7CToolbarToggerButtonUI extends BasicToggleButtonUI {

    protected float alpha = 1f;//不透明

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        initComponent(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        J7CToolbarToggerButton btn = (J7CToolbarToggerButton) c;
        ButtonModel model = btn.getModel();
        Graphics2D g2 = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2.setComposite(composite);
        if (btn.isRolloverEnabled() && model.isRollover() || btn.isSelected()) {
            //绘制边框
            g2.setColor(frameColor_up);
            g2.drawRoundRect(1, 1, btn.getWidth() - 4, btn.getHeight() - 2, 3, 3);
            //渐变背景
            g2.setPaint(new GradientPaint(2, 2, c1_up, 1, btn.getHeight() / 3, c2_up));
            g2.fillRect(2, 2, btn.getWidth() - 5, btn.getHeight() / 3);
            //渐变二段
            g2.setPaint(new GradientPaint(1, btn.getHeight() / 3, c3_up, 1, btn.getHeight(), c4_up));
            g2.fillRect(2, btn.getHeight() / 3, btn.getWidth() - 5, btn.getHeight() / 3 * 2 - 1);
        }
        //
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2.setComposite(composite);
        if (btn.getBorderType() == FRAME_AROUND) {
            g.setColor(COLOR_TOOLBAR_FRAME);
            g.drawRoundRect(1, 1, btn.getWidth() - 4, btn.getHeight() - 2, 3, 3);
        } else if (btn.getBorderType() == FRAME_RIGHT) {
            g.setColor(COLOR_TOOLBAR_FRAME);
            g.drawLine(btn.getWidth() - 1, 1, btn.getWidth() - 1, btn.getHeight() - 2);
        }
        super.paint(g, c);
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(half_composite);
        g2.setColor(frameColor_up.darker());
        g2.fillRoundRect(1, 1, b.getWidth() - 4, b.getHeight() - 2, 3, 3);
        g2.drawRoundRect(1, 1, b.getWidth() - 4, b.getHeight() - 2, 3, 3);
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
        super.paintFocus(g, b, viewRect, textRect, iconRect); //To change body of generated methods, choose Tools | Templates.
    }

    protected void initComponent(JComponent c) {
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

        });
    }

}
