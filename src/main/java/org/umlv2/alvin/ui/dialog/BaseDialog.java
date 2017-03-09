/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.ui.dialog;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class BaseDialog extends JDialog {

    public static final int STATUS_OK = 0;
    public static final int STATUS_CANCEL = 1;
    private int dialog_status = STATUS_CANCEL;

    public BaseDialog() {
        super(UMLCore.getMainFrame(), true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                setDialog_status(STATUS_CANCEL);
            }

        });

    }

    public int getDialog_status() {
        return dialog_status;
    }

    public void setDialog_status(int dialog_status) {
        this.dialog_status = dialog_status;
    }

}
