package edu.stuy.starlorn.upgrades;

public class DoubleShotUpgrade extends GunUpgrade {
    private int _shotNum;

    public DoubleShotUpgrade() {
        super();
        _shotNum = 0;
    }

    @Override
    public int getNumShots() {
        return 2;
    }

    @Override
    public double getAimAngle() {
        if (_shotNum == 0) {
            _shotNum++;
            return Math.PI/8;
        } else if (_shotNum == 1) {
            _shotNum--;
            return -Math.PI/8;
        } else { // If those failed, something weird is going on, reset and aim straight.
            _shotNum = 0;
            return 0;
        }
    }
}
