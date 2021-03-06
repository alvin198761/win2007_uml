/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.edit;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import org.umlv2.alvin.action.ActionManager;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class CutShapeAction extends BaseAbstractAction {

    public CutShapeAction() {
//        putValue(NAME, "剪切");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_T);
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/cut.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl X"));
        putValue(Action.SHORT_DESCRIPTION, "剪切");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UMLCore.getClipboard().cut();
        ActionManager.fireActionEnabled();
    }

    @Override
    public boolean isEnabled() {
         if (UMLCore.getMainFrame() == null) {
            return false;
        }
        if (UMLCore.getMainFrame().getDrawPane() == null) {
            return false;
        }
        return UMLCore.getClipboard().canCut();
    }

}
