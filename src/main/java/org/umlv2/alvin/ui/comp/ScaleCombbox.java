package org.umlv2.alvin.ui.comp;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.umlv2.alvin.action.ActionManager;
import org.umlv2.alvin.action.view.ResetZoomShapePanelAction;
import org.umlv2.alvin.action.view.ZoomInShapePaneAction;
import org.umlv2.alvin.action.view.ZoomOutShapePaneAction;

public class ScaleCombbox extends JComboBox {

    private static final long serialVersionUID = 1L;

    public ScaleCombbox() {
        setModel(new ScaleModel());
    }

    protected void selectedItemChanged() {
//		String item = this.getSelectedItem().toString().replace('%', ' ')
//				.trim();
//		double scale = Double.parseDouble(item) / 100;
//		Application.drawPanel.setScale(scale);
//		Application.drawPanel.reDraw();
//		Application.drawPanel.updateUI();
//
//		ActionManager.getAction(ZoomInAction.class).firePropertyChange();
//		ActionManager.getAction(ZoomOutAction.class).firePropertyChange();
//		ActionManager.getAction(ResetScaleAction.class).firePropertyChange();
//		firePropertyChange();
    }

    public boolean isEnabled() {
        return ActionManager.getAction(ZoomInShapePaneAction.class).isEnabled()
                || ActionManager.getAction(ZoomOutShapePaneAction.class).isEnabled()
                || ActionManager.getAction(ResetZoomShapePanelAction.class).isEnabled();
    }

    public void firePropertyChange() {
        firePropertyChange("enabled", true, false);
    }
}

class ScaleModel extends DefaultComboBoxModel {

    private static final long serialVersionUID = 1L;
    private static Object[] items = {"--none--", "50%", "75%", "100%", "150%",
        "200%"};

    public ScaleModel() {
        super(items);
    }

}
