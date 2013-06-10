package edu.stuy.starlorn.upgrades;

public class DualShotUpgrade extends GunUpgrade {
    private boolean _right;

    public DualShotUpgrade() {
        super();
        _right = true;
        _name = "Twin Shot";
        _description = "Twins, man!";
    }

    @Override
    public int getNumShots() {
        return 2;
    }

    @Override
    public double getXOffset() {
        int direction = 0;
        if (_right) {
            _right = !_right;
            direction = 1;
        } else if (!_right) {
            _right = !_right;
            direction = -1;
        }
        return direction * 20;
    }

    @Override
    public Upgrade clone() {
        return new DualShotUpgrade();
    }
}
