package org.umlv2.alvin.biz;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
 
/**
 * 
 * 
 * @author Administrator
 * 
 */
public abstract class BaseBiz implements MouseListener, MouseWheelListener,
		MouseMotionListener, DropTargetListener {

	public void dragEnter(DropTargetDragEvent dtde) {

	}

	public void dragOver(DropTargetDragEvent dtde) {

	}

	public void dropActionChanged(DropTargetDragEvent dtde) {

	}

	public void dragExit(DropTargetEvent dte) {

	}

	public void drop(DropTargetDropEvent dtde) {

	}

	protected void aotuConnection(MouseEvent e) {
//		Rectangle2D rect = Application.drawPanel.getImageRect();
//		BaseLineShape line;
//		line = (BaseLineShape) Application.currentResizeShape.getCtrl();
//		if (line == null)
//			return;
//		// 
//		line.setBeginShape(null);
//		line.setEndShape(null);
//		// ���
//		for (BaseDataShape shape : Application.shapes) {
//			//  
//			if (!shape.isConectable())
//				continue;
//
//			//  
//			if (!(shape.contains(new Point2D.Double(e.getPoint().x
//					- rect.getX(), e.getPoint().y - rect.getY()))))
//				continue;
//
//			//   
//			if (Application.currentResizeShape.getWay().equals(
//					ShapeHelper.WAY_TOP)) {
//				line.setBeginShape(shape);
//				break;
//			}
//			if (Application.currentResizeShape.getWay().equals(
//					ShapeHelper.WAY_BOTTOM)) {
//				line.setEndShape(shape);
//				break;
//			}
//		}
	}
}
