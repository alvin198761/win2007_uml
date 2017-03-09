package org.umlv2.alvin.bean.shape.data.seri.line;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.umlv2.alvin.bean.shape.ShapeHelper;

/**
 *
 */
public class SeriReturnMessageBackLineShape extends BaseCallLineShape {

    private static final long serialVersionUID = 1L;

    public SeriReturnMessageBackLineShape() {
        type = ShapeHelper.SERI_MESSAGE_CALL_RETURN;
        text = "SeriMsgCallReturn";
    }

    protected void drawLine(Graphics2D g) {
        BasicStroke dotted = new BasicStroke(1.5f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 0, new float[]{1, 5, 1, 5}, 0);
        Stroke s = g.getStroke();
        g.setStroke(dotted);
        super.drawLine(g);
        g.setStroke(s);
    }

}
