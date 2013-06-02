package edu.stuy.starlorn.entities;

public class Bullet extends Entity {

    protected int damage, speed;
    protected boolean firedByPlayer;

    public Bullet() {
        super("bullet/blue/long");
    }

    public Bullet(double angle, int damage, int speed) {
        this();
        this.angle = angle;
        this.damage = damage;
        this.speed = speed;
        firedByPlayer = false;
    }

    @Override
    public void step() {
        setXvel(speed * Math.cos(-angle));
        setYvel(speed * Math.sin(-angle));
        super.step();
        if (!onScreen())
            kill();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setFiredByPlayer(boolean value) {
        firedByPlayer = value;
    }

    public boolean wasFiredByPlayer() {
        return firedByPlayer;
    }
}
