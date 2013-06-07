package edu.stuy.starlorn.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rainbow extends Rectangle2D.Double {

    private int curColor = 0;
    private int update = 0;
    private Color[] colors = {
        Color.red,
        Color.orange,
        Color.yellow,
        Color.green,
        Color.blue,
        new Color(75,0,130),
        new Color(143,0,255)
    };

    public Rainbow(double width, double height, int color) {
        super(color*width, 0, width, height);
        this.width = width;
        this.height = height;
        curColor = setColor(color);
    }

    public Color nextColor() {
        if(update == 10) {
            update = 0;
            int newColor = curColor + 1;
            if (newColor > 6) newColor = 0;
            curColor = newColor;
            return colors[newColor];
        } else {
            update++;
            return colors[curColor];
        }
    }
    
    public int setColor(int c) {
        c %= colors.length;
        if(c > 6)
            c = 0;
        return c;
    }
    
    public int getColor() {
        return curColor;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(nextColor());
        graphics.fill(this);
    }

    public void step() {
        nextColor();
    }
}
