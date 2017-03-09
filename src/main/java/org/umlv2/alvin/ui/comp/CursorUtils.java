/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umlv2.alvin.ui.comp;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author Administrator
 */
public class CursorUtils {

    private static Cursor rightCursor;
    private static Cursor wrongCursor;

    public static Cursor getRightCursor() {
        if (rightCursor == null) {
            rightCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    Toolkit.getDefaultToolkit().createImage(CursorUtils.class.getResource("/right.gif")),
                    new Point(0, 0), "move_right");
        }
        return rightCursor;
    }

    public static Cursor getWrongCursor() {
        if (wrongCursor == null) {
            wrongCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    Toolkit.getDefaultToolkit().createImage(CursorUtils.class.getResource("/wrong.gif")), new Point(0, 0), "move_wrong");
        }
        return wrongCursor;
    }

}
