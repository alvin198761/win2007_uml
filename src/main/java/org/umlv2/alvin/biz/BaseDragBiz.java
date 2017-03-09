package org.umlv2.alvin.biz;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.active.line.ActiveLineShape;
import org.umlv2.alvin.biz.active.ActiveDragPanelBiz;
import org.umlv2.alvin.biz.seri.SeriDragPanelBiz;
import org.umlv2.alvin.biz.usecase.UseCaseDrageBiz;
import org.umlv2.alvin.ui.comp.drag.ShapeGhostGlassPane;
import org.umlv2.alvin.ui.drawpane.DrawPaneUndoRedoEdit;
import org.umlv2.alvin.ui.drawpane.BaseDrawComp;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;
import org.umlv2.alvin.ui.comp.CursorUtils;

/**
 * 拖动处理的父类
 *
 * @author Administrator
 *
 */
public abstract class BaseDragBiz extends BaseBiz {

    public static BaseDragBiz createInstance(String type) {
        switch (type) {
            case "0":
                return new ActiveDragPanelBiz();
            case "1":
                return new UseCaseDrageBiz();
            case "2":
                return new SeriDragPanelBiz();
        }
        return null;
    }

    protected AlphaComposite composite;
    protected BaseDataShape shape;
    protected boolean flag;
    protected long startTime;
    protected BaseDataShape dragged = null;
    protected Point location = new Point(0, 0);
    private int state;

    public static final int STATE_ACCEPT = 1;
    public static final int STATE_UNACCEPT = 2;
    public static final int STATE_NOMAR = -1;

    public BaseDragBiz() {
        // 半透明效果
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            return;
        }
        if (UMLCore.getMainFrame().getDrawPane().getCurrentResizeShape() != null) {
            return;
        }
        startTime = System.currentTimeMillis();
        flag = false;
        if (Application.OPER_DRAWLINE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            return;
        }
        shapeMouseDown(e);
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        if (e.isMetaDown()) {
            return;
        }
        if (Application.OPER_DRAWLINE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            return;
        }
        if (this.shape == null) {
            return;
        }
        if (this.shape instanceof ActiveLineShape) {
            return;
        }
        if (System.currentTimeMillis() - startTime < 80) {
            // 减少重新绘制的次数，
            return;
        }
        startTime = System.currentTimeMillis();
        flag = true;
        Component c = e.getComponent();
        if (c.equals(UMLCore.getMainFrame().getDrawPane())
                && Application.OPER_RESIZE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            this.setImage(null);
            UMLCore.getMainFrame().getGlassPane().repaint();
            return;
        }
        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        Point eventPoint = (Point) p.clone();
        SwingUtilities.convertPointFromScreen(p, (ShapeGhostGlassPane) UMLCore.getMainFrame().getGlassPane());

