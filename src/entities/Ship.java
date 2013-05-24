package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.upgrades.GunUpgrade;

import java.util.LinkedList;

public class Ship extends Entity {
    protected LinkedList<GunUpgrade> _gunupgrades;
    protected int _baseDamage, _baseShotSpeed, _health;
    protected double _baseAim;

    public Ship() {
        super();
        _gunupgrades = new LinkedList<GunUpgrade>();
        _baseDamage = 1;
        _baseShotSpeed = 1;
        _health = 10;
        _baseAim = Math.PI/2; //Aim up by default
    }

    public void addUpgrade(GunUpgrade upgrade) {
        _gunupgrades.add(upgrade);
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
            Bullet b = new Bullet(_baseAim + topShot.getAimAngle(), damage, shotSpeed);
            b.setWorld(this.getWorld());
        }
    }
}
