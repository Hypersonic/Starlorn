package edu.stuy.starlorn.entities;

public class Bullet extends Entity {
    protected int damage, speed;
    protected double angle;

    public Bullet() {
        super("bullet/blue/long");
    }

    public Bullet(double angle, int damage, int speed) {
        this.angle = angle;
        this.damage = damage;
        this.speed = speed;
    }

    @Override
    public void step() {
        setXvel(speed * Math.cos(angle + Math.PI));
        setYvel(speed * Math.sin(angle + Math.PI));
        super.step();
    }

    public void setDamage(int damage) { this.damage = damage; }
    public int getDamage() { return damage; }
    public void setSpeed(int speed) { this.speed = speed; }
    public int getSpeed() { return speed; }

}
