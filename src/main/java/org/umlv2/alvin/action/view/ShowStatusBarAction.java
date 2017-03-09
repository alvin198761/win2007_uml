/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.view;

import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.umlv2.alvin.action.BaseAbstractAction;

/**
 *
 * @author Administrator
 */
public class ShowStatusBarAction extends BaseAbstractAction {

    public ShowStatusBarAction() {
        putValue(NAME, "状态栏");
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/showStaus.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
