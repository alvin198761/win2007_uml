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
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class ExitSysAction extends BaseAbstractAction {

    public ExitSysAction() {
        super("退出");
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/end.png")));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        int res = JOptionPane.showConfirmDialog(UMLCore.getMainFrame(), "你确定要退出吗？", "询问", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(res == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

}
