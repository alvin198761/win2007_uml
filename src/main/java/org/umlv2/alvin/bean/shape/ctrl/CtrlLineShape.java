package org.umlv2.alvin.bean.shape.ctrl;

import static org.umlv2.alvin.bean.shape.ShapeHelper.CTRL_SHAPE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.bean.shape.data.BaseLineShape;

/**
 * 
 * @author ��ֲ��
 * 
 */
public class CtrlLineShape extends BaseCtrlShape {

	private static final long serialVersionUID = 1L;
	public static final String STATUS_BEGIN = "begin";
	public static final String STATUS_END = "end";
	// ͼ��ĳ����λ����������
	protected LinkedList<BaseLineShape> lines = new LinkedList<BaseLineShape>();
	// ÿ�����ߵķ���[line.id,way],��Ϊ�����м�ͷ��
	protected Map<String, String> lineMap = new HashMap<String, String>();

	public Map<String, String> getLineMap() {
		return lineMap;
	}

	public CtrlLineShape(BaseDataShape ctrl, String way) {
		super(ctrl, way);
	}

	
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		Point2D p = ctrl.getPoisationByWay(way);
		shape = new Rectangle2D.Double(p.getX(), p.getY(), CTRL_SHAPE_SIZE,
				CTRL_SHAPE_SIZE);
		g.draw(new Line2D.Double(p.getX(), p.getY(),
				p.getX() + CTRL_SHAPE_SIZE, p.getY() + CTRL_SHAPE_SIZE));
		g.draw(new Line2D.Double(p.getX(), p.getY() + CTRL_SHAPE_SIZE, p.getX()
				+ CTRL_SHAPE_SIZE, p.getY()));
		drawLineHoverBox(g);
	}

	/**
	 * ��ӿ�ʼ��������������������
	 * 
	 * @param line
	 */
	public void addBeginLine(BaseLineShape line) {
		this.lines.add(line);
		lineMap.put(line.getId(), STATUS_BEGIN);
		contrllineShape(line);
	}

	/**
	 * � �
	 * 
	 * @param line
	 */
	public void addEndLine(BaseLineShape line) {
		this.lines.add(line);
		lineMap.put(line.getId(), STATUS_END);
		contrllineShape(line);
	}

	/**
	 *  
	 * 
	 * @param line
	 */
	public boolean removeLine(BaseDataShape line) {
		return this.lines.remove(line);
	}

	/**
	 * � 
	 */
	public void contrlLine() {
		for (BaseLineShape line : lines) {
			contrllineShape(line);
		}
	}

	public List<BaseLineShape> getLines() {
		return lines;
	}

	/**
	 * �ƶ�ĳһ����
	 * 
	 * @param line
	 */
	private void contrllineShape(BaseLineShape line) {
		Rectangle2D rect = this.getBounds2D();
		if (lineMap.get(line.getId()).equals(STATUS_BEGIN)) {
			line.setX(rect.getCenterX());
			line.setY(rect.getCenterY());
		}
		if (lineMap.get(line.getId()).equals(STATUS_END)) {
			line.setX2(rect.getCenterX());
			line.setY2(rect.getCenterY());
		}
	}

}
