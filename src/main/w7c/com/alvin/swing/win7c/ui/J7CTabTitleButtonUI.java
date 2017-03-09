/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.ui;

import com.alvin.swing.win7c.component.J7CTabPage;
import com.alvin.swing.win7c.component.J7CTabTitleButton;
import static com.alvin.swing.win7c.ui.I7CColor.COLOR_UI_TEXT;
import static com.alvin.swing.win7c.ui.I7CColor.FONT_UI_TEXT;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Administrator
 */
public class J7CTabTitleButtonUI extends BasicButtonUI {

    private static final ImageIcon leftImage = new ImageIcon(J7CTabTitleButton.class
            .getResource("/left.png"));
    private static final ImageIcon rightImage = new ImageIcon(J7CTabTitleButton.class
            .getResource("/right.png"));
    private static final ImageIcon leftImage2 = new ImageIcon(J7CTabTitleButton.class
            .getResource("/left2.png"));
    private static final ImageIcon rightImage2 = new ImageIcon(J7CTabTitleButton.class
            .getResource("/right2.png"));

    private J7CTabPage page;

    public J7CTabTitleButtonUI(J7CTabPage page) {
        this.page = page;
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        Graphics2D g2D = (Graphics2D) g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        qualityHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2D.setRenderingHints(qualityHints);

        g2D.setColor(COLOR_UI_TEXT);
        g2D.setFont(FONT_UI_TEXT);
        int x = (c.getWidth() - g2D.getFontMetrics().stringWidth(((AbstractButton) c).getText())) >> 1;
        g2D.drawString(((AbstractButton) c).getText(), x, 20);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2D = (Graphics2D) g;
        J7CTabTitleButton btn = (J7CTabTitleButton) c;
        ButtonModel model = btn.getModel();
        if (page.isActived()) {
            g2D.drawImage(leftImage.getImage(), 0, 0, c);
            g2D.drawImage(rightImage.getImage(), c.getWidth() - 5, 0, c);
            g2D.setColor(Color.white);
            g2D.fill(new Rectangle(leftImage.getIconWidth(), 0, c.getWidth() - leftImage.getIconWidth() - rightImage.getIconWidth(), c.getHeight()));
            //顶部的线条
            g2D.setColor(new Color(186, 201, 219));
            g2D.drawLine(3, 0, c.getWidth() - 5, 0);
        } else {
            g2D.setColor(new Color(186, 201, 219));
            g2D.drawLine(0, c.getHeight() - 1, c.getWidth(), c
                    .getHeight() - 1);
        }

        // 鼠标进入
        if (btn.isRolloverEnabled() && model.isRollover()) {
            g2D.drawImage(leftImage2.getImage(), 1, 0, c);
            g2D.drawImage(rightImage2.getImage(), c.getWidth() - 4, 0, c);
            g2D.setColor(new Color(250, 184, 15));
            g2D.drawLine(3, 0, c.getWidth() - 5, 0);
            // 填充颜色不一致部分
            if (page.isActived()) {
                g2D.setColor(Color.white);
            } else {
                g2D.setColor(I7CColor.COLOR_PANEL_BACKGROUND);
            }
            g2D.fill(new Rectangle(leftImage2.getIconWidth(), 2, c.getWidth() - leftImage2.getIconWidth() - rightImage2.getIconWidth(), c.getHeight() - 2));
            //左边外面画一条竖线
            g2D.drawLine(3, 2, 3, c.getHeight() - 1);
            //左边里面画一条竖线
            g2D.drawLine(4, 1, 4, c.getHeight() - 1);
            //右边外面画一条竖线
            g2D.drawLine(c.getWidth() - 4, 2, c.getWidth() - 4, c.getHeight() - 1);
            //右边里面画一条竖线
            g2D.drawLine(c.getWidth() - 5, 1, c.getWidth() - 5, c.getHeight() - 1);
            g2D.drawLine(c.getWidth() - 3, 3, c.getWidth() - 3, c.getHeight() - 1);
        }
        super.paint(g, c);
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        super.paintButtonPressed(g, b); //To change body of generated methods, choose Tools | Templates.
    }

    // 渐变效果线程
    class PageButtonThread extends Thread {

        private final J7CTabTitleButton pb;
        private int aspect = 1;// 1正方向,-1 反方向
        private final Color[] colors = {new Color(255, 255, 255),
            new Color(245, 250, 253), new Color(238, 245, 251),
            new Color(231, 240, 249), new Color(224, 235, 247),};// 5次完成渐变

        public PageButtonThread(J7CTabTitleButton pb, int aspect) {
            this.pb = pb;
            this.aspect = aspect;
        }

        public void run() {
            if (aspect == -1) {
                for (Color c : colors) {
                    pb.setBackground(c);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                for (int i = colors.length - 1; i >= 0; i--) {
                    pb.setBackground(colors[i]);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        initComponent(c);
    }

    protected void initComponent(JComponent c) {
        c.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if ("paintUnSelected".equals(evt.getPropertyName()) || "paintSelected".equals(evt.getPropertyName())) {
                new PageButtonThread((J7CTabTitleButton) c, 1).start();
            }
        });
    }
}
