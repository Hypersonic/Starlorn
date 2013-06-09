package edu.stuy.starlorn.upgrades;

public class RapidFireUpgrade extends GunUpgrade {

    public RapidFireUpgrade() {
        super();
        _name = "Rapidfire";
        _description = "pew pew -> pew pew pew pew";
    }

    @Override
    public double getCooldown(double cooldown) {
        return cooldown/2;
    }

    @Override
    public Upgrade clone() {
        return new RapidFireUpgrade();
    }

}
