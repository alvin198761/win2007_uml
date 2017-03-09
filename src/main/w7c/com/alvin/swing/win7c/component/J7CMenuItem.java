/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import java.awt.Insets;
import javax.swing.Action;
import javax.swing.Icon;

/**
 *
 * @author Administrator
 */
public class J7CMenuItem extends J7CToolbarButton {

    public J7CMenuItem() {
    }

    public J7CMenuItem(Action a) {
        super(a);
    }

    public J7CMenuItem(Icon icon) {
        super(icon);
    }

    public J7CMenuItem(String text) {
        super(text);
    }

    public J7CMenuItem(String text, Icon icon) {
        super(text, icon);
    }

    @Override
    public Insets getInsets() {
        Insets inset = super.getInsets(); //To change body of generated methods, choose Tools | Templates.
        return new Insets(inset.top, 20, inset.bottom, inset.right);
    }

    @Override
    protected void initUI() {
        super.initUI(); //To change body of generated methods, choose Tools | Templates.
        setHorizontalAlignment(LEFT);
        setIconTextGap(10);
    }

}
