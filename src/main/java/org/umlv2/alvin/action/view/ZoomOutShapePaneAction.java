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
public class ZoomOutShapePaneAction extends BaseAbstractAction {

    public ZoomOutShapePaneAction() {
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_SUBTRACT);
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/zoomout.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl SUBTRACT"));
        putValue(NAME, "缩小");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double sacle = UMLCore.getMainFrame().getDrawPane().getScale();
        UMLCore.getMainFrame().getDrawPane().setScale(sacle - .1);
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
        return UMLCore.getMainFrame().getDrawPane().getScale() > .5
                && !UMLCore.getMainFrame().getDrawPane().getShapes().isEmpty();
    }

}
