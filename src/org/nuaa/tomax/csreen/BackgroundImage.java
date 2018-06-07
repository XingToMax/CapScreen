package org.nuaa.tomax.csreen;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/5/28 9:11
 */
public class BackgroundImage extends JLabel {

    private int x;
    private int y;
    private int w;
    private int h;

    private int lineX;
    private int lineY;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.paintComponent(g);
        g.drawRect(x, y, w, h);
        String area = Integer.toString(w) + " * " + Integer.toString(h);
        g.drawString(area, x + (int) w / 2 - 15, y + (int) h / 2);
        g.drawLine(lineX, 0, lineX, getHeight());
        g.drawLine(0, lineY, getWidth(), lineY);
    }

    public void drawRectangle(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        repaint();
    }

    public void drawCross(int x, int y) {
        lineX = x;
        lineY = y;
        repaint();
    }

    public void initialize(){
        x = 0;
        y = 0;
        h = 0;
        w = 0;
        lineX = 0;
        lineY = 0;
    }
}
