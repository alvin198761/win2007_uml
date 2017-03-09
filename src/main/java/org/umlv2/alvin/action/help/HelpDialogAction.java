/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.help;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.ui.dialog.HelpDialog;

/**
 *
 * @author Administrator
 */
public class HelpDialogAction extends BaseAbstractAction {

    public HelpDialogAction() {
        putValue(NAME, "帮助");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_H);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/prop.png")));
    }

    private HelpDialog dialog;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (dialog == null) {
            dialog = new HelpDialog();
            dialog.setLocationRelativeTo(UMLCore.getMainFrame());
        }
        dialog.setVisible(true);
    }

}
