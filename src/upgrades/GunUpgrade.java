package edu.stuy.starlorn.upgrades;

/*
 * Upgrades that modify a ship's gun/shots
 */
public class GunUpgrade extends Upgrade {

    public GunUpgrade() {
        super();
        _name = "Unnamed gun upgrade";
        _description = "Gun Upgrade!";
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
     * Gives a number of coordinates to offset the shot's origin
     * on the X axis from wherever shots would normally come from.
     * Not garunteed to return the same thing each time, should probably keep track of which shot this is (like getAimAngle)
     * Should be chosen based off of getNumShots
     */
    public int getXOffset() {
        return 0;
    }
    /* 
     * Given the amount of damage that would be done, tell how much should be done.
     * Multipliers, buffs, etc go here.
     * All should be applied
     */
    public int getDamage(int damage) {
        return damage;
    }
    /*
     * Get the cooldown between shots, in ticks, based off the previous one
     */
    public int getCooldown(int cooldown) {
        return cooldown;
    }
    /*
     * Get the speed the shot should move at
     */
    public int getShotSpeed(int shotspeed) {
        return shotspeed;
    }

    @Override
    public Upgrade clone() {
        return new GunUpgrade();
    }
}
