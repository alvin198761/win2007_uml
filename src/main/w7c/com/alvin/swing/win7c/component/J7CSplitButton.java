/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import com.alvin.swing.win7c.ui.I7CColor;
import static com.alvin.swing.win7c.ui.I7CColor.FONT_UI_TEXT_BUTTON;
import com.alvin.swing.win7c.ui.J7CSplitButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.Icon;

/**
 *
 * @author Administrator
 */
public class J7CSplitButton extends J7CToolbarButton {

    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    //
    protected int orientation = X_AXIS;
    //
    protected ActionListener rightActionListener;

    public J7CSplitButton() {
    }

    public J7CSplitButton(Action a) {
        super(a);
    }

    public J7CSplitButton(Icon icon) {
        super(icon);
    }

    public J7CSplitButton(String text) {
        super(text);
    }

    public J7CSplitButton(String text, Icon icon) {
        super(text, icon);
    }

    public int getOrientation() {
        return orientation;
    }

    @Override
    protected void initUI() {
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        //
        setFont(FONT_UI_TEXT_BUTTON);
        setForeground(I7CColor.COLOR_UI_TEXT);
        setUI(new J7CSplitButtonUI());
    }

    public ActionListener getRightAction() {
        if (rightActionListener != null) {
            return rightActionListener;
        }
        return (ActionEvent e) -> {
        };
    }

    public void setRightAction(ActionListener rightAction) {
        this.rightActionListener = rightAction;
    }

}
