package org.umlv2.alvin.bean.shape.ctrl;

import static org.umlv2.alvin.bean.shape.ShapeHelper.CTRL_SHAPE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Method;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;

/**
 * 
 */
public class CtrlResizeShape extends BaseCtrlShape {

	private static final long serialVersionUID = 1L;

	public CtrlResizeShape(BaseDataShape ctrl, String way) {
		super(ctrl, way);
	}

	/**
	 * 
	 * @param e
	 */
	public void contrl(Point2D p) {
		if (!enable) {
			return;
		}
		try {
			Method me = this.getClass().getDeclaredMethod("contrl" + way,
					Point2D.class);
			boolean flag = me.isAccessible();
			me.invoke(this, p);
			me.setAccessible(flag);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void contrl8(Point2D p) {
		double y = ctrl.getY();
		double sub = y - p.getY();
		ctrl.setY(y - sub);
		ctrl.setH(ctrl.getH() + sub);
	}

	protected void contrl9(Point2D p) {
		contrl8(p);
		contrl12(p);
	}

	protected void contrl10(Point2D p) {
		contrl8(p);
		contrl11(p);
	}

	// ��
	protected void contrl11(Point2D p) {
		double x = ctrl.getX();
		double sub = x - p.getX();
		ctrl.setX(x - sub);
		ctrl.setW(ctrl.getW() + sub);
	}

	protected void contrl12(Point2D p) {
		double x = ctrl.getX() + ctrl.getW();
		double sub = p.getX() - x;
		ctrl.setW(ctrl.getW() + sub);
	}

	protected void contrl13(Point2D p) {
		double y = ctrl.getY() + ctrl.getH();
		double sub = p.getY() - y;
		ctrl.setH(ctrl.getH() + sub);
	}

	protected void contrl14(Point2D p) {
		contrl13(p);
		contrl11(p);
	}

	protected void contrl15(Point2D p) {
		contrl13(p);
		contrl12(p);
	}

	
	public void draw(Graphics2D g) {
		g.setColor(enable ? Color.green : Color.gray);
		Point2D p = ctrl.getPoisationByWay(way);
		shape = new Rectangle2D.Double(p.getX(), p.getY(), CTRL_SHAPE_SIZE,
				CTRL_SHAPE_SIZE);
		g.fill(shape);
		g.setColor(Color.black);
		g.draw(shape);
	}

}
