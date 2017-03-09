package org.umlv2.alvin.ui.drawpane;

import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

import org.umlv2.alvin.action.ActionManager;

/**
 * undo����
 * 
 * @author Administrator
 * 
 */
public class UndoableEditManager {
	/**
	 * Edit�������
	 */
	protected UndoableEditSupport undoSupport = new UndoableEditSupport();
	/**
	 * �����ָ�������
	 */
	protected UndoManager undoManager = new UndoManager();

	public UndoableEditManager() {
		undoSupport.addUndoableEditListener(undoManager);
	}

	/**
	 * 重置
	 */
	public void reset() {
		undoManager.discardAllEdits();
		ActionManager.fireActionEnabled();
	}

	/**
	 * �ύUndo
	 * 
	 * @param edit
	 */
	public void post(UndoableEdit edit) {
		undoSupport.postEdit(edit);
		ActionManager.fireActionEnabled();
	}

	public void undo() {
		undoManager.undo();
	}

	public void redo() {
		undoManager.redo();
	}

	public boolean canRedo() {
		return undoManager.canRedo();
	}

	public boolean canUndo() {
		return undoManager.canUndo();
	}
}