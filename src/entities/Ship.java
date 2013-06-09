package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Iterator;

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
            if (up.getName() != "Default Gun")
                s.addUpgrade(up.clone());
        }
    }

    public void addUpgrade(Upgrade upgrade) {
        upgrade.setOwnedByPlayer(this instanceof PlayerShip);
        if (upgrade instanceof GunUpgrade)
            gunUpgrades.add((GunUpgrade) upgrade);
    }

    public boolean isHit(Bullet b) {
        Rectangle2D.Double brect = b.getRect();
        return brect.intersects(rect);
    }

    public LinkedList<Bullet> applyUpgrade(Bullet bull, GunUpgrade up) {
        LinkedList<Bullet> createdBullets = new LinkedList<Bullet>();
        for (int i = 0; i < up.getNumShots(); i++) {
            Bullet b = bull.clone();
            b.setAngle(b.getAngle() + up.getAimAngle());
            b.setSeeking(up.getSeeking(b.getSeeking()));
            b.setSpeed(up.getShotSpeed(b.getSpeed()));
            b.getRect().x += up.getXOffset();
            createdBullets.add(b);
        }
        return createdBullets;
    }

    public LinkedList<Bullet> applyAllUpgrades(String[] sprites) {
        LinkedList<Bullet> bullets = new LinkedList<Bullet>();
        LinkedList<Bullet> newBullets = new LinkedList<Bullet>();
        Bullet firstBullet = new Bullet(sprites, baseAim, 10);
        double centerx = rect.x + rect.width / 2 - firstBullet.getRect().width / 2;
        firstBullet.getRect().x = centerx;
        firstBullet.getRect().y = this.getRect().y;
        bullets.add(firstBullet);
        for (GunUpgrade up : gunUpgrades) {
            Iterator<Bullet> it = bullets.iterator();
            while (it.hasNext()) {
                Bullet b = it.next();
                LinkedList<Bullet> created = applyUpgrade(b, up);
                for (Bullet bul : created) {
                    newBullets.add(bul);
                }
                it.remove();
            }

            for (Bullet b : newBullets)
                bullets.add(b);
            newBullets.clear();
        }

        return bullets;
    }

    /*
     * Create the shots based on the available GunUpgrades
     */
    public void shoot() {
        double cooling = baseCooldown;
        double agility = 0;
        String[] sprites = null;
        for (GunUpgrade up : gunUpgrades) {
            cooling = up.getCooldown(cooling);
            agility = up.getAgility(agility);
            sprites = up.getSprites(sprites, this);
        }
        cooldownTimer = (int) cooling;
        for (Bullet b : applyAllUpgrades(sprites)) {
            if (b.getSeeking())
                b.seek(agility, getNearestTarget());
            b.setSprites(sprites);
            spawnBullet(b);
        }
    }

    /* Post-creation hook for subclasses to modify bullets. */
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

    public int getNumUpgrades() {
        return gunUpgrades.size() - 1;  // Ignore default GunUpgrade
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
