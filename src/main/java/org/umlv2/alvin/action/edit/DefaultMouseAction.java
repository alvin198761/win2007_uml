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
import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;

/**
 *
 * @author Administrator
 */
public class DefaultMouseAction extends BaseAbstractAction {

    public DefaultMouseAction() {
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/hand.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift D"));
        putValue(Action.SHORT_DESCRIPTION, "默认鼠标");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_NONE);
        UMLCore.getMainFrame().getDrawPane().setCursor(Cursor.getDefaultCursor());
        for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
            shape.setLineHover(false);
        }
    }

    @Override
    public boolean isEnabled() {
        return Application.saveType != null;
    }

}
