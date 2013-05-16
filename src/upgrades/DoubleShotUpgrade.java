package edu.stuy.starlorn.upgrades;

public class DoubleShotUpgrade extends GunUpgrade {
    private boolean _shotNum;

    public DoubleShotUpgrade() {
        super();
        _shotNum = false;
    }

    @Override
    public int getNumShots() {
        return 2;
    }

    @Override
    public double getAimAngle() {
        int direction = 0;
        if (_shotNum) {
            _shotNum = !_shotNum;
            direction = 1;
        } else if (!_shotNum) {
            _shotNum = !_shotNum;
            direction = -1;
        }
        return direction * Math.PI/8;
    }
}
