package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import edu.stuy.starlorn.graphics.Sprite;
import edu.stuy.starlorn.upgrades.GunUpgrade;

public class Ship extends Entity {

    protected LinkedList<GunUpgrade> gunUpgrades;
    protected int baseDamage, baseShotSpeed, health, maxHealth, cooldownTimer,
                  cooldown, cooldownRate, movementSpeed;
    protected double baseAim;
    protected boolean shootRequested;

    public Ship(double x, double y, String name) {
        super(x, y, name);
        gunUpgrades = new LinkedList<GunUpgrade>();
        baseDamage = 1;
        baseShotSpeed = 1;
        maxHealth = 10;
        health = maxHealth;
        baseAim = 0; //Aim up by default
        cooldown = 10;
        cooldownTimer = 0;
        cooldownRate = 1;
        movementSpeed = 1;
        shootRequested = false;
    }

    public Ship(String name) {
        this(0, 0, name);
    }

    public Ship(double x, double y) {
        this(x, y, null);
    }

    public Ship() {
        this(null);
    }

    public Ship clone() {
        Ship s = new Ship();
        s.sprite = sprite;
        s.baseDamage = baseDamage;
        s.baseShotSpeed = baseShotSpeed;
        s.maxHealth = maxHealth;
        s.health = maxHealth;
        s.cooldown = cooldown;
        s.cooldownRate = cooldownRate;
        s.movementSpeed = movementSpeed;
        s.baseAim = baseAim;
        return s;
    }

    // public void draw(Graphics2D graphics) {
    // }

    public void addUpgrade(GunUpgrade upgrade) {
        gunUpgrades.add(upgrade);
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
        GunUpgrade topShot = gunUpgrades.get(0);
        int damage = baseDamage;
        int shotSpeed = baseShotSpeed;
        for (GunUpgrade up : gunUpgrades) {
            if (up.getNumShots() > topShot.getNumShots())
                topShot = up;
            damage = up.getDamage(damage);
            shotSpeed = up.getShotSpeed(shotSpeed);
        }
        // Create new shots, based on dem vars
        int numShots = topShot.getNumShots();
        for (int i = 0; i < numShots; i++) {
            Bullet b = new Bullet(baseAim + topShot.getAimAngle(), damage,
                    shotSpeed);
            b.getRect().x = rect.x + topShot.getXOffset();
            b.getRect().y = rect.y + 10;
            b.setWorld(this.getWorld());
        }
    }
    @Override
    public void step() {
        //Only cooldown if we're below the rate, otherwise the ship hasn't tried to shoot
        if (cooldownTimer <= 0 && shootRequested) {
            this.shoot();
            cooldownTimer = cooldown;
        } else {
            cooldownTimer -= cooldownRate;
        }
        super.step();
    }

    public void setBaseDamage(int damage) {
        baseDamage = damage;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseShotSpeed(int speed) {
        baseShotSpeed = speed;
    }

    public int getBaseShotSpeed() {
        return baseShotSpeed;
    }

    public void setMaxHealth(int health) {
        maxHealth = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldownRate(int rate) {
        cooldownRate = rate;
    }

    public int getCooldownRate() {
        return cooldownRate;
    }

    public void setMovementSpeed(int speed) {
        movementSpeed = speed;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }
}
