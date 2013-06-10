package edu.stuy.starlorn.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Star extends Rectangle2D.Double {

    private double speed;
    private Color color;

    public Star(double x, double y) {
        super(x, y, 1, 1);
        width = height = (int) Math.round(Math.random() + 1);
        speed = 1 + Math.random() * 2;
        float light = (float) Math.random();
        color = new Color(light, light, light);
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fill(this);
    }

    public void step() {
        y += speed;
    }
}
