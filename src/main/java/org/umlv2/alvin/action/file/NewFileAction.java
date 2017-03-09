/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.action.file;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.bean.factory.AbstractShapeFactory;
import org.umlv2.alvin.biz.BaseDragBiz;
import org.umlv2.alvin.biz.BaseDrawPanelBiz;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;
import org.umlv2.alvin.ui.comp.JTitlePanel;
import org.umlv2.alvin.ui.comp.drag.ShapeDragManager;
import org.umlv2.alvin.ui.comp.drag.ShapeGhostGlassPane;
import org.umlv2.alvin.ui.dialog.BaseDialog;
import org.umlv2.alvin.ui.dialog.NewFileDialog;
import org.umlv2.alvin.ui.drawpane.DrawPane;

/**
 *
 * @author Administrator
 */
public class NewFileAction extends BaseAbstractAction {

    public NewFileAction() {
        putValue(NAME, "新建");
        putValue(MNEMONIC_KEY, KeyEvent.VK_N);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
        putValue(SMALL_ICON, new ImageIcon(this.getClass().getResource("/new.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NewFileDialog dialog = new NewFileDialog();
        dialog.setVisible(true);
        if (dialog.getDialog_status() == BaseDialog.STATUS_OK) {
            //1.根据类型初始化 画图板
            DrawPane drawPane = new DrawPane();
            ShapeGhostGlassPane glass = new ShapeGhostGlassPane(drawPane);
            //2.初始化 drag manage
            UMLCore.getMainFrame().setGlassPane(glass);
            ShapeDragManager dragManager = new ShapeDragManager();
            UMLCore.getMainFrame().setDragManager(dragManager);
            //3.初始化 factory
            AbstractShapeFactory shapeFactory = AbstractShapeFactory.createInstance(dialog.getProjectBean().getType());
            drawPane.setShapeFactory(shapeFactory);
            //4.初始化 业务
            // 画图处理
            BaseDrawPanelBiz drawPanelBiz = BaseDrawPanelBiz.createInstance(dialog.getProjectBean().getType());
            // 拖动处理
            BaseDragBiz dragBiz = BaseDragBiz.createInstance(dialog.getProjectBean().getType());
            drawPane.setDrawPanelBiz(drawPanelBiz);
            drawPane.setDragBiz(dragBiz);
            //5.初始化左边的面板
            JTitlePanel titlePanel = JTitlePanel.createInstance(dialog.getProjectBean().getType(), drawPane);
            //6.初始化右边的面板
            JSplitPane mainPane = createMainPane();
            mainPane.setLeftComponent(titlePanel);
            mainPane.setRightComponent(new JScrollPane(drawPane));
            //7.candrag
            dragManager.canDrag(drawPane, drawPane);
            UMLCore.getMainFrame().setDrawPane(drawPane);
            UMLCore.getMainFrame().reInitProject(dialog.getProjectBean(), mainPane);
            //
            drawPane.addMouseListener(drawPanelBiz);
            drawPane.addMouseMotionListener(drawPanelBiz);
            drawPane.addMouseWheelListener(drawPanelBiz);
            new DropTarget(drawPane, DnDConstants.ACTION_COPY_OR_MOVE, drawPanelBiz);
            //
            Application.repaint();
        }
    }

    private JSplitPane createMainPane() {
        JSplitPane pane = new JSplitPane();
        pane.setDividerLocation(200);
        pane.setDividerSize(20);
        pane.setBorder(null);
        pane.setOneTouchExpandable(true);
        return pane;
    }

}
