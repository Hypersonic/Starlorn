package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import edu.stuy.starlorn.graphics.Sprite;
import edu.stuy.starlorn.upgrades.GunUpgrade;

public class Ship extends Entity {
    protected LinkedList<GunUpgrade> _gunupgrades;
    protected int _baseDamage, _baseShotSpeed, _health, _maxHealth, _cooldownTimer, _cooldown, _cooldownRate, _movementSpeed;
    protected double _baseAim;
    protected boolean _shootRequested;

    public Ship() {
        super();
        _gunupgrades = new LinkedList<GunUpgrade>();
        _baseDamage = 1;
        _baseShotSpeed = 1;
        _maxHealth = 10;
        _health = _maxHealth;
        _baseAim = 0; //Aim up by default
        _cooldown = 10;
        _cooldownTimer = 0;
        _cooldownRate = 1;
        _movementSpeed = 1;
        _shootRequested = false;
    }

    public Ship clone() {
        Ship s = new Ship();
        s._baseDamage = _baseDamage;
        s._baseShotSpeed = _baseShotSpeed;
        s._maxHealth = _maxHealth;
        s._health = _maxHealth;
        s._cooldown = _cooldown;
        s._cooldownRate = _cooldownRate;
        s._movementSpeed = _movementSpeed;
        s._baseAim = _baseAim;
        return s;
    }

    // public void draw(Graphics2D graphics) {
    // }

    public void addUpgrade(GunUpgrade upgrade) {
        _gunupgrades.add(upgrade);
    }

    public boolean isHit(Bullet b) {
        Rectangle2D.Double brect = b.getRect();
        return (brect.x + b.getXvel() + brect.width > rect.x + xvel &&
                brect.x + b.getXvel() < rect.x + rect.width + xvel &&
                brect.y + b.getYvel() + brect.height > rect.y + yvel &&
                brect.y + b.getYvel() < rect.y + rect.height + yvel);
    }

    /*
     * Create the shots based on the available GunUpgrades
     */
    public void shoot() {
        GunUpgrade topShot = _gunupgrades.get(0);
        int damage = _baseDamage;
        int shotSpeed = _baseShotSpeed;
        for (GunUpgrade up : _gunupgrades) {
            if (up.getNumShots() > topShot.getNumShots())
                topShot = up;
            damage = up.getDamage(damage);
            shotSpeed = up.getShotSpeed(shotSpeed);
        }
        // Create new shots, based on dem vars
        int numShots = topShot.getNumShots();
        for (int i = 0; i < numShots; i++) {
            Bullet b = new Bullet(_baseAim + topShot.getAimAngle(), damage,
                    shotSpeed);
            b.getRect().x = rect.x + topShot.getXOffset();
            b.getRect().y = rect.y + 10;
            b.setWorld(this.getWorld());
        }
    }
    @Override
    public void step() {
        //Only cooldown if we're below the rate, otherwise the ship hasn't tried to shoot
        if (_cooldownTimer <= 0 && _shootRequested) {
            this.shoot();
            _cooldownTimer = _cooldown;
        } else {
            _cooldownTimer -= _cooldownRate;
        }
        super.step();
    }

    public void setBaseDamage(int damage) {
        _baseDamage = damage;
    }

    public int getBaseDamage() {
        return _baseDamage;
    }

    public void setBaseShotSpeed(int speed) {
        _baseShotSpeed = speed;
    }

    public int getBaseShotSpeed() {
        return _baseShotSpeed;
    }

    public void setMaxHealth(int health) {
        _maxHealth = health;
    }

    public int getMaxHealth() {
        return _maxHealth;
    }

    public void setCooldown(int cooldown) {
        _cooldown = cooldown;
    }

    public int getCooldown() {
        return _cooldown;
    }

    public void setCooldownRate(int rate) {
        _cooldownRate = rate;
    }

    public int getCooldownRate() {
        return _cooldownRate;
    }

    public void setMovementSpeed(int speed) {
        _movementSpeed = speed;
    }

    public int getMovementSpeed() {
        return _movementSpeed;
    }
}
