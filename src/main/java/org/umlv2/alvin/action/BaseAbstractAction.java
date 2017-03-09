/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action;

import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 *
 * @author Administrator
 */
public abstract class BaseAbstractAction extends AbstractAction {

    public BaseAbstractAction() {
    }

    public BaseAbstractAction(String name) {
        super(name);
    }

    public BaseAbstractAction(String name, Icon icon) {
        super(name, icon);
    }

    public void changeEnabledProperty() {
        firePropertyChange("enabled", true, false);
    }

}
