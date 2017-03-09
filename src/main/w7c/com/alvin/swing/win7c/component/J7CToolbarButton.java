/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import com.alvin.swing.win7c.ui.I7CColor;
import com.alvin.swing.win7c.ui.J7CToolButtonUI;
import java.awt.Graphics;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Administrator
 */
public class J7CToolbarButton extends JButton implements I7CColor {

    protected boolean mousePressd = false;
    protected boolean mouseEnterd = false;
    protected float alpha = 1f;//不透明
    protected int borderType = FRAME_NONE;

    //
    public static int FRAME_NONE = 0;
    public static int FRAME_RIGHT = 1;
    public static int FRAME_AROUND = 4;

    protected boolean paintSuper = true;

    public J7CToolbarButton() {
        initUI();
    }

    public J7CToolbarButton(Action a) {
        super(a);
        initUI();
    }

    public J7CToolbarButton(Icon icon) {
        super(icon);
        initUI();
    }

    public J7CToolbarButton(String text) {
        super(text);
        initUI();
    }

    public J7CToolbarButton(String text, Icon icon) {
        super(text, icon);
        initUI();
    }

    protected void initUI() {
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        //
        setFont(FONT_UI_TEXT_BUTTON);
        setForeground(I7CColor.COLOR_UI_TEXT);
        setUI(new J7CToolButtonUI());
    }

    @Override
    public final void update(Graphics g) {
        this.paintComponent(g);
    }

    public int getBorderType() {
        return borderType;
    }

    public void setBorderType(int borderType) {
        this.borderType = borderType;
    }

}
