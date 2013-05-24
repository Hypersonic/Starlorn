package edu.stuy.starlorn.entities;

public class Bullet extends Entity {
    protected int _damage, _speed;
    protected double _angle;

    public Bullet() {
        super();
    }

    public Bullet(double angle, int damage, int speed) {
        _angle = angle;
        _damage = damage;
        _speed = speed;
    }
    
    @Override 
    public void step() {
    	setXvel(_speed * Math.cos( _angle + Math.PI));
    	setYvel(_speed * Math.sin( _angle + Math.PI));
    	super.step();
    }

    public void setDamage(int damage) { _damage = damage; }
    public int getDamage() { return _damage; }
    public void setSpeed(int speed) { _speed = speed; }
    public int getSpeed() { return _speed; }

    
}
