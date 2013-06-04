package edu.stuy.starlorn.upgrades;

public class SideShotUpgrade extends GunUpgrade {

    private boolean _left;

    public SideShotUpgrade() {
        super();
        _name = "Side Shot";
        _description = "Crap.";
        _left = false;
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
