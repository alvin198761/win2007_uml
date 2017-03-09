/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import org.umlv2.alvin.action.BaseAbstractAction;

/**
 *
 * @author Administrator
 */
public class SaveAsShapeFileAction extends BaseAbstractAction {

    public SaveAsShapeFileAction() {
        putValue(NAME, "另存为...");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/save.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift A"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
