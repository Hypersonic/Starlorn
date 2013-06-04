package edu.stuy.starlorn.upgrades;

public class GuidedMissileUpgrade extends GunUpgrade {
    public GuidedMissileUpgrade() {
        super();
        _name = "Guided Missile";
        _description = "Auto-targets the nearest enemy!";
    }

    @Override
    public boolean getSeeking(boolean seeking) {
        return true;
    }

    @Override
    public double getAgility(double agility) {
        return agility + 0.01;
    }

    @Override
    public Upgrade clone() {
        return new GuidedMissileUpgrade();
    }
}
