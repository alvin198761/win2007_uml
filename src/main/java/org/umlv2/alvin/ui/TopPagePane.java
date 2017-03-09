/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.ui;

import static com.alvin.swing.win7c.ui.I7CColor.COLOR_TOOLBAR_FRAME;
import com.alvin.swing.win7c.component.J7CMenuItem;
import com.alvin.swing.win7c.component.J7CSplitMenuItem;
import com.alvin.swing.win7c.component.J7CTabPage;
import com.alvin.swing.win7c.component.J7CTabedPane;
import com.alvin.swing.win7c.component.J7CToolbarButton;
import com.alvin.swing.win7c.component.J7CToolbarToggerButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.umlv2.alvin.action.ActionManager;
import org.umlv2.alvin.action.edit.ArrowMouseAction;
import org.umlv2.alvin.action.edit.CopyShapeAction;
import org.umlv2.alvin.action.edit.CutShapeAction;
import org.umlv2.alvin.action.edit.DefaultMouseAction;
import org.umlv2.alvin.action.edit.DeleteShapeAction;
import org.umlv2.alvin.action.edit.PasteShapeAction;
import org.umlv2.alvin.action.edit.RedoShapeAction;
import org.umlv2.alvin.action.edit.SelectAllShapeAction;
import org.umlv2.alvin.action.edit.UndoShapeAction;
import org.umlv2.alvin.action.file.ExitSysAction;
import org.umlv2.alvin.action.file.NewFileAction;
import org.umlv2.alvin.action.file.OpenShapeFileAction;
import org.umlv2.alvin.action.file.PrintShapeFileAction;
import org.umlv2.alvin.action.file.SaveAsShapeFileAction;
import org.umlv2.alvin.action.file.SaveShapeFileAction;
import org.umlv2.alvin.action.help.AboutDialogAction;
import org.umlv2.alvin.action.help.HelpDialogAction;
import org.umlv2.alvin.action.view.ResetZoomShapePanelAction;
import org.umlv2.alvin.action.view.ShowStatusBarAction;
import org.umlv2.alvin.action.view.ZoomInShapePaneAction;
import org.umlv2.alvin.action.view.ZoomOutShapePaneAction;

/**
 *
 * @author Administrator
 */
public class TopPagePane extends J7CTabedPane {

    public TopPagePane(JFrame owner) {
        super(owner, null);
        setPreferredSize(new Dimension(owner.getWidth(), 150));
        //
        J7CTabPage startPage = this.addTabPage("开始");
        J7CTabPage viewPage = this.addTabPage("查看");
        this.setShowMenu(true);
        //
        getPopeMenu().addMenuItem(new J7CMenuItem(ActionManager.getAction(NewFileAction.class)));
        //
        getPopeMenu().addMenuItem(new J7CMenuItem(ActionManager.getAction(OpenShapeFileAction.class)));
        //
        getPopeMenu().addMenuItem(new J7CMenuItem(ActionManager.getAction(SaveShapeFileAction.class)));
        //
        getPopeMenu().addMenuItem(new J7CSplitMenuItem(ActionManager.getAction(SaveAsShapeFileAction.class)));
        //
        getPopeMenu().addMenuItem(new J7CSplitMenuItem(ActionManager.getAction(PrintShapeFileAction.class)));
        //
        getPopeMenu().addMenuItem(new J7CMenuItem(ActionManager.getAction(ExitSysAction.class)));
        //
        createStartPage(startPage);
        createViewPage(viewPage);

    }

    private void createStartPage(J7CTabPage page) {
        //
        JPanel clipboardPanel = createClipBoardPane();
        page.add(clipboardPanel);
        //
        JPanel editPanel = createEditPane();
        page.add(editPanel);
    }

    private JPanel createClipBoardPane() {
        JPanel clipboardPanel = new JPanel();
        clipboardPanel.setOpaque(false);
        clipboardPanel.setLayout(new BorderLayout(5, 5));
        //
        J7CToolbarButton paste = new J7CToolbarButton(ActionManager.getAction(PasteShapeAction.class));
        paste.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        paste.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        paste.setPreferredSize(new Dimension(60, 50));
        paste.setBorderType(J7CToolbarButton.FRAME_RIGHT);
        clipboardPanel.add(paste, BorderLayout.WEST);
        //
        JPanel clipboardPanel_1 = new JPanel();
        clipboardPanel_1.setOpaque(false);
        clipboardPanel_1.setLayout(new BoxLayout(clipboardPanel_1, BoxLayout.Y_AXIS));
        //
        J7CToolbarButton cutBtn = new J7CToolbarButton(ActionManager.getAction(CutShapeAction.class));
        cutBtn.setPreferredSize(new Dimension(45, 45));
        //
        J7CToolbarButton copyBtn = new J7CToolbarButton(ActionManager.getAction(CopyShapeAction.class));
        copyBtn.setPreferredSize(new Dimension(45, 45));
        clipboardPanel_1.add(cutBtn);
        clipboardPanel_1.add(copyBtn);
        clipboardPanel.add(clipboardPanel_1, BorderLayout.CENTER);
        JLabel lbl = new JLabel("剪贴板");
        lbl.setEnabled(false);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        clipboardPanel.add(lbl, BorderLayout.SOUTH);
        clipboardPanel.setPreferredSize(new Dimension(100, 105));
        return clipboardPanel;
    }

