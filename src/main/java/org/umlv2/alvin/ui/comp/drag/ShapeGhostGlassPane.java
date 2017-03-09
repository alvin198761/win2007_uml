package org.umlv2.alvin.ui.comp.drag;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.umlv2.alvin.sys.Application;
import org.umlv2.alvin.ui.drawpane.DrawPane;

/**
 *
 */
public class ShapeGhostGlassPane extends JPanel {

    private static final long serialVersionUID = 1L;
    private DrawPane drawPane;

    public ShapeGhostGlassPane(DrawPane drawPane) {
        setOpaque(false);
        this.drawPane = drawPane;
    }

    public void paintComponent(Graphics g) {
        drawPane.getDragBiz().paintCompent(g);
    }

    public final void update(Graphics g) {
        this.paintComponent(g);
    }
}
