package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.stuy.starlorn.graphics.Sprite;
import edu.stuy.starlorn.world.World;

/*
 * An object moving around the world. This shouldn't be used directly --
 * It should be subclassed and extended.
 */
public class Entity {

    protected Rectangle2D.Double rect;
    protected double xvel, yvel;
    protected World world;
    protected Sprite sprite;

    public Entity(double x, double y, double width, double height) {
        rect = new Rectangle2D.Double(x, y, width, height);
        xvel = yvel = 0;
    }

    public Entity(double x, double y, String name) {
        if (name != null) {
            sprite = Sprite.getSprite(name);
            rect = new Rectangle2D.Double(x, y, sprite.getWidth(), sprite.getHeight());
        }
        else
            rect = new Rectangle2D.Double(x, y, 0, 0);
        xvel = yvel = 0;
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

    public void draw(Graphics2D graphics) {
        if (sprite != null) {
            graphics.setPaint(sprite.getPaint(rect));
            graphics.fill(rect);
        }
    }

    /*
     * Used for handling things that need to be done before anyone takes action.
     */
    public void preStep() {
    }

    /*
     * Used for taking actions (moving, shooting, etc)
     */
    public void step() {
        rect.x += xvel;
        rect.y += yvel;
    }

    /*
     * Used for cleanup after the step
     */
    public void postStep() {
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

    // To be coded later:
    // public boolean intersect(){
    //     return false;
    // }
}
