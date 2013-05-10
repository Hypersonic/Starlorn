package edu.stuy.starlorn.entities;

/*
 * An object moving around the world. This shouldn't be used directly -- It should be subclassed and extended.
 */
public class Entity {
    private double _x, _y;
    
    public Entity(double x, double y) {
        _x = x;
        _y = y;
    }

    public Entity() {
        this(0,0);
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
        System.out.printf("X: %.1f, Y: %.1f\n",getX(), getY());
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

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }
}