    private JPanel createEditPane() {
        JPanel editPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(COLOR_TOOLBAR_FRAME);
                g.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 2, 3, 3);
            }
        };
        editPanel.setOpaque(false);
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
//        //
        J7CToolbarButton undoBtn = new J7CToolbarButton(ActionManager.getAction(UndoShapeAction.class));
        undoBtn.setPreferredSize(new Dimension(45, 45));
        editPanel.add(undoBtn);
        //
        J7CToolbarButton redoBtn = new J7CToolbarButton(ActionManager.getAction(RedoShapeAction.class));
        redoBtn.setPreferredSize(new Dimension(45, 45));
        editPanel.add(redoBtn);
        //
        ButtonGroup bg = new ButtonGroup();
        J7CToolbarToggerButton lineBtn = new J7CToolbarToggerButton(ActionManager.getAction(ArrowMouseAction.class));
        lineBtn.setPreferredSize(new Dimension(45, 45));
        editPanel.add(lineBtn);
        bg.add(lineBtn);
        //
        J7CToolbarButton selectAll = new J7CToolbarButton(ActionManager.getAction(SelectAllShapeAction.class));
        selectAll.setPreferredSize(new Dimension(45, 45));
        editPanel.add(selectAll);
        //
        J7CToolbarButton delBtn = new J7CToolbarButton(ActionManager.getAction(DeleteShapeAction.class));
        delBtn.setPreferredSize(new Dimension(45, 45));
        editPanel.add(delBtn);

        //
        J7CToolbarToggerButton defaultHand = new J7CToolbarToggerButton(ActionManager.getAction(DefaultMouseAction.class));
        defaultHand.setPreferredSize(new Dimension(45, 45));
        editPanel.add(defaultHand);
        bg.add(defaultHand);
        //
        editPanel.setPreferredSize(new Dimension(160, 104));
        return editPanel;
    }

//
    private void createViewPage(J7CTabPage viewPage) {
        JPanel zoomPane = createZoomPane();
        viewPage.add(zoomPane);
        JPanel showHidePane = createShowHidePane();
        viewPage.add(showHidePane);
        //
        J7CToolbarButton helpBtn = new J7CToolbarButton(ActionManager.getAction(HelpDialogAction.class));
        helpBtn.setBorderType(J7CToolbarButton.FRAME_RIGHT);
        helpBtn.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        helpBtn.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        helpBtn.setPreferredSize(new Dimension(60, 105));
        viewPage.add(helpBtn);
        //
        J7CToolbarButton aboutBtn = new J7CToolbarButton(ActionManager.getAction(AboutDialogAction.class));
        aboutBtn.setBorderType(J7CToolbarButton.FRAME_RIGHT);
        aboutBtn.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        aboutBtn.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        aboutBtn.setPreferredSize(new Dimension(60, 105));
        viewPage.add(aboutBtn);
    }

    private JPanel createZoomPane() {
        JPanel zoomPane = new JPanel();
        zoomPane.setPreferredSize(new Dimension(180, 105));
        zoomPane.setOpaque(false);
        //
        JPanel toolPane = new JPanel();
        toolPane.setOpaque(false);
        toolPane.setLayout(new GridLayout(1, 3));
        J7CToolbarButton zoomInBtn = new J7CToolbarButton(ActionManager.getAction(ZoomInShapePaneAction.class));
        zoomInBtn.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        zoomInBtn.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        zoomInBtn.setPreferredSize(new Dimension(60, 80));
//        zoomInBtn.setFrameType(ToolbarButton.FRAME_RIGHT);
        toolPane.add(zoomInBtn);
        J7CToolbarButton zoomOutBtn = new J7CToolbarButton(ActionManager.getAction(ZoomOutShapePaneAction.class));
        zoomOutBtn.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        zoomOutBtn.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        zoomOutBtn.setPreferredSize(new Dimension(60, 80));
//        zoomOutBtn.setFrameType(ToolbarButton.FRAME_RIGHT);
        toolPane.add(zoomOutBtn);
        J7CToolbarButton zoomback = new J7CToolbarButton(ActionManager.getAction(ResetZoomShapePanelAction.class));
        zoomback.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        zoomback.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        zoomback.setPreferredSize(new Dimension(60, 80));
//        zoomback.setFrameType(ToolbarButton.FRAME_RIGHT);
        toolPane.add(zoomback);
        //
        zoomPane.setLayout(new BorderLayout());
        zoomPane.add(toolPane, BorderLayout.CENTER);
        JLabel lbl = new JLabel("缩放");
        lbl.setEnabled(false);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        zoomPane.add(lbl, BorderLayout.SOUTH);
        return zoomPane;
    }

    private JPanel createShowHidePane() {
        JPanel showHidePane = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(COLOR_TOOLBAR_FRAME);
                g.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 2, 3, 3);
            }
        };
        showHidePane.setPreferredSize(new Dimension(85, 105));
        showHidePane.setOpaque(false);
        JPanel toolpJPanel = new JPanel();
        toolpJPanel.setOpaque(false);
        toolpJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        J7CToolbarButton showStaButton = new J7CToolbarButton(ActionManager.getAction(ShowStatusBarAction.class));
        showStaButton.setVerticalTextPosition(J7CToolbarButton.BOTTOM);
        showStaButton.setHorizontalTextPosition(J7CToolbarButton.CENTER);
        showStaButton.setPreferredSize(new Dimension(75, 100));
        toolpJPanel.add(showStaButton);
        //
        showHidePane.setLayout(new BorderLayout());
        showHidePane.add(toolpJPanel, BorderLayout.CENTER);
        JLabel lbl = new JLabel("隐藏和显示");
        lbl.setEnabled(false);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        showHidePane.add(lbl, BorderLayout.SOUTH);
        //
        return showHidePane;
    }
}
