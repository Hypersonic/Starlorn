package edu.stuy.starlorn.menu;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Star {

    private Rectangle2D.Double rect;
    private double xvel, yvel, zvel, maxwidth, maxheight;
    private boolean show;

    public Star(double displayx, double displayy) {
        rect = new Rectangle2D.Double();
        maxwidth = displayx;
        maxheight = displayy;
        show = false;
        reset();
    }

    private void reset() {
        rect.x = Math.random() * maxwidth;
        rect.y = Math.random() * maxheight;
        rect.width = rect.height = 1;
        xvel = yvel = zvel = 0;
    }

    public void update() {
        double cx = maxwidth / 2, cy = maxheight / 2;
        double dist = Math.sqrt(((cx - rect.x) * (cx - rect.x)) +
                                ((cy - rect.y) * (cy - rect.y)));

        rect.x += xvel;
        rect.y += yvel;
        xvel = (rect.x - cx) / 50;
        yvel = (rect.y - cy) / 50;
        rect.width += zvel / 3;
        rect.height = rect.width;
        zvel = dist * dist / 700000;

        if (rect.x <= 0 || rect.y <= 0 || rect.x >= maxwidth || rect.y >= maxheight) {
            reset();
            show = true;
        }
    }

    public void draw(Graphics2D graphics) {
        if (show)
            graphics.fill(rect);
    }
}
