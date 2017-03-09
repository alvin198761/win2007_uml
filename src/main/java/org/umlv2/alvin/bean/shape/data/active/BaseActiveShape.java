package org.umlv2.alvin.bean.shape.data.active;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashMap;
import java.util.List;

import org.umlv2.alvin.bean.shape.ShapeHelper;
import org.umlv2.alvin.bean.shape.ctrl.CtrlLineShape;
import org.umlv2.alvin.bean.shape.ctrl.CtrlResizeShape;
import org.umlv2.alvin.bean.shape.data.BaseDataShape;

public abstract class BaseActiveShape extends BaseDataShape {

	private static final long serialVersionUID = 1L;

	public BaseActiveShape() {
	}

	
	protected void drawSelectBox(Graphics2D g) {
		Stroke s = g.getStroke();
		BasicStroke bs = new BasicStroke(.8f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 0, new float[] { 6, 4, 6, 4 }, 0);
		g.setColor(Color.red);
		g.setStroke(bs);
		g.draw(getBounds2D());
		g.setStroke(s);
	}

	protected void createBox() {
		if (!this.ctrlable) {
			return;
		}
		resizeMap = new HashMap<String, CtrlResizeShape>();
		ctrlLineMap = new HashMap<String, CtrlLineShape>();
		List<String> list = ShapeHelper.getWay8();
		for (String str : list) {
			resizeMap.put(str, new CtrlResizeShape(this, str));
		}
		list = ShapeHelper.getWay4();
		for (String str : list) {
			ctrlLineMap.put(str, new CtrlLineShape(this, str));
		}
		endInit();
	}

}
