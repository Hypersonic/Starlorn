package edu.stuy.starlorn.upgrades;

public class DoubleShotUpgrade extends GunUpgrade {
    private boolean _right;

    public DoubleShotUpgrade() {
        super();
        _right = true;
        _name = "Double Shot";
        _description = "2x the fun!";
    }

    @Override
    public int getNumShots() {
        return 2;
    }

    @Override
    public double getAimAngle() {
        int direction = 0;
        if (_right) {
            _right = !_right;
            direction = 1;
        } else if (!_right) {
            _right = !_right;
            direction = -1;
        }
        return direction * Math.PI/8;
    }

    @Override
    public Upgrade clone() {
        return new DoubleShotUpgrade();
    }
}
