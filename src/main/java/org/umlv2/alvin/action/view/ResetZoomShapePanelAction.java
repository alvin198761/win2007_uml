/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.view;

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
public class ResetZoomShapePanelAction extends BaseAbstractAction {

    public ResetZoomShapePanelAction() {
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_I);
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/zoomback.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
        putValue(NAME, "重置");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UMLCore.getMainFrame().getDrawPane().setScale(1);
        UMLCore.getMainFrame().getDrawPane().reDraw();
        UMLCore.getMainFrame().getDrawPane().updateUI();
        ActionManager.getAction(ZoomInShapePaneAction.class).changeEnabledProperty();
        ActionManager.getAction(ZoomOutShapePaneAction.class).changeEnabledProperty();
        ActionManager.getAction(ResetZoomShapePanelAction.class).changeEnabledProperty();
    }

    @Override
    public boolean isEnabled() {
        if (UMLCore.getMainFrame() == null) {
            return false;
        }
        if (UMLCore.getMainFrame().getDrawPane() == null) {
            return false;
        }
        return UMLCore.getMainFrame().getDrawPane().getScale() != 1;
    }

}
