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
public class PasteShapeAction extends BaseAbstractAction {

    public PasteShapeAction() {
        putValue(NAME, "粘贴");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/paste.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UMLCore.getClipboard().paste();
        ActionManager.fireActionEnabled();

    }

    @Override
    public boolean isEnabled() {
        return UMLCore.getClipboard().canPaste();
    }

}
