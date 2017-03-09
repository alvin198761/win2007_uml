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
 * 放大
 *
 * @author Administrator
 */
public class ZoomInShapePaneAction extends BaseAbstractAction {

    public ZoomInShapePaneAction() {
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_ADD);
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/zoomin.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl ADD"));
        putValue(NAME, "放大");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double sacle = UMLCore.getMainFrame().getDrawPane().getScale();
        UMLCore.getMainFrame().getDrawPane().setScale(sacle + .2);
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
        return UMLCore.getMainFrame().getDrawPane().getScale() < 4
                && !UMLCore.getMainFrame().getDrawPane().getShapes().isEmpty();
    }

}
