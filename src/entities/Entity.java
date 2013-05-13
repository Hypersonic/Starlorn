package edu.stuy.starlorn.entities;

/*
 * An object moving around the world. This shouldn't be used directly -- It should be subclassed and extended.
 */
public class Entity {
    private double _x, _y;
    private int _width, _height;

    public Entity(double x, double y, int width, int height) {
        _x = x;
        _y = y;
        _width = width;
        _height = height;
    }

    public Entity(double x, double y) {
        this(x, y, 0, 0);
    }

    public Entity() {
        this(0,0);
    }

    /* 
     * Used for handling things that need to be done before anyone takes action.
     */
    public void preStep() {
        System.out.printf("X: %.1f, Y: %.1f\n",getX(), getY());
    }

    /*
     * Used for taking actions (moving, shooting, etc)
     */
    public void step() {
        setXY(getX()-1 + Math.random() * 2, getY()-1 + Math.random() * 2);
    }

    /*
     * Used for cleanup after the step
     */
    public void postStep() {
    }

    public void setX(double x) {
        _x = x;
    }
    public double getX() {
        return _x;
    }

    public void setY(double y) {
        _y = y;
    }
    public double getY() {
        return _y;
    }

    public void setWidth(int width) {
        _width = width;
    }
    public int getWidth() {
        return _width;
    }

    public void setHeight(int height) {
        _height = height;
    }
    public int getHeight() {
        return _height;
    }

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }
}
