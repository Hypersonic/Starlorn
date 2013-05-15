package edu.stuy.upgrades;

/*
 * Upgrades that modify a ship's gun/shots
 */
public class GunUpgrade extends Upgrade {

    /*
     * Gives an angle this ship should aim at, measured in radians,
     * From the vertical axis.
     */
    public double getAimAngle() {
        return 0; // Aim straight.
    }
    /* 
     * Given the amount of damage that would be done, tell how much should be done.
     */
    public int getDamage(int damage) {
        return damage;
    }
}
