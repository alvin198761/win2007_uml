package org.umlv2.alvin.ui.drawpane;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;
import org.umlv2.alvin.sys.ObjectUtil;

/**
 *
 */
@SuppressWarnings("unchecked")
public class DrawPaneUndoRedoEdit extends AbstractUndoableEdit {

    private static final long serialVersionUID = 1L;
    private static int index = 0;
    protected DrawPane panel;
    /**
     */
    protected String allShapes;
    /**
     */
    protected String backAllShapes;

    public DrawPaneUndoRedoEdit(DrawPane panel) {
        this.panel = panel;
        try {
            allShapes = writeShapes(UMLCore.getMainFrame().getDrawPane().getShapes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void undo() {
        super.undo();
        try {
            backAllShapes = writeShapes(UMLCore.getMainFrame().getDrawPane().getShapes());
            doExecute(readShapes(allShapes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void redo() {
        super.redo();
        doExecute(readShapes(backAllShapes));
    }

    private void doExecute(List<BaseDataShape> shapes) {
        UMLCore.getMainFrame().getDrawPane().removeAllShapes();
        for (BaseDataShape shape : shapes) {
            UMLCore.getMainFrame().getDrawPane().addShape(shape);
        }
        Application.repaint();
    }

    private String writeShapes(List<BaseDataShape> shapes) {
        String path = System.getProperty("java.io.tmpdir").concat(index++ + "");
        File file = new File(path);
        try {
            ObjectUtil.writeObj(shapes, file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    private List<BaseDataShape> readShapes(String path) {
        List<BaseDataShape> shapes = null;
        try {
            shapes = (List<BaseDataShape>) ObjectUtil.readObj(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            shapes = new LinkedList<BaseDataShape>();
        }
        return shapes;
    }
}