        // 鼠标是否在目标控件的上方
        SwingUtilities.convertPointFromScreen(eventPoint, UMLCore.getMainFrame().getDrawPane());
        int state = UMLCore.getMainFrame().getDrawPane().getImageRect().contains(eventPoint) ? STATE_ACCEPT
                : STATE_UNACCEPT;
        setState(state);
        setPoint(p);
        UMLCore.getMainFrame().getGlassPane().repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isMetaDown()) {
            return;
        }
        if (Application.OPER_DRAWLINE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            return;
        }
        UMLCore.getMainFrame().getGlassPane().setCursor(Cursor.getDefaultCursor());
        if (!flag) {
            setImage(null);
            UMLCore.getMainFrame().getGlassPane().repaint();
            UMLCore.getMainFrame().getGlassPane().setVisible(false);
            setImage(null);
            return;
        }
        UMLCore.getMainFrame().getDragManager().setDragSource(null);
        System.gc();
        final Component c = e.getComponent();
        final Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        final Point eventPoint = (Point) p.clone();
        final Rectangle2D rect = UMLCore.getMainFrame().getDrawPane().getImageRect();
        SwingUtilities.convertPointFromScreen(p, (ShapeGhostGlassPane) UMLCore.getMainFrame().getGlassPane());
        SwingUtilities.convertPointFromScreen(eventPoint, UMLCore.getMainFrame().getDrawPane());
        setPoint(p);
        UMLCore.getMainFrame().getGlassPane().repaint();
        /*
         * 移动控件有一下几种情况
         * 
         * 1、控件在原来的位置之上，没动
         * 
         * 2、控件从原来的位置移动到目标控件的位置
         * 
         * 3、控件已经在目标的位置上，然后自动在目标的位置
         * 
         * 4、控件在目标的位置上移动的其他控件上
         */
        // 还没有进入目标组件
        if ((!c.equals(UMLCore.getMainFrame().getDrawPane()) && !UMLCore.getMainFrame().getDrawPane().contains(eventPoint))
                || (!c.equals(UMLCore.getMainFrame().getDrawPane()) && UMLCore.getMainFrame().getDrawPane().contains(eventPoint))
                && !UMLCore.getMainFrame().getDrawPane().getImageRect().contains(eventPoint)) {
            new Thread(new Runnable() {

                public void run() {
                    // 没有接受
                    setState(STATE_NOMAR);
                    // 拿到组件的原始位置
                    Point cp = (Point) c.getLocationOnScreen().clone();
                    // 转成glass位置
                    SwingUtilities.convertPointFromScreen(cp, (ShapeGhostGlassPane) UMLCore.getMainFrame().getGlassPane());
                    // 当前组件在glass上的位置
                    int x = cp.x;
                    int y = cp.y;
                    // 拿到当前显示的图片的位置
                    int cx = p.x - (c.getWidth() / 2);
                    int cy = p.y - (c.getHeight() / 2);

                    int subX = (x - cx) / 10;
                    int subY = (y - cy) / 10;

                    x = p.x - (c.getWidth() / 2);
                    y = p.y - (c.getHeight() / 2);

                    for (int i = 0; i < 11; i++) {
                        x += subX;
                        y += subY;
                        if (i == 10) {
                            x = cp.x;
                            y = cp.y;
                        }
                        cx = x + (c.getWidth() / 2);
                        cy = y + (c.getHeight() / 2);
                        setPoint(new Point(cx, cy));
                        UMLCore.getMainFrame().getGlassPane().repaint();
                        try {
                            Thread.sleep(20);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    UMLCore.getMainFrame().getGlassPane().setVisible(false);
                    setImage(null);
                }
            }).start();
            return;
        }
        // 移动到目标的位置
        if (!c.equals(UMLCore.getMainFrame().getDrawPane())
                && UMLCore.getMainFrame().getDrawPane().getImageRect().contains(eventPoint)) {
            // 又多了一种情况
            // 必须在image上
            // 复制出新的组件
            UMLCore.getMainFrame().getDrawPane().getUndoManager().post(new DrawPaneUndoRedoEdit(UMLCore.getMainFrame().getDrawPane()));
            sourceToTarget(e, c, rect);

            UMLCore.getMainFrame().getDrawPane().setSaveed(false);
            return;
        }
        // 控件已经在目标的位置上，然后自动在目标的位置
        if (c.equals(UMLCore.getMainFrame().getDrawPane()) && UMLCore.getMainFrame().getDrawPane().contains(eventPoint)
                && !Application.OPER_RESIZE.equals(UMLCore.getMainFrame().getDrawPane().getOperStatus())) {
            UMLCore.getMainFrame().getDrawPane().getUndoManager().post(new DrawPaneUndoRedoEdit(UMLCore.getMainFrame().getDrawPane()));
            targetToTarget(e, c, rect);
            UMLCore.getMainFrame().getDrawPane().setSaveed(false);
            return;
        }
        // 控件在目标的位置上移动的其他控件上
        if (c.equals(UMLCore.getMainFrame().getDrawPane()) && !UMLCore.getMainFrame().getDrawPane().contains(eventPoint)) {
            // 没有接受
            setState(STATE_NOMAR);
            // 这里记录图形的
            new Thread(new Runnable() {

                public void run() {
                    BaseDataShape sourceShape = ((BaseDrawComp) c).getShape();
                    if (sourceShape == null) {
                        return;
                    }
                    // 画板在屏幕上的位置
                    // shape的位置
                    Point cp = new Point((int) (sourceShape.getX()),
                            (int) (sourceShape.getY()));
                    // 转化到屏幕
                    SwingUtilities.convertPointToScreen(cp, c);
                    // 转成glass位置
                    SwingUtilities.convertPointFromScreen(cp, (ShapeGhostGlassPane) UMLCore.getMainFrame().getGlassPane());
                    // 拿到组件的位置
                    int x = cp.x;
                    int y = cp.y;
                    // 拿到当前鼠标的位置
                    SwingUtilities.convertPointToScreen(eventPoint, c);
                    SwingUtilities
                            .convertPointFromScreen(eventPoint, (ShapeGhostGlassPane) UMLCore.getMainFrame().getGlassPane());
                    int cx = eventPoint.x;
                    int cy = eventPoint.y;

                    int subX = (x - cx) / 10;
                    int subY = (y - cy) / 10;

                    x = eventPoint.x;
                    y = eventPoint.y;

                    for (int i = 0; i < 11; i++) {
                        x += subX;
                        y += subY;

                        if (i == 10) {
                            x = cp.x;
                            y = cp.y;
                        }

                        cx = (int) (x + (shape.getW() / 2));
                        cy = (int) (y + (shape.getH() / 2));
                        setPoint(new Point(cx, cy));
                        UMLCore.getMainFrame().getGlassPane().repaint();
                        try {
                            Thread.sleep(20);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    UMLCore.getMainFrame().getGlassPane().setVisible(false);
                    setImage(null);
                }
            }).start();
            return;
        }
    }

    public void mouseMoved(MouseEvent e) {

    }

    /**
     * 从原来的组件拖到目标组件
     *
     * @param e
     * @param c
     * @param rect
     */
    protected abstract void sourceToTarget(MouseEvent e, final Component c,
            final Rectangle2D rect);

    /**
     * 目标组件拖到目标组件
     *
     * @param e
     * @param c
     * @param rect
     */
    protected abstract void targetToTarget(MouseEvent e, final Component c,
            final Rectangle2D rect);

    /**
     * 鼠标按下的操作
     *
     * @param e
     */
    protected abstract void shapeMouseDown(MouseEvent e);

    /**
     * 画图的实现
     *
     * @param g
     */
    public void paintCompent(Graphics g) {
        if (dragged == null) {
            return;
        }
        paintShape(g);
        switch (state) {
            case STATE_ACCEPT:
                UMLCore.getMainFrame().getGlassPane().setCursor(CursorUtils.getRightCursor());
                break;
            case STATE_UNACCEPT:
                UMLCore.getMainFrame().getGlassPane().setCursor(CursorUtils.getWrongCursor());
                break;
            case STATE_NOMAR:
                UMLCore.getMainFrame().getGlassPane().setCursor(Cursor.getDefaultCursor());
                break;
        }
    }

    /**
     * 图形绘制
     *
     * @param g
     */
    protected abstract void paintShape(Graphics g);

    public void setState(int state) {
        this.state = state;
    }

    public void setImage(BaseDataShape dragged) {
        this.dragged = dragged;
    }

    public void setPoint(Point location) {
        this.location = location;
    }
}
