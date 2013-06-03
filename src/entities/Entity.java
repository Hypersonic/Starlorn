package edu.stuy.starlorn.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.stuy.starlorn.graphics.Anchor;
import edu.stuy.starlorn.graphics.Sprite;
import edu.stuy.starlorn.world.World;

/*
 * An object moving around the world. This shouldn't be used directly --
 * It should be subclassed and extended.
 */
public class Entity {

    private static final boolean DRAW_OUTLINES = false;

    protected Rectangle2D.Double rect;
    protected double xvel, yvel, angle;
    protected World world;
    protected Sprite sprite;
    protected boolean dead;

    public Entity(double x, double y, double width, double height) {
        rect = new Rectangle2D.Double(x, y, width, height);
        xvel = yvel;
        angle = Math.PI / 2;
        dead = false;
    }

    public Entity(double x, double y, String name) {
        this(x, y);
        if (name != null)
            updateSprite(name);
    }

    public Entity(double x, double y) {
        this(x, y, 0, 0);
    }

    public Entity(String name) {
        this(0, 0, name);
    }

    public Entity() {
        this(0, 0);
    }

    protected void updateSprite(String name, Anchor anchor) {
        if (sprite != null && name.equals(sprite.getName()))
            return;
        sprite = Sprite.getSprite(name);
        double xdiff = anchor.xTrans * (rect.width - sprite.getWidth());
        double ydiff = anchor.yTrans * (rect.height - sprite.getHeight());
        rect = new Rectangle2D.Double(rect.x + xdiff, rect.y + ydiff,
                                      sprite.getWidth(), sprite.getHeight());
    }

    protected void updateSprite(String name) {
        updateSprite(name, Anchor.CENTER);
    }

    public void draw(Graphics2D graphics) {
        if (sprite != null) {
            graphics.setPaint(sprite.getPaint(rect));
            if (angle != Math.PI / 2) {
                double theta = Math.PI / 2 - angle,
                       centerx = rect.x + rect.width / 2,
                       centery = rect.y + rect.height / 2;
                graphics.rotate(theta, centerx, centery);
                graphics.fill(rect);
                graphics.rotate(-theta, centerx, centery);
            }
            else
                graphics.fill(rect);
            if (DRAW_OUTLINES) {
                graphics.setColor(Color.WHITE);
                graphics.draw(rect);
            }
        }
    }

    /*
     * Used for taking actions (moving, shooting, etc)
     */
    public void step() {
        rect.x += xvel;
        rect.y += yvel;
    }

    public Rectangle2D.Double getRect() {
        return rect;
    }

    public void setRect(Rectangle2D.Double newrect) {
        rect = newrect;
    }

    public double getXvel() {
        return xvel;
    }

    public void setXvel(double x) {
        xvel = x;
    }

    public double getYvel() {
        return yvel;
    }

    public void setYvel(double y) {
        yvel = y;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double theta) {
        angle = theta;
    }

    public World getWorld() {
        return world;
    }

    /*
     * Remove self from current world (if there is one) and put it in the new world
     */
    public void setWorld(World world) {
        if (world != null) world.removeEntity(this);
        this.world = world;
        if (world != null) world.addEntity(this);
    }

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        dead = true;
    }

    public boolean onScreen() {
        return (rect.x >= 0 && rect.x <= world.getScreen().getWidth() - rect.width &&
                rect.y >= 0 && rect.y <= world.getScreen().getHeight() - rect.height);
    }
}
