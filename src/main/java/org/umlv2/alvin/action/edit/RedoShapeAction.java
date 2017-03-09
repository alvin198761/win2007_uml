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
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class RedoShapeAction extends BaseAbstractAction {

    public RedoShapeAction() {
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/redo.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
        putValue(Action.SHORT_DESCRIPTION, "恢复");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UMLCore.getMainFrame().getDrawPane().getUndoManager().redo();
    }

    @Override
    public boolean isEnabled() {
         if(UMLCore.getMainFrame()== null){
            return false;
        }
        if(UMLCore.getMainFrame().getDrawPane()== null){
            return false;
        }
        return UMLCore.getMainFrame().getDrawPane().getUndoManager().canRedo();
    }

}
