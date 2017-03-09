/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import com.alvin.swing.win7c.ui.I7CColor;
import static com.alvin.swing.win7c.ui.I7CColor.FONT_UI_TEXT_BUTTON;
import com.alvin.swing.win7c.ui.J7CToolbarToggerButtonUI;
import java.awt.Graphics;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JToggleButton;

/**
 *
 * @author Administrator
 */
public class J7CToolbarToggerButton extends JToggleButton implements I7CColor {

    protected boolean mousePressd = false;
    protected boolean mouseEnterd = false;
    protected float alpha = 1f;//不透明
    protected int borderType = FRAME_NONE;

    //
    public static int FRAME_NONE = 0;
    public static int FRAME_RIGHT = 1;
    public static int FRAME_AROUND = 4;

    protected boolean paintSuper = true;

    public J7CToolbarToggerButton() {
        initUI();
    }

    public J7CToolbarToggerButton(Action a) {
        super(a);
        initUI();
    }

    public J7CToolbarToggerButton(Icon icon) {
        super(icon);
        initUI();
    }

    public J7CToolbarToggerButton(String text) {
        super(text);
        initUI();
    }

    public J7CToolbarToggerButton(String text, Icon icon) {
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
        setUI(new J7CToolbarToggerButtonUI());
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
