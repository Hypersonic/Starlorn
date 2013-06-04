package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import edu.stuy.starlorn.graphics.Sprite;
import edu.stuy.starlorn.upgrades.GunUpgrade;
import edu.stuy.starlorn.upgrades.Upgrade;

public class Ship extends Entity {

    protected LinkedList<GunUpgrade> gunUpgrades;
    protected int baseShotSpeed, cooldownTimer, baseCooldown, cooldownRate, maxSpeed;
    protected double baseAim;
    protected boolean shootRequested;

    public Ship(double x, double y, String name) {
        super(x, y, name);
        gunUpgrades = new LinkedList<GunUpgrade>();
        gunUpgrades.add(new GunUpgrade()); // add default gunupgrade
        baseShotSpeed = 12;
        baseAim = Math.PI / 2; //Aim up by default
        baseCooldown = 10;
        cooldownTimer = 0;
        cooldownRate = 1;
        maxSpeed = 10;
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

    protected void clone(Ship s) {
        s.sprite = sprite;
        s.baseShotSpeed = baseShotSpeed;
        s.baseCooldown = baseCooldown;
        s.cooldownRate = cooldownRate;
        s.maxSpeed = maxSpeed;
        s.baseAim = baseAim;
        for (GunUpgrade up : gunUpgrades) {
            s.addUpgrade(up.clone());
        }
    }

    public void addUpgrade(Upgrade upgrade) {
        if (upgrade instanceof GunUpgrade)
            gunUpgrades.add((GunUpgrade) upgrade);
    }

    public boolean isHit(Bullet b) {
        Rectangle2D.Double brect = b.getRect();
        return brect.intersects(rect);
    }

    /*
     * Create the shots based on the available GunUpgrades
     */
    public void shoot() {
        GunUpgrade topShot = gunUpgrades.get(0);
        double shotSpeed = baseShotSpeed;
        double cooldown = baseCooldown;
        boolean seeking = false;
        double agility = 0;
        for (GunUpgrade up : gunUpgrades) {
            if (up.getNumShots() >= topShot.getNumShots())
                topShot = up;
            shotSpeed = up.getShotSpeed(shotSpeed);
            cooldown = up.getCooldown(cooldown);
            seeking = up.getSeeking(seeking);
            agility = up.getAgility(agility);
        }

        // Create new shots, based on dem vars
        int numShots = topShot.getNumShots();
        for (int i = 0; i < numShots; i++) {
            Bullet b = new Bullet(topShot.getSprites(this),
                                  baseAim + topShot.getAimAngle(), shotSpeed);
            if (seeking)
                b.seek(agility, getNearestTarget());
            double centerx = rect.x + rect.width / 2 - b.getRect().width / 2;
            b.getRect().x = centerx + topShot.getXOffset();
            b.getRect().y = rect.y + 10;
            spawnBullet(b);
        }
        cooldownTimer = (int) cooldown;
    }

    protected void spawnBullet(Bullet b) {
        b.setWorld(getWorld());
    }

    @Override
    public void step() {
        //Only baseCooldown if we're below the rate, otherwise the ship hasn't tried to shoot
        if (cooldownTimer <= 0 && shootRequested) {
            this.shoot();
        } else {
            cooldownTimer -= cooldownRate;
        }
        super.step();
    }

    public Ship getNearestTarget() {
        return world.getPlayer();
    }

    public void setShootRequested(boolean shoot) {
        shootRequested = shoot;
    }

    public boolean getShootRequested() {
        return shootRequested;
    }

    public void setBaseShotSpeed(int speed) {
        baseShotSpeed = speed;
    }

    public int getBaseShotSpeed() {
        return baseShotSpeed;
    }

    public void setBaseCooldown(int baseCooldown) {
        this.baseCooldown = baseCooldown;
    }

    public int getBaseCooldown() {
        return baseCooldown;
    }

    public void setCooldownRate(int rate) {
        cooldownRate = rate;
    }

    public int getCooldownRate() {
        return cooldownRate;
    }

    public void setMovementSpeed(int speed) {
        maxSpeed = speed;
    }

    public int getMovementSpeed() {
        return maxSpeed;
    }
}
