package edu.stuy.starlorn.upgrades;

import edu.stuy.starlorn.entities.Ship;

/*
 * Upgrades that modify a ship's gun/shots
 */
public class GunUpgrade extends Upgrade {

    public GunUpgrade() {
        super();
        _name = "Default Gun";
        _description = "No changes at all!";
    }

    /*
     * Returns the number of shots this upgrade allows for.
     * Highest should be chosen
     */
    public int getNumShots() {
        return 1;
    }

    /*
     * Gives an angle this ship should aim at, measured in radians,
     * From the vertical axis.
     * Called for each shot, so it should probably keep track of which one it's at.
     * Should be chosen based on getNumShots
     */
    public double getAimAngle() {
        return 0;
    }

    /*
     * Gives the name of the bullet sprites, as an array to be cycled through.
     */
    public String[] getSprites(String[] sprites) {
        return sprites;
    }

    /*
     * Gives a number of coordinates to offset the shot's origin
     * on the X axis from wherever shots would normally come from.
     * Not garunteed to return the same thing each time, should probably keep track of which shot this is (like getAimAngle)
     * Should be chosen based off of getNumShots
     */
    public double getXOffset() {
        return 0;
    }

    /*
     * Get the cooldown between shots, in ticks, based off the previous one
     */
    public double getCooldown(double cooldown) {
        return cooldown;
    }

    /*
     * Get the speed the shot should move at
     */
    public double getShotSpeed(double shotspeed) {
        return shotspeed;
    }

    /*
     * Returns whether or not this upgrade causes shots to seek a target.
     */
    public boolean getSeeking(boolean seeking) {
        return seeking;
    }

    /*
     * Get the max turn angle of bullets created by this upgrade, in
     * radians per tick.
     */
    public double getAgility(double agility) {
        return agility;
    }

    @Override
    public Upgrade clone() {
        return new GunUpgrade();
    }
}
