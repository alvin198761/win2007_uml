/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.component;

import com.alvin.swing.win7c.ui.icon.EllipseIcon;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class J7CAnchorButton extends J7CToolbarButton {

    public J7CAnchorButton(ImageIcon imageIcon) {
        Icon icon = new EllipseIcon(imageIcon.getImage(), new Color(255, 255, 255, 50));
        setIcon(icon);
        Icon rollIcon = new EllipseIcon(imageIcon.getImage(), c1_up);
        setRolloverIcon(rollIcon);
        Icon pressIcon = new EllipseIcon(imageIcon.getImage(), c1_up.darker());
        setPressedIcon(pressIcon);
    }

}
