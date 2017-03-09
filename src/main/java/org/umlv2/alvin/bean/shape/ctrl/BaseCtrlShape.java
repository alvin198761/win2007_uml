package org.umlv2.alvin.bean.shape.ctrl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import org.umlv2.alvin.bean.shape.BaseShape;
import org.umlv2.alvin.bean.shape.data.BaseDataShape;

/**
 * 
 * @author ��ֲ��
 * 
 */
public abstract class BaseCtrlShape extends BaseShape {

	private static final long serialVersionUID = 1L;
	//���Ƶ�ķ�λ�����磬�ı��С�Ŀ��Ƶ㣬�����Ϸ��ģ��������ϸı��С
	protected String way;
	//������ֵ��ͼ��
	protected BaseDataShape ctrl;
	//�Ƿ����ÿ���
	protected boolean enable;
	//�ڻ��ߵ�ʱ����û��ѡ�иÿ��Ƶ�
	protected boolean lineHover;

	public BaseCtrlShape(BaseDataShape ctrl, String way) {
		this.ctrl = ctrl;
		this.way = way;
		bgColor = Color.green;
	}

	
	public boolean contains(Point2D p) {
		if (!enable) {
			return false;
		}
		return super.contains(p);
	}

	public BaseDataShape getCtrl() {
		return ctrl;
	}

	public String getWay() {
		return way;
	}

	public boolean isEnable() {
		return enable;
	}

	public boolean isLineHover() {
		return lineHover;
	}

	public void setCtrl(BaseDataShape ctrl) {
		this.ctrl = ctrl;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setLineHover(boolean lineHover) {
		this.lineHover = lineHover;
	}

	public void setWay(String way) {
		this.way = way;
	}

	protected void drawLineHoverBox(Graphics2D g) {
		if (!lineHover) {
			return;
		}
		g.setColor(Color.red);
		g.draw(getBounds2D());
	}

	
	public String toString() {
		return way;
	}
}
