/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.ui;

import com.alvin.swing.win7c.component.J7CTabedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.umlv2.alvin.ui.comp.ScaleCombbox;
import org.umlv2.alvin.ui.comp.drag.ShapeDragManager;
import org.umlv2.alvin.ui.drawpane.DrawPane;
import org.umlv2.alvin.bean.ProjectBean;
import org.umlv2.alvin.core.UMLCore;

/**
 *
 * @author Administrator
 */
public class UMLFrame extends JFrame {

    private JPanel defaultPanel = new JPanel();
    private DrawPane drawPane;
    private ShapeDragManager dragManager;
    private ScaleCombbox scaleCombbox = new ScaleCombbox();

    public UMLFrame() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/logo.gif")));
        setTitle("Swing UML" + "-" + UMLCore.version);
        //tab 面板
        J7CTabedPane tabPage = new TopPagePane(this);
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(tabPage, BorderLayout.NORTH);
        //操作面板
        defaultPanel.setBackground(Color.gray);
        defaultPanel.setLayout(new BorderLayout());
        panel.add(defaultPanel, BorderLayout.CENTER);

        setSize(1200, 700);
        setLocationRelativeTo(null);
    }

    public void reInitProject(ProjectBean projectBean, JSplitPane pane) {
        defaultPanel.removeAll();
        defaultPanel.add(pane);
        defaultPanel.updateUI();
    }

    public DrawPane getDrawPane() {
        return drawPane;
    }

    public void setDrawPane(DrawPane drawPane) {
        this.drawPane = drawPane;
    }

    public ShapeDragManager getDragManager() {
        return dragManager;
    }

    public void setDragManager(ShapeDragManager dragManager) {
        this.dragManager = dragManager;
    }

    public ScaleCombbox getScaleCombbox() {
        return this.scaleCombbox;
    }
}
