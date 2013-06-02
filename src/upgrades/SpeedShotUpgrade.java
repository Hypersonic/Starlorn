package edu.stuy.starlorn.upgrades;

public class SpeedShotUpgrade extends GunUpgrade {
    public SpeedShotUpgrade() {
        super();
        _name = "Speed Shot";
        _description = "Shot Speed x 2!";
    }

    @Override
    public int getShotSpeed(int shotspeed) {
        return shotspeed * 2;
    }
    @Override
    public Upgrade clone() {
        return new SpeedShotUpgrade();
    }
}
