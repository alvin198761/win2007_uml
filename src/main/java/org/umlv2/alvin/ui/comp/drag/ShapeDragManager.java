package org.umlv2.alvin.ui.comp.drag;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import org.umlv2.alvin.biz.BaseDragBiz;


import org.umlv2.alvin.ui.drawpane.DrawPane;

/**
 * 实现图形拖拽的类
 *
 * 参考swing hacks
 *
 * @author 唐植超
 *
 */
public class ShapeDragManager {

    private Component dragSource;
    private final Map<Component, BaseDragBiz> dragMap = new HashMap<>();

    public void setDragSource(Component dragSource) {
        this.dragSource = dragSource;
    }

    public ShapeDragManager() {
    }

    /**
     * 指定能拖拽的组件
     *
     *
     * @param source
     * @param drawPanel
     */
    public void canDrag(Component source, DrawPane drawPanel) {
        source.addMouseListener(drawPanel.getDragBiz());
        source.addMouseMotionListener(drawPanel.getDragBiz());
        dragSource = source;
        dragMap.put(source, drawPanel.getDragBiz());
    }

    public void removeDrag(Component source) {
        BaseDragBiz action = dragMap.get(source);
        if (action != null) {
            source.removeMouseListener(action);
            source.removeMouseMotionListener(action);
            action = null;
            System.gc();
        }
    }

    /**
     * 返回拖拽源对象
     *
     * @return
     */
    public Component getDragSource() {
        return dragSource;
    }

}
