package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.upgrades.GunUpgrade;

import java.util.LinkedList;

public class Ship extends Entity {
    protected LinkedList<GunUpgrade> _gunupgrades;

    public Ship() {
        super();
        _gunupgrades = new LinkedList<GunUpgrade>();
    }

    public void addUpgrade(GunUpgrade upgrade) {
        _gunupgrades.add(upgrade);
    }

    /*
     * Create the shots based on the available GunUpgrades
     */
    public void shoot() {
        //TODO: Make this work based off the GunUpgrade spec
    }
}
