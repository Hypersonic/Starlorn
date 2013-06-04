package edu.stuy.starlorn.upgrades;

import edu.stuy.starlorn.entities.Ship;

public class GuidedMissileUpgrade extends GunUpgrade {
    public GuidedMissileUpgrade() {
        super();
        _name = "Guided Missile";
        _description = "Auto-targets the nearest enemy!";
    }

    @Override
    public String[] getSprites(String[] sprites, Ship ship) {
        return new String[]{
            "bullet/missile/1", "bullet/missile/2", "bullet/missile/3",
            "bullet/missile/4", "bullet/missile/5"};
    }

    @Override
    public double getCooldown(double cooldown) {
        return cooldown * 1.25;
    }

    @Override
    public double getShotSpeed(double shotspeed) {
        return shotspeed * 0.9;
    }

    @Override
    public boolean getSeeking(boolean seeking) {
        return true;
    }

    @Override
    public double getAgility(double agility) {
        return agility + (0.02 / (1 + 3 * agility));
    }

    @Override
    public Upgrade clone() {
        return new GuidedMissileUpgrade();
    }
}
