/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import org.umlv2.alvin.action.edit.CopyShapeAction;
import org.umlv2.alvin.action.edit.CutShapeAction;
import org.umlv2.alvin.action.edit.PasteShapeAction;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class ActionManager {

    private static final Map<Class<? extends BaseAbstractAction>, BaseAbstractAction> actionMap = new HashMap<>();

    public static BaseAbstractAction getAction(Class<? extends BaseAbstractAction> clazz) {
        if (!actionMap.containsKey(clazz)) {
            try {
                actionMap.put(clazz, (BaseAbstractAction) clazz.newInstance());
            } catch (Exception ex) {
                Logger.getLogger(ActionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actionMap.get(clazz);
    }

    public static void fireActionEnabled() {
        actionMap.entrySet().stream().forEach((entry) -> {
            entry.getValue().changeEnabledProperty();
        });
    }

    public static JPopupMenu getEditPopeMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(getAction(CopyShapeAction.class));
        menu.add(getAction(CutShapeAction.class));
        menu.add(getAction(PasteShapeAction.class));
        return menu;
    }

}
