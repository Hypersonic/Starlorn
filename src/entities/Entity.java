package edu.stuy.starlorn.entities;

/*
 * An object moving around the world. This shouldn't be used directly --
 * It should be subclassed and extended.
 */
public class Entity {
    private double _xcor, _ycor;
    private double _xvel, _yvel;
    
    private int _width, _height;

    public Entity(double x, double y, int width, int height) {
        _xcor = x;
        _ycor = y;
        _xvel = _yvel = 0;
        _width = width;
        _height = height;
    }

    public Entity(double x, double y) {
        this(x, y, 0, 0);
    }

    public Entity() {
        this(0,0);
    }

    public void draw() { //this draws the thing
        //code to be put later for texture
    }
    
    /*
     * Used for handling things that need to be done before anyone takes action.
     
     // (these functions aren't useful, it should be all in the update() )

     
    public void preStep() {
        System.out.printf("X: %.1f, Y: %.1f\n",getX(), getY());
    }

    
     * Used for taking actions (moving, shooting, etc)
     
     // (these functions aren't useful, it should be all in the update() )
    
    public void step() {
        setXY(getX()-1 + Math.random() * 2, getY()-1 + Math.random() * 2);
    }

    
     * Used for cleanup after the step
     
    public void postStep() {
        
    }
     */

    public void setX(double x) {_xcor = x;}
    public double getX() {return _xcor;}

    public void setY(double y) {_ycor = y;}
    public double getY() {return _ycor;}

    public void setWidth(int width) {_width = width;}
    public int getWidth() {return _width;}

    public void setHeight(int height) {_height = height;}
    public int getHeight() {return _height;}

    public void setXY(double x, double y) {setX(x);setY(y);}
    
    public boolean intersect(/* Some object, idk*/){ //to be coded later
    }
    
    public void update(int delta){
        _xcor += _xvel;
        _ycor += _yvel;

        boolean interscet false;' //Just a placeholder, this should be from a method
        if (intersect) {//do stuff
        }
    }
    
    
}
