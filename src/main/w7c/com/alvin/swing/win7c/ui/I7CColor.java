/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvin.swing.win7c.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Administrator
 */
public interface I7CColor {

    Font FONT_UI_TEXT = new Font("微软雅黑", Font.PLAIN, 12); //UI字体
    Font FONT_UI_TEXT_BUTTON = new Font("微软雅黑", Font.PLAIN, 14); //UI字体
    AlphaComposite half_composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    //
    Color COLOR_PANEL_BACKGROUND = new Color(224, 235, 247); //面板背景色
    Color COLOR_UI_TEXT = new Color(21, 66, 139); //UI文字颜色
    Color COLOR_UI_FRAME = new Color(255, 183, 0);//UI边框颜色(黄色)
    Color COLOR_TOOLBAR_FRAME = new Color(186, 201, 219); //工具栏边框

    Color frameColor_up = new Color(255, 255, 222);
    Color c1_up = new Color(253, 236, 219);
    Color c2_up = new Color(253, 223, 187);
    Color c3_up = new Color(255, 206, 105);
    Color c4_up = new Color(255, 255, 222);

    Color frameColor_down = new Color(255, 183, 0);

}
