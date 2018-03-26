package edu.stuy.starlorn.upgrades;

public class StutterShotUpgrade extends GunUpgrade {
    protected boolean _fast_shot;

    public StutterShotUpgrade() {
        super();
        _name = "Stutter Shot";
        _description = "P-Pew P-Pew P-Pew!";
        _fast_shot = false;
    }

    @Override
    public double getShotSpeed(double shotspeed) {
        return shotspeed;
    }

    @Override
    public double getCooldown(double cooldown) {
        _fast_shot = !_fast_shot;
        if (_fast_shot) {
            return cooldown * 3 / 2;
        } else {
            return cooldown / 2;
        }
    }

    @Override
    public String getSpriteName() {
        return "upgrade/stuttershot";
    }

    @Override
    public Upgrade clone() {
        return new StutterShotUpgrade();
    }
}

