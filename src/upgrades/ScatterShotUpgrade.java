package edu.stuy.starlorn.upgrades;

public class ScatterShotUpgrade extends GunUpgrade {

    public ScatterShotUpgrade() {
        super();
        _name = "Scatter Shot";
        _description = "Skrillex x Foreign Beggars";
    }

    @Override
    public int getNumShots() {
        return 4;
    }

    @Override
    public double getAimAngle() {
        // Random angle going forwards
        return Math.PI * (2.0/3.0) * (Math.random() - .5);
    }

    @Override
    public double getShotSpeed(double shotspeed) {
        // Random speed, either 1, 2, or 3
        return Math.random() * 3 + shotspeed;
    }

    @Override
    public double getCooldown(double cooldown) {
        return cooldown * 3;
    }

    @Override
    public String getSpriteName() {
        return "upgrade/scattershot";
    }

    @Override
    public Upgrade clone() {
        return new ScatterShotUpgrade();
    }
}
