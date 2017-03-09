package org.umlv2.alvin.ui.drawpane;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.LinkedList;
import java.util.List;

import javax.activation.DataHandler;
import org.umlv2.alvin.bean.shape.BaseShape;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;
import org.umlv2.alvin.sys.ObjectUtil;

public class DrawPaneClipboard {

    /**
     * 剪贴板
     */
    private Clipboard board = new Clipboard("DrawPanel");
    /**
     *
     */
    public static DataFlavor SHAPE_LIST_FLAVOR;

    static {
        try {
            SHAPE_LIST_FLAVOR = new DataFlavor(
                    "application/x-java-serialized-object; class = java.util.LinkedList");
        } catch (Exception e) {
            System.out.print("剪贴板初始化失败");
        }
    }

    public DrawPaneClipboard() {
    }

    public void copy() {
        DataHandler data = new DataHandler(copyBaseDataShapeList(UMLCore.getMainFrame().getDrawPane().getAllCopyShape(), 0),
                "application/x-java-serialized-object; class = java.util.ArrayList");
        board.setContents(data, null);
    }

    /**
     *
     *
     * @param list
     * @return
     */
    private List<BaseDataShape> copyBaseDataShapeList(List<BaseDataShape> list,
            int trans) {
        List<BaseDataShape> result = new LinkedList<>();
        for (BaseDataShape shape : list) {
            BaseDataShape copyShape;
            try {
                copyShape = (BaseDataShape) ObjectUtil.cloneObject(shape);
                copyShape.setX(shape.getX() + trans);
                result.add(copyShape);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public void paste() {
        Transferable value = board.getContents(SHAPE_LIST_FLAVOR);
        if (value == null) {
            return;
        }
        try {
            if (!(value.getTransferData(SHAPE_LIST_FLAVOR) instanceof LinkedList)) {
                return;
            }
            // undoredo
            UMLCore.getMainFrame().getDrawPane().getUndoManager().post(new DrawPaneUndoRedoEdit(UMLCore.getMainFrame().getDrawPane()));
            // 
            List<BaseDataShape> list = (LinkedList<BaseDataShape>) value
                    .getTransferData(SHAPE_LIST_FLAVOR);
            list = (LinkedList<BaseDataShape>) copyBaseDataShapeList(list, 15);

            //  
            for (BaseShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
                shape.setSelect(false);
            }
            // 
            UMLCore.getMainFrame().getDrawPane().clearSelect();
            UMLCore.getMainFrame().getDrawPane().reDraw();
            UMLCore.getMainFrame().getDrawPane().addDraw(list);
            copy();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void cut() {
        copy();
        UMLCore.getMainFrame().getDrawPane().removeSelectShape();
        Application.repaint();
    }

    public boolean canCopy() {
        for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
            if (shape.canCopy()) {
                return true;
            }
        }
        return false;
    }

    public boolean canPaste() {
        Transferable value = board.getContents(SHAPE_LIST_FLAVOR);
        if (value != null) {
            try {
                return (value.getTransferData(SHAPE_LIST_FLAVOR) instanceof LinkedList);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean canCut() {
        return canCopy();
    }

}
