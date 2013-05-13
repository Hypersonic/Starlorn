package edu.stuy.starlorn.entities;

public class Projectile extends Entity {
    private double _angle, _speed; // Angle is radians, measured from the positive x axis

    public Projectile() {
        super();
        _angle = 0;
        _speed = 1;
    }

    @Override
    public void step() {
        setXY(getX() + (getSpeed()*Math.cos(getAngle())), getY() + (getSpeed()*Math.sin(getAngle())));
    }

    public void setAngle(double angle) {
        _angle = angle;
    }
    public double getAngle() {
        return _angle;
    }

    public void setSpeed(double speed) {
        _speed = speed;
    }
    public double getSpeed() {
        return _speed;
    }
}
