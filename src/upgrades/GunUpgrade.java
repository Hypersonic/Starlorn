package edu.stuy.upgrades;

/*
 * Static methods because you don't need an instance, just values.
 */
public class GunUpgrade extends Upgrade {

    /*
     * Gives an angle this ship should aim at, measured in radians,
     * From the vertical axis.
     */
    public static double getAimAngle() {
        return 0; // Aim straight.
    }
    /* 
     * Given the amount of damage that would be done, tell how much should be done.
     */
    public static int getDamage(int damage) {
        return damage;
    }
}
