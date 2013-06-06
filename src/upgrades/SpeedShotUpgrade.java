package edu.stuy.starlorn.upgrades;

public class SpeedShotUpgrade extends GunUpgrade {
    public SpeedShotUpgrade() {
        super();
        _name = "Speed Shot";
        _description = "Shot Speed x 2!";
    }

    @Override
    public double getCooldown(double cooldown) {
        return cooldown / 3;
    }

    @Override
    public Upgrade clone() {
        return new SpeedShotUpgrade();
    }
}
