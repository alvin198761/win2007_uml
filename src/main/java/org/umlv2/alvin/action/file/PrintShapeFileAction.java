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
public class PrintShapeFileAction extends BaseAbstractAction {

    public PrintShapeFileAction() {
        putValue(NAME, "打印");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/print.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl p"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
