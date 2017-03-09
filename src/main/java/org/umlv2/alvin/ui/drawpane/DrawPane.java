package org.umlv2.alvin.ui.drawpane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import org.umlv2.alvin.action.ActionManager;
import org.umlv2.alvin.bean.factory.AbstractShapeFactory;
import org.umlv2.alvin.bean.shape.BaseShape;
import org.umlv2.alvin.bean.shape.ctrl.CtrlResizeShape;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.BaseLineShape;
import org.umlv2.alvin.biz.BaseDragBiz;
import org.umlv2.alvin.biz.BaseDrawPanelBiz;
import org.umlv2.alvin.sys.Application;
import static org.umlv2.alvin.sys.Application.OPER_NONE;

/**
 * 画板主界面
 */
public class DrawPane extends JPanel implements BaseDrawComp {

    private static final long serialVersionUID = 1L;

    private double scale = 1;
    private BufferedImage bufferImage;
    private Graphics2D g2d;
    //
    private double imageWidth;
    private double imageHeight;
    private double imagex = 0;
    private double imagey = 0;
    //
    private Rectangle2D imageBox = new Rectangle2D.Double();
    //
    private UndoableEditManager undoManager;
    // 图形生产的抽象工厂
    private AbstractShapeFactory shapeFactory;
    //
    // 画图处理
    private BaseDrawPanelBiz drawPanelBiz;
    // 拖动处理
    private BaseDragBiz dragBiz;
    //
    // 当前选中的图形
    private BaseDataShape currentSelectShape;
    // 当前要操作的控制点
    private CtrlResizeShape currentResizeShape;
    // 操作的所有图形
    private LinkedList<BaseDataShape> shapes = new LinkedList<>();
    // 当前操作的类型
    private String operStatus = OPER_NONE;
    // 是否保存
    private boolean saveed = true;
    // 保存文件的路径
    private File saveFile;

    public double getImagex() {
        return imagex;
    }

    public void setImagex(double imagex) {
        this.imagex = imagex;
    }

    public double getImagey() {
        return imagey;
    }

    public void setImagey(double imagey) {
        this.imagey = imagey;
    }

    public DrawPane() {
        setBackground(Color.gray);
        //
        bufferImage = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
        g2d = bufferImage.createGraphics();
        undoManager = new UndoableEditManager();
        //
        imageWidth = bufferImage.getWidth() * scale;
        imageHeight = bufferImage.getHeight() * scale;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        imagex = (this.getWidth() - (int) imageWidth) >> 1;
        imagey = (this.getHeight() - (int) imageHeight) >> 1;
        g.drawImage(bufferImage, (int) imagex, (int) imagey,
                (int) imageWidth, (int) imageHeight, this);
    }

