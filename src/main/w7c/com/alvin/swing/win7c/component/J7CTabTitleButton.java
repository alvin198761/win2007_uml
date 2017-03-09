/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import com.alvin.swing.win7c.ui.I7CColor;
import static com.alvin.swing.win7c.ui.I7CColor.FONT_UI_TEXT_BUTTON;
import com.alvin.swing.win7c.ui.J7CTabTitleButtonUI;
import java.awt.Dimension;

/**
 *
 * @author Administrator
 */
public class J7CTabTitleButton extends J7CToolbarButton {

    private J7CTabPage page;

    public J7CTabTitleButton(String text, J7CTabPage page) {
        super(text);
        paintSuper = false;
        this.page = page;
        this.setPreferredSize(new Dimension(text.length() * FONT_UI_TEXT.getSize() + 30, 29));
        setUI(new J7CTabTitleButtonUI(page));
    }

    public void paintSelected() {
        firePropertyChange("paintSelected", true, false);
    }

    public void paintUnSelected() {
        firePropertyChange("paintUnSelected", true, false);
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
    }

}
