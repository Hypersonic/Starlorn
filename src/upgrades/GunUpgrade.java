package edu.stuy.starlorn.upgrades;

/*
 * Upgrades that modify a ship's gun/shots
 */
public class GunUpgrade extends Upgrade {


    public GunUpgrade() {
        super();
    }

    /*
     * Returns the number of shots this upgrade allows for.
     * The player's ship should choose the highest of these from its upgrades.
     */
    public int getNumShots() {
        return 1;
    }
    /*
     * Gives an angle this ship should aim at, measured in radians,
     * From the vertical axis.
     * It should use the value given by the upgrade with the highest numShots
     * Called for each shot, so it should probably keep track of which one it's at.
     */
    public double getAimAngle() {
        return 0; // Aim straight.
    }
    /*
     * Gives a number of coordinates to offset the shot's origin
     * on the X axis from wherever shots would normally come from.
     * Not garunteed to return the same thing each time, should probably keep track of which shot this is (like getAimAngle)
     */
    public int getXOffset() {
        return 0;
    }
    /* 
     * Given the amount of damage that would be done, tell how much should be done.
     * Multipliers, buffs, etc go here.
     */
    public int getDamage(int damage) {
        return damage;
    }
}
