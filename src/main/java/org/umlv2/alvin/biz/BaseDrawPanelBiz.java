package org.umlv2.alvin.biz;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.umlv2.alvin.bean.shape.ShapeHelper;
import org.umlv2.alvin.bean.shape.ctrl.CtrlResizeShape;
import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.action.ActionManager;
import org.umlv2.alvin.action.BaseAbstractAction;
import org.umlv2.alvin.action.view.ZoomInShapePaneAction;
import org.umlv2.alvin.action.view.ZoomOutShapePaneAction;
import org.umlv2.alvin.biz.active.ActiveDrawPanelBiz;
import org.umlv2.alvin.biz.seri.SeriDrawPanelBiz;
import org.umlv2.alvin.biz.usecase.UseCaseDrawPanelBiz;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;

/**
 * �
 *
 * @author Administrator
 *
 */
public abstract class BaseDrawPanelBiz extends BaseBiz {

    public static BaseDrawPanelBiz createInstance(String type) {
        switch (type) {
            case "0":
                return new ActiveDrawPanelBiz();
            case "1":
                return new UseCaseDrawPanelBiz();
            case "2":
                return new SeriDrawPanelBiz();
        }
        return null;
    }

    protected Point2D startPoint = new Point2D.Double();
    protected long startTime;
    protected Point2D mousePoint = new Point2D.Double();

    public BaseDrawPanelBiz() {
    }

    public void mouseEntered(MouseEvent e) {
        UMLCore.getMainFrame().getDrawPane().requestFocus();
    }

    public void mouseExited(MouseEvent e) {
        UMLCore.getMainFrame().getDrawPane().requestFocus(false);
    }

