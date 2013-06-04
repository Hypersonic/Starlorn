package edu.stuy.starlorn.upgrades;

public class LawnSprinklerUpgrade extends GunUpgrade {

    private double _direction;
    private boolean _where;

    public LawnSprinklerUpgrade() {
        super();
        _name = "Lawn Sprinkler";
        _description = "_.-^-._.-^-._.-^-._.-^-._";
        _direction = -0.2;
    }

    @Override
    public double getAimAngle() {
        if (_where)
            _direction += 0.2;
        if (!_where)
            _direction -= 0.2;
        if (_direction >= 1.6)
            _where = false;
        if (_direction <= 0)
            _where = true;
        return (Math.PI * (0.8 - _direction) / 2);
    }

    @Override
    public double getCooldown(double cooldown) {
        return cooldown * 0.75;
    }

    @Override
    public Upgrade clone() {
        return new LawnSprinklerUpgrade();
    }
}
