package edu.stuy.starlorn.upgrades;

public class LawnSprinklerUpgrade extends GunUpgrade {

    private double _direction;

    public LawnSprinklerUpgrade() {
        super();
        _name = "Lawn Sprinkler";
        _description = "¸.·´¯`·.´¯`·.¸¸.·´¯`·.¸";

    }

    @Override
    public double getAimAngle() {
        if (_direction >= 2){
            _direction = 0;
        }
        else
            _direction += 0.1;
        // Random angle going forwards
        return (Math.PI * (1 - _direction) / 2);
    }

    @Override
    public Upgrade clone() {
        return new LawnSprinklerUpgrade();
    }
}