    public void reDraw() {
//        g2d = bufferImage.createGraphics();
        // ����� ����
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.getBackground());
        g2d.fill(new Rectangle2D.Double(0, 0, bufferImage
                .getWidth(), bufferImage.getHeight()));
        g2d.setColor(Color.white);
        g2d.scale(scale, scale);
        g2d.fill(new Rectangle2D.Double(0, 0, bufferImage
                .getWidth(), bufferImage.getHeight()));
        int baseSize = 10;
        int hCount = bufferImage.getHeight() / baseSize;
        Stroke defaultStroke = g2d.getStroke();
        BasicStroke dotted = new BasicStroke(1.5f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 0, new float[]{0, 10, 0, 10}, 0);
        g2d.setColor(Color.gray.brighter());
        g2d.setStroke(dotted);
        for (int i = 1; i < hCount; i++) {
            g2d.draw(new Line2D.Double(0, baseSize * i,
                    bufferImage.getWidth(), baseSize * i));
        }
        shapes = drawShapes(defaultStroke, shapes, currentSelectShape);
        if (Application.anchor.getX() > -1) {
            g2d.setColor(Color.red);
            g2d.fill(new Ellipse2D.Double(
                    Application.anchor.getX() - 3,
                    Application.anchor.getY() - 3, 6, 6));
        }
        if (Application.selectBox != null) {
            g2d.setColor(Color.green);
            g2d.draw(Application.selectBox);
        }
    }

    public final void update(Graphics g) {
        this.paintComponent(g);
    }

    public BaseDataShape getShape() {
        return currentSelectShape;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        imageWidth = bufferImage.getWidth() * scale;
        imageHeight = bufferImage.getHeight() * scale;
        this.setPreferredSize(new Dimension((int) imageWidth, (int) imageHeight));
    }

    public Rectangle2D getImageRect() {
        if (imagex < 0) {
            return this.getBounds();
        }
        imageBox.setRect(imagex, imagey, imageWidth, imageHeight);
        return imageBox;
    }

    public Graphics2D getImageGraphics2D() {
        return this.g2d;
    }

    public UndoableEditManager getUndoManager() {
        return undoManager;
    }

    public AbstractShapeFactory getShapeFactory() {
        return shapeFactory;
    }

    public void setShapeFactory(AbstractShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public BaseDrawPanelBiz getDrawPanelBiz() {
        return drawPanelBiz;
    }

    public void setDrawPanelBiz(BaseDrawPanelBiz drawPanelBiz) {
        this.drawPanelBiz = drawPanelBiz;
    }

    public BaseDragBiz getDragBiz() {
        return dragBiz;
    }

    public void setDragBiz(BaseDragBiz dragBiz) {
        this.dragBiz = dragBiz;
    }

    public void removeSelectShape() {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).isSelect()) {
                shapes.remove(i);
            }
        }
    }

    public LinkedList<BaseDataShape> getShapes() {
        return this.shapes;
    }

    public void addShape(BaseDataShape shape) {
        shapes.add(shape);
        ActionManager.fireActionEnabled();
    }

    public void removeAllShapes() {
        shapes.clear();
        currentSelectShape = null;
        ActionManager.fireActionEnabled();
    }

    public boolean isSelectAll() {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            BaseDataShape shape = shapes.get(i);
            if (!shape.isSelect()) {
                return false;
            }
        }
        return true;
    }

    public List<BaseDataShape> getAllCopyShape() {
        List<BaseDataShape> list = new LinkedList<>();
        for (BaseDataShape shape : shapes) {
            if (!shape.canCopy()) {
                continue;
            }
            list.add(shape);
        }
        return list;
    }

    public void clearSelect() {
        Iterator<BaseDataShape> list = shapes.iterator();
        BaseShape shape = null;
        while (list.hasNext()) {
            shape = list.next();
            shape.setSelect(false);
        }
        currentSelectShape = null;
        currentResizeShape = null;
    }

    public LinkedList<BaseDataShape> drawShapes(Stroke defaultStroke,
            List<BaseDataShape> shapes, BaseDataShape currShape) {
        // 画图的排序
        LinkedList<BaseDataShape> tempList = new LinkedList<>();
        for (BaseDataShape shape : shapes) {
            if (shape.isSubSystem()) {
                tempList.add(shape);
            }
        }
        for (BaseDataShape shape : shapes) {
            if (!shape.isSubSystem() && !(shape instanceof BaseLineShape)) {
                tempList.add(shape);
            }
        }
        if (currShape != null && !currShape.isSubSystem()
                && !(currShape instanceof BaseLineShape)) {
            tempList.remove(currShape);
            tempList.add(currShape);
        }
        for (BaseDataShape shape : shapes) {
            if (shape instanceof BaseLineShape) {
                tempList.add(shape);
            }
        }
        // 开始画图
        getImageGraphics2D().setStroke(defaultStroke);
        for (BaseDataShape shape : shapes) {
            shape.draw(getImageGraphics2D());
        }
        return tempList;
    }

    public void addDraw(List<BaseDataShape> list) {
        for (BaseDataShape shape : list) {
            shape.setSelect(true);
        }
        shapes.addAll(drawShapes(getImageGraphics2D().getStroke(), list, null));
        repaint();
    }

    public void setOperStatus(String opt) {
        this.operStatus = opt;
    }

    public BaseDataShape getCurrentSelectShape() {
        return this.currentSelectShape;
    }

    public void setSaveed(boolean saved) {
        this.saveed = saved;
    }

    public CtrlResizeShape getCurrentResizeShape() {
        return this.currentResizeShape;
    }

    public void setCurrentSelectShape(BaseDataShape line) {
        this.currentSelectShape = line;
    }

    public String getOperStatus() {
        return this.operStatus;
    }

    public void setCurrentResizeShape(CtrlResizeShape contansCtrlBox) {
        this.currentResizeShape = contansCtrlBox;
    }
}
