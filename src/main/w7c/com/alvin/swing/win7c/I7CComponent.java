/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c;

import com.alvin.swing.win7c.ui.I7CColor;
import java.awt.event.MouseListener;

/**
 *
 * @author Administrator
 */
public interface I7CComponent extends MouseListener, I7CColor {

    public void updateUI();

    public void setMouseEntered(boolean enterd);

}
