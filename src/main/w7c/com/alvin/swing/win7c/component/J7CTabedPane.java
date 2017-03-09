/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import static com.alvin.swing.win7c.ui.I7CColor.COLOR_PANEL_BACKGROUND;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class J7CTabedPane extends JPanel {

    private boolean showMenu = false;//显示菜单
    private boolean showAnchorButton = false;//显示圆形按钮
    private J7CPopeMenu popeMenu;//如果使用菜单，就用这个
    private J7CAnchorButton anchorButton;
    private LinkedList<J7CTabPage> pages = new LinkedList<>();//所有的页面
    private JFrame owner;
    private TabButtonPane buttonPane;
    private J7CTabPage activePage;
    private JButton menuButton;

    private static final ImageIcon buttonmage1 = new ImageIcon(J7CTabedPane.class
            .getResource("/menubutton1.png"));
    private static ImageIcon buttonmage2 = new ImageIcon(J7CTabedPane.class
            .getResource("/menubutton2.png"));
    private static ImageIcon buttonmage3 = new ImageIcon(J7CTabedPane.class
            .getResource("/menubutton3.png"));

    public J7CTabedPane(JFrame owner, ImageIcon anchorIcon) {
        this.owner = owner;
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
//        this.anchorButton = new J7CAnchorButton(anchorIcon);
        buttonPane = new TabButtonPane();
        add(buttonPane, BorderLayout.NORTH);
        menuButton = new JButton();
        menuButton.setBorder(null);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener((ActionEvent e) -> {
            popeMenu.show(J7CTabedPane.this, menuButton.getX(), menuButton.getY());
        });
        //
        menuButton.setIcon(buttonmage1);
        menuButton.setRolloverIcon(buttonmage2);
        menuButton.setPressedIcon(buttonmage3);
        //
        popeMenu = new J7CPopeMenu();
        resetMenu();
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        if (showAnchorButton) {
            return;
        }
        if (showMenu == this.showMenu) {
            return;
        }
        this.showMenu = showMenu;
        resetMenu();
    }

    public boolean isShowAnchorButton() {
        return showAnchorButton;
    }

    public void setShowAnchorButton(boolean showAnchorButton) {
        this.showAnchorButton = showAnchorButton;
        if (showAnchorButton) {
            setShowMenu(false);
        }
    }

    private final PropertyChangeListener activePcLisener = (PropertyChangeEvent evt) -> {
        if (activePage != null) {
            activePage.setActived(false);
            remove(activePage);
        }
        activePage = (J7CTabPage) evt.getOldValue();
        add(activePage, BorderLayout.CENTER);
        updateUI();
    };

    private void resetMenu() {
        if (!showMenu) {
            buttonPane.remove(menuButton);
            buttonPane.updateUI();
        } else {
            buttonPane.add(menuButton, 0);
            buttonPane.updateUI();
        }
    }

    private class TabButtonAction implements ActionListener {

        J7CTabPage page;

        public TabButtonAction(J7CTabPage page) {
            this.page = page;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!page.isActived()) {
                page.setActived(true);
            }
        }
    };

    public J7CTabPage addTabPage(String title) {
        J7CTabPage page = new J7CTabPage(title);
        page.addPropertyChangeListener("activeChanged", activePcLisener);
        pages.add(page);
        if (pages.size() == 1) {
            page.setActived(true);
        }
        //
        buttonPane.add(page.getTitlaButton());
        page.getTitlaButton().addActionListener(new TabButtonAction(page));
        return page;
    }

    public J7CTabPage addTabPage(String title, Icon icon) {
        J7CTabPage page = addTabPage(title);
        page.getTitlaButton().setIcon(icon);
        return page;
    }

    public J7CTabPage getPage(int index) {
        return pages.get(index);
    }

    public J7CPopeMenu getPopeMenu() {
        return popeMenu;
    }

    private class TabButtonPane extends JPanel {

        public TabButtonPane() {
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 2);
            setLayout(layout);
            setBorder(javax.swing.BorderFactory.createEmptyBorder(-4, -5, 0, 0));
            setPreferredSize(new Dimension(100, 37));
            setBackground(COLOR_PANEL_BACKGROUND);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(186, 201, 219));
            //底部的线条
            g.drawLine(0, this.getHeight() - 2, this.getWidth(), this.getHeight() - 2);
            //白线，立体效果
            g.setColor(Color.WHITE);
            g.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
        }

        @Override
        public Insets getInsets() {
            if (showAnchorButton) {
                return new Insets(5, 200, 0, 0);
            } else {
                return new Insets(5, 0, 0, 0);
            }
        }

    }

}
