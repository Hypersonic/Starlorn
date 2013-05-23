package edu.stuy.starlorn.entities;

public class Bullet extends Entity {
    protected int _damage, _speed;

    public Bullet() {
        super();
    }

    public Bullet(int damage, int speed) {
        _damage = damage;
        _speed = speed;
    }

    public void setDamage(int damage) { _damage = damage; }
    public int getDamage() { return _damage; }
    public void setSpeed(int speed) { _speed = speed; }
    public int getSpeed() { return _speed; }

    
}
