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
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class OpenShapeFileAction extends BaseAbstractAction {

    public OpenShapeFileAction() {
        super("打开");
        putValue(Action.SMALL_ICON, new ImageIcon(this.getClass().getResource("/open.png")));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.smm File", "smm");
        jfc.setFileFilter(filter);
        //
        int returnVal = jfc.showOpenDialog(UMLCore.getMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            Application.contentPane.remove(Application.defaultPanel);
//            Application.contentPane.add(Application.mainSplitPanel);
//            Application.contentPane.updateUI();
//            Application.removeAllShapes();
//            SystemFileUtil.analyzerFile(f);
//
//            operStatus = OPER_NONE;
//            SMMSystem.undoManager.reset();
//            Application.currentSelectShape = null;
//            ActionManager.firePropertyChangeAllAction();
//            Application.drawPanel.setCursor(Cursor.getDefaultCursor());
//            SMMSystem.chanageFrameTitle();
        }
    }

}
