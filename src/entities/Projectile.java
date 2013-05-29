package edu.stuy.starlorn.entities;

public class Projectile extends Entity {
    // Angle is radians, measured from the positive y axis
    private double _angle, _speed;

    public Projectile() {
        super();
        _angle = 0;
        _speed = 1;
    }

    @Override
    public void preStep() {
        super.preStep();
        _xvel = getSpeed() * Math.cos(getAngle() - (Math.PI/2));
        _yvel = getSpeed() * Math.sin(getAngle() - (Math.PI/2));

    }
    @Override
    public void step() {
        super.step();
    }
    
    public boolean shouldDespawnOnCollision() {
        return false;
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
