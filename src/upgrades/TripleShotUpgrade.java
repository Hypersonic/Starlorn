package edu.stuy.starlorn.upgrades;

public class TripleShotUpgrade extends GunUpgrade {
    private int _shotNum;

    public TripleShotUpgrade() {
        super();
        _shotNum = 0;
        _name = "Triple Shot";
        _description = "Thrice the awesome!";
    }

    @Override
    public int getNumShots() {
        return 3;
    }

    @Override
    public double getAimAngle() {
        int direction = _shotNum - 1;
        _shotNum++;
        if (_shotNum > 2) _shotNum = 0;
        return direction * Math.PI/8;
    }

    @Override
    public Upgrade clone() {
        return new TripleShotUpgrade();
    }
}
