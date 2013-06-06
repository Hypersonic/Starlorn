package edu.stuy.starlorn.upgrades;

public class SideShotUpgrade extends GunUpgrade {

    private boolean _left;

    public SideShotUpgrade() {
        super();
        _name = "Side Shot";
        _description = "Everything you know it revolves to the... side";
        _left = false;
    }

    @Override
    public int getNumShots() {
        return 2;
    }

    @Override
    public double getCooldown(double cooldown) {
        return cooldown * 2;
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
    public Upgrade clone() {
        return new SideShotUpgrade();
    }
}
