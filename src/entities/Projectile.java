package edu.stuy.starlorn.entities;

public class Projectile extends Entity {
    // Angle is radians, measured from the positive y axis
    private double angle, speed;

    public Projectile() {
        super();
        angle = 0;
        speed = 1;
    }

    @Override
    public void preStep() {
        super.preStep();
        xvel = getSpeed() * Math.cos(getAngle() - (Math.PI / 2));
        yvel = getSpeed() * Math.sin(getAngle() - (Math.PI / 2));

    }
    @Override
    public void step() {
        super.step();
    }

    public boolean shouldDespawnOnCollision() {
        return false;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
