/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import static com.alvin.swing.win7c.ui.I7CColor.COLOR_PANEL_BACKGROUND;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class J7CTabPage extends JPanel {

    private J7CTabTitleButton titlaButton;
    private boolean actived;

    public J7CTabPage(String title) {
        this.setBackground(Color.white);
        titlaButton = new J7CTabTitleButton(title, this);
        setLayout(new FlowLayout(FlowLayout.LEFT, 1, 3));
    }

    public J7CTabPage(String title, Icon icon) {
        this(title);
        this.titlaButton.setIcon(icon);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, 40, COLOR_PANEL_BACKGROUND));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(new Color(186, 201, 219));
        g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
        if (actived) {
            firePropertyChange("activeChanged", this, actived);
            titlaButton.paintSelected();
        } else {
            titlaButton.paintUnSelected();
        }
    }

    public J7CTabTitleButton getTitlaButton() {
        return titlaButton;
    }

}
