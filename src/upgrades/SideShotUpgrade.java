package edu.stuy.starlorn.upgrades;

public class SideShotUpgrade extends GunUpgrade {

    private boolean _left;

    public SideShotUpgrade() {
        super();
        _name = "Side Shot";
        _description = "Everything you own in a box to the... side";
        _left = false;
    }

    @Override
    public int getNumShots() {
        return 2;
    }

    @Override
    public double getCooldown(double cooldown) {
        return 1.5 * cooldown;
    }

    @Override
    public double getAimAngle() {
        if (_left){
            _left = false;
            return 0 - (Math.PI / 2);
        }
        else {
            _left = true;
            return (Math.PI / 2);
        }
    }

    @Override
    public double getXOffset() {
        if (_left) return 15;
        else return -15;
    }

    @Override
    public String getSpriteName() {
        return "upgrade/sideshot";
    }

    @Override
    public Upgrade clone() {
        return new SideShotUpgrade();
    }
}
