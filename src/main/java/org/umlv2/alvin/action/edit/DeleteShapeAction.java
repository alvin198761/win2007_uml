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
import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class DeleteShapeAction extends BaseAbstractAction {

    public DeleteShapeAction() {
//        putValue(NAME, "删除");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_DELETE);
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/end.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
        putValue(Action.SHORT_DESCRIPTION, "删除");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (UMLCore.getMainFrame().getDrawPane().isSelectAll()) {
            UMLCore.getMainFrame().getDrawPane().getShapes().clear();
        } else {
            BaseDataShape shape = UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape();
            UMLCore.getMainFrame().getDrawPane().getShapes().remove(shape);
        }
        UMLCore.getMainFrame().getDrawPane().repaint();
    }

    @Override
    public boolean isEnabled() {
        if (UMLCore.getMainFrame() == null) {
            return false;
        }
        if (UMLCore.getMainFrame().getDrawPane() == null) {
            return false;
        }
        return !UMLCore.getMainFrame().getDrawPane().getShapes().isEmpty() && (UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape() != null || UMLCore.getMainFrame().getDrawPane().isSelectAll());
    }

}
