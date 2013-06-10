package edu.stuy.starlorn.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Star extends Rectangle2D.Double {

    private Random random;
    private double speed;
    private Color color;
    private Color[] colors = {
        Color.red,
        Color.orange,
        Color.yellow,
        Color.green,
        Color.blue,
        new Color(75,0,130),
        new Color(143,0,255)
    };

    public Star(double x, double y) {
        super(x, y, 1, 1);
        random = new Random();
        width = height = (int) Math.round(Math.random() + 1);
        speed = 1 + Math.random() * 2;
        color = new Color(random.nextInt(255),
                          random.nextInt(255),
                          random.nextInt(255));
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fill(this);
    }

    public void step() {
        y += speed;
    }
}
