/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import static com.alvin.swing.win7c.ui.I7CColor.COLOR_UI_TEXT;
import static com.alvin.swing.win7c.ui.I7CColor.FONT_UI_TEXT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Administrator
 */
public class J7CPopeMenu extends JPopupMenu {

    public static Color COLOR_PANEL_BACKGROUND = new Color(224, 235, 247);
    private static ImageIcon buttonmage = new ImageIcon(J7CPopeMenu.class
            .getResource("/menubutton3.png"));   //菜单标题图标
    private JPanel leftMenu;    //左面板
    private JPanel rightMenu;   //右面板
    private GridLayout gridLayout = new GridLayout(1, 0);
    private int menuItemHeight = 50;  //菜单默认高度 
    public final static int MENU_WIDTH = 150; //菜单的宽度
    private BufferedImage titleBgImage;
    //
    private LinkedList<J7CToolbarButton> menuItems = new LinkedList<>();

    public J7CPopeMenu() {
        leftMenu = new JPanel();
        leftMenu.setLayout(gridLayout);
        leftMenu.setBackground(Color.WHITE);
        leftMenu.setBounds(0, 0, this.getWidth(), this.getHeight());
        //
        rightMenu = new JPanel();
        rightMenu.setBackground(new Color(239, 245, 250));
        //
        setLayout(new BorderLayout()); //东南西北中的布局
        setBackground(COLOR_PANEL_BACKGROUND);
        setPopupSize(400, 48); //原始高度,顶部+底部状态条

        try {
            titleBgImage = ImageIO.read(J7CPopeMenu.class.getResource("/menutitle.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //顶部标题条
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                TexturePaint tp = new TexturePaint(titleBgImage, new Rectangle(0, 0, 2, 28));
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(tp);
                g.fillRect(0, 0, getWidth(), 28);
                g.drawImage(buttonmage.getImage(), 0, 0, this);
                g.setColor(new Color(171, 184, 201));
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        };

        p.setPreferredSize(new Dimension(this.getWidth(), 28));
        this.add(p, BorderLayout.NORTH);
        this.add(leftMenu, BorderLayout.WEST);
        this.add(rightMenu, BorderLayout.CENTER);

        //底部状态条
        JPanel bp = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(0, 0, new Color(249, 252, 255), 0, 25, new Color(226, 235, 249)));
                g.fillRect(0, 0, getWidth(), 25);
                g.setColor(new Color(171, 184, 201));
                g.drawLine(1, 1, getWidth(), 1);
            }
        };
        bp.setPreferredSize(new Dimension(this.getWidth(), 25));
        this.add(bp, BorderLayout.SOUTH);

        //右边顶部标题
        JPanel rpt = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //消除锯齿
                RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                qualityHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                ((Graphics2D) g).setRenderingHints(qualityHints);
                g.setColor(COLOR_UI_TEXT);
                g.setFont(FONT_UI_TEXT);
                g.drawString("最近的打开的文件", 10, 20);
                g.setColor(new Color(171, 184, 201));
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        };
        rpt.setPreferredSize(new Dimension(400 - MENU_WIDTH, 30));
        rpt.setBackground(rightMenu.getBackground());
        rightMenu.setLayout(new BorderLayout());
        rightMenu.add(rpt, BorderLayout.NORTH);
    }

    @Override
    public final void update(Graphics g) {
        this.paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    //增加菜单
    public void addMenuItem(J7CToolbarButton menuItem) {
        gridLayout.setRows(menuItems.size() + 1);
        setPopupSize(400, 48 + menuItemHeight + menuItems.size() * menuItemHeight);
        menuItem.setPreferredSize(new Dimension(MENU_WIDTH, menuItemHeight));
        //设置高度 
        leftMenu.add(menuItem);
        menuItems.add(menuItem);
    }

}
