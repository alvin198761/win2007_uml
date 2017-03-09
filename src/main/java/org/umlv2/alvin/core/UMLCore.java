/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.umlv2.alvin.bean.ProjectBean;
import org.umlv2.alvin.ui.UMLFrame;
import org.umlv2.alvin.ui.drawpane.DrawPaneClipboard;

/**
 *
 * @author Administrator
 */
public class UMLCore {

    public static final String version = "2.0";

    private static UMLFrame mainFrame;
    //
    public static ProjectBean currentProject;
    //
    private static DrawPaneClipboard clipboard;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException ex) {
            Logger.getLogger(UMLCore.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                mainFrame = new UMLFrame();
                mainFrame.setVisible(true);
            }
        });

    }

    public static UMLFrame getMainFrame() {
        return mainFrame;
    }

    public static DrawPaneClipboard getClipboard() {
        if (clipboard == null) {
            clipboard = new DrawPaneClipboard();
        }
        return clipboard;
    }

}
