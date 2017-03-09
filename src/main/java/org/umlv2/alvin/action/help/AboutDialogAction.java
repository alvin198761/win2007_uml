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
import org.umlv2.alvin.ui.dialog.AboutDialog;

/**
 *
 * @author Administrator
 */
public class AboutDialogAction extends BaseAbstractAction {

    public AboutDialogAction() {
        putValue(NAME, "关于");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift B"));
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/about.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutDialog dialog = new AboutDialog();
        dialog.setLocationRelativeTo(UMLCore.getMainFrame());
        dialog.setVisible(true);
    }

}
