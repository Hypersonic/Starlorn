package edu.stuy.starlorn.entities;

public class Bullet extends Entity {
    protected int damage, speed;

    public Bullet() {
        super("bullet/blue/long");
    }

    public Bullet(double angle, int damage, int speed) {
        this();
        this.angle = angle;
        this.damage = damage;
        this.speed = speed;
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
}