    protected BaseDataShape getLineHoverShape() {
        for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
            if (shape.isLineHover()) {
                return shape;
            }
        }
        return null;
    }

    public void mouseMoved(MouseEvent e) {
        if (Application.OPER_DRAWLINE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
                shape.containsLineHover(e.getPoint());
            }
        } else {
            if (e.isMetaDown()) {
                return;
            }
            if (UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape() == null) {
                UMLCore.getMainFrame().getDrawPane().setCursor(Cursor.getDefaultCursor());
                return;
            }
            Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
            Point2D p = new Point2D.Double();
            p.setLocation(e.getPoint().getX() - rect.getX(), e.getPoint().getY()
                    - rect.getY());
            for (BaseDataShape com : UMLCore.getMainFrame().getDrawPane().getShapes()) {
                if (!com.isSelect()) {
                    continue;
                }
                CtrlResizeShape resize = com.contansCtrlBox(p);
                if (resize == null) {
                    continue;
                }
                ShapeHelper.setCursor(UMLCore.getMainFrame().getDrawPane(), resize.getWay());
                return;
            }
            UMLCore.getMainFrame().getDrawPane().setCursor(Cursor.getDefaultCursor());
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        // �� 
        if (!e.isControlDown()) {
            return;
        }
        double scale = UMLCore.getMainFrame().getDrawPane().getScale();
        if (scale <= .5 && scale >= 2) {
            return;
        }
        int value = e.getWheelRotation();
        if (value == 1) {
            BaseAbstractAction action = ActionManager.getAction(ZoomInShapePaneAction.class);
            if (action.isEnabled()) {
                action.actionPerformed(null);
            }
        }
        if (value == -1) {
            BaseAbstractAction action = ActionManager.getAction(ZoomOutShapePaneAction.class);
            if (action.isEnabled()) {
                ActionManager.getAction(ZoomOutShapePaneAction.class).actionPerformed(
                        null);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape() != null
                && UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape().isEditable()) {
//            ActionManager.getAction(PropertyAction.class).actionPerformed(null);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (e.isMetaDown()) {
            return;
        }
        if (System.currentTimeMillis() - startTime < 100) {
            // � 
            return;
        }
        startTime = System.currentTimeMillis();
        do {
            Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
            if (Application.OPER_DRAWLINE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
                drawLineDrag(e);
                break;
            }
            if (Application.OPER_RESIZE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
                //  
                Point2D p = new Point2D.Double(e.getPoint().getX()
                        - rect.getX(), e.getPoint().getY() - rect.getY());
                UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape().contrl(p);
                // 
                break;
            }
            if (Application.OPER_DRAWSELECTBOX.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
                double x = Math.min(startPoint.getX(), e.getPoint().getX())
                        - rect.getX();
                double y = Math.min(startPoint.getY(), e.getPoint().getY())
                        - rect.getY();
                double w = Math.abs(startPoint.getX() - e.getPoint().getX());
                double h = Math.abs(startPoint.getY() - e.getPoint().getY());
                Application.selectBox.setRect(x, y, w, h);
                break;
            }
        } while (false);
        Application.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if (e.isMetaDown()) {
//            return;
//        }
//        Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
//        mousePoint.setLocation(e.getPoint().getX() - rect.getX(), e.getPoint()
//                .getY() - rect.getY());
//        startTime = System.currentTimeMillis();
//        // 实现多选
//        if (e.isControlDown()) {
//            Application.operStatus = Application.OPER_MUILT_SELECT;
////            Log.info("多选");
//            List<BaseDataShape> list = Application.shapes;
//            Iterator<BaseDataShape> shapes = list.iterator();
//            BaseDataShape shape = null;
//            // 讲选中的控件放入列表
//            while (shapes.hasNext()) {
//                shape = shapes.next();
//                if (shape.isSelect()) {
//                    continue;
//                }
//                shape.setSelect(shape.contains(mousePoint));
//            }
//            Application.currentSelectShape = shape;
//            Application.repaint();
//            return;
//        }
//        // 如果是多选状态，先检查有没有点击到已经选中的控件
//        if (Application.OPER_MUILT_SELECT.equals(Application.operStatus)) {
//            // //System.out.println(this.editor.getOperStatus()
//            // +" eeeeeeeeeeeeeeeeeeee");
//            List<BaseDataShape> list = Application.shapes;
//            Iterator<BaseDataShape> shapes = list.iterator();
//            BaseDataShape shape = null;
//            // 判断当前点击的控件有没有在原来的列表中存在
//            while (shapes.hasNext()) {
//                shape = shapes.next();
//                if (!shape.contains(mousePoint)) {
//                    continue;
//                }
//                Application.currentSelectShape = shape;
//                Application.repaint();
////                if (Application.currentSelectShape.contains(shape)) {
////                    return;
////                } else {
////                    Application.clearSelect();
////                    break;
////                }
//            }
//        }
//        // //System.out.println(this.editor.getOperStatus() +" xxxxxxxxxxxxx ");
//        // 有没有点击到当前控制点上
//        do {
//            if (Application.currentResizeShape == null) {
//                break;
//            }
//            if (!Application.currentResizeShape.contains(mousePoint)) {
//                Application.currentResizeShape = null;
//                break;
//            }
//            // 改变控件大小的状态
//            Application.operStatus = Application.OPER_RESIZE;
//            // Log.info("改变大小");
//            return;
//        } while (false);
//        // //System.out.println(this.editor.getOperStatus()
//        // +" yyyyyyyyyyyyyyyyyyyy ");
//        // 有没有点击到当前的选中的图形上
//        do {
//            if (Application.currentSelectShape == null) {
//                break;
//            }
//            Application.currentResizeShape = Application.currentSelectShape.contansCtrlBox(mousePoint);
//            if (Application.currentResizeShape != null) {
//                Application.operStatus = Application.OPER_RESIZE;
//                return;
//            }
//            if (!Application.currentSelectShape.contains(mousePoint)) {
//                Application.currentSelectShape = null;
//                break;
//            }
//            // Log.info("选中图形");
//            Application.currentSelectShape.setSelect(true);
//            Application.repaint();
//            Application.operStatus = Application.OPER_DRAGSHAPE;
//            return;
//        } while (false);
//        //System.out.println("1111111");
//        Application.operStatus = Application.OPER_NONE;
//        // Log.info("重新选中");
//        // 都没有选中 就先清空所有的图形选中状态
//        List<BaseDataShape> list = Application.shapes;
//        Application.clearSelect();
////        this.editor.firePropertyChange(
////                IDrawEditable.firePropertyChangeEditAction, true, false);
//        Iterator<BaseDataShape> shapes = list.iterator();
//        BaseDataShape shape = null;
//        while (shapes.hasNext()) {
//            shape = shapes.next();
//            shape.setSelect(shape.contains(mousePoint));
//            if (shape.isSelect()) {
//                Application.currentSelectShape = shape;
//                Application.repaint();
//                if (!CenterPanelManager.isSelectedNode(shape)) {
//                    AndroidPageContainer page = (AndroidPageContainer) Application
//                            .getPageByComponent((BaseAndroidComponent) shape);
//                    ProjectEntity project = Application.getProjectByPage(page);
//                    TreePath treePath = new TreePath(new Object[]{
//                        Application.treeModel.getRoot(), project, page,
//                        shape
//
//                    });
//                    CenterPanelManager.selectNode(treePath);
//                }
//                // ActionManager.firePropertyChangeEditAction();
//                //System.out.println("2222222");
//                return;
//            }
//        }
//        // 一个都没选中 开始画选择框
//        startPoint.setLocation(e.getPoint().getX(), e.getPoint().getY());
//        this.editor.setOperStatus(Application.OPER_DRAWSELECTBOX);
//        this.editor.clearSelect();
//        // ActionManager.firePropertyChangeEditAction();
//        //System.out.println("333333");
        if (e.isMetaDown()) {
            return;
        }
        if (e.isPopupTrigger()) {
            return;
        }
        Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
        mousePoint.setLocation(e.getPoint().getX() - rect.getX(), e.getPoint()
                .getY() - rect.getY());
        startTime = System.currentTimeMillis();
        // � 
        do {
            if (UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape() == null) {
                break;
            }
            if (!UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape().contains(mousePoint)) {
                UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(null);
                break;
            }
            // � 
            UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_RESIZE);
            return;
        } while (false);
        //  
        do {
            if (UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape() == null) {
                break;
            }
            UMLCore.getMainFrame().getDrawPane().setCurrentResizeShape(UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape()
                    .contansCtrlBox(mousePoint));
            if (UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape() != null) {
                UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_RESIZE);
                return;
            }
            if (!UMLCore.getMainFrame().getDrawPane().getCurrentSelectShape().contains(mousePoint)) {
                UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(null);
                break;
            }

            UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_DRAGSHAPE);
            ActionManager.fireActionEnabled();
            return;
        } while (false);
        if (UMLCore.getMainFrame().getDrawPane().getOperStatus() == null ? Application.OPER_DRAWLINE == null : UMLCore.getMainFrame().getDrawPane().getOperStatus().equals(Application.OPER_DRAWLINE)) {
            drawLineStart(e);
            return;
        }
        UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_NONE);

        LinkedList<BaseDataShape> list = UMLCore.getMainFrame().getDrawPane().getShapes();
        UMLCore.getMainFrame().getDrawPane().clearSelect();
        BaseDataShape shape = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            shape = list.get(i);
            shape.setSelect(shape.contains(mousePoint));
            if (!shape.isSelect()) {
                continue;
            }
            UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(shape);
            Application.repaint();
            ActionManager.fireActionEnabled();
            return;
        }
        // 
        startPoint.setLocation(e.getPoint().getX(), e.getPoint().getY());
        UMLCore.getMainFrame().getDrawPane().setOperStatus(Application.OPER_DRAWSELECTBOX);
        ActionManager.fireActionEnabled();
    }

    public void drop(DropTargetDropEvent dtde) {

        try {
            // �����ļ�������
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                List<?> list = (List<?>) (dtde.getTransferable()
                        .getTransferData(DataFlavor.javaFileListFlavor));
                Iterator<?> iterator = list.iterator();
                while (iterator.hasNext()) {
                    File f = (File) iterator.next();
                    System.out.println(f.getAbsolutePath());
                }
                dtde.dropComplete(true);
            } else {
                dtde.rejectDrop();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            ActionManager.getEditPopeMenu().show(e.getComponent(), e.getX(), e.getY());
            return;
        }
        Application.anchor.setLocation(-1, -1);
        if (Application.OPER_DRAWLINE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            drawLineFinish(e);
            return;
        }
        if (Application.OPER_RESIZE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            resizeShape(e);
            Application.repaint();
            return;
        }
        if (Application.OPER_DRAWSELECTBOX.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            if (Application.selectBox.getWidth() > -1) {
                for (BaseDataShape shape : UMLCore.getMainFrame().getDrawPane().getShapes()) {
                    shape.setSelect(shape.intersects(Application.selectBox));
                }
            }
            Application.selectBox.setRect(-1, -1, -1, -1);
            Application.repaint();
            return;
        }
    }

    /**
     *
     */
    protected abstract void resizeShape(MouseEvent e);

    /**
     *
     */
    protected abstract void drawLineStart(MouseEvent e);

    /**
     * �
     */
    protected abstract void drawLineFinish(MouseEvent e);

    /**
     * @param e
     */
    protected abstract void drawLineDrag(MouseEvent e);
}
