package edu.stuy.starlorn.upgrades;

public class LawnSprinklerUpgrade extends GunUpgrade {

    private double _direction;

    public LawnSprinklerUpgrade() {
        super();
        _name = "Lawn Sprinkler";
        _description = "_.-^-._.-^-._";
        _direction = -0.2;
    }

    @Override
    public double getAimAngle() {
        _direction += 0.2;
        if (_direction >= 1.6)
            _direction = 0;
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
