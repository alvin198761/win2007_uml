/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.edit;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;

/**
 *
 * @author Administrator
 */
public class ArrowMouseAction extends BaseAbstractAction {

    public ArrowMouseAction() {
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/arrow.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl L"));
        putValue(Action.SHORT_DESCRIPTION, "通用箭头");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_DRAWLINE);
        UMLCore.getMainFrame().getDrawPane().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public boolean isEnabled() {
        return Application.saveType != null;
    }

}
