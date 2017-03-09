///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.umlv2.alvin.ui;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JSplitPane;
//import org.umlv2.alvin.ui.comp.JTitlePanel;
//import org.umlv2.alvin.ui.comp.drag.ShapeDragManager;
//import org.umlv2.alvin.ui.drawpane.DrawPane;
//import org.umlv2.alvin.bean.ProjectBean;
//import org.umlv2.alvin.core.UMLCore;
//import org.umlv2.alvin.sys.Application;
//
///**
// *
// * @author Administrator
// */
//public class MainSplitPane extends JSplitPane {
//
//    private DrawPane drawPanel;
//    private ShapeDragManager dragManager;
//
//    public MainSplitPane(ProjectBean project) {
//        setDividerLocation(200);
//        setDividerSize(20);
//        setBorder(null);
//        setOneTouchExpandable(true);
//        //
//        Application.shapes.clear();
//        drawPanel = new DrawPane();
//        //
//        UMLCore.getMainFrame().setDrawPane(drawPanel);
//        dragManager = new ShapeDragManager(UMLCore.getMainFrame(), drawPanel);
//        UMLCore.getMainFrame().setDragManager(dragManager);
//        //
//        JTitlePanel shapePane = new JTitlePanel();
//        setLeftComponent(shapePane);
//        setRightComponent(UMLCore.getMainFrame().getDrawPane());
//        //
//        Application.repaint();
//    }
//
//}
