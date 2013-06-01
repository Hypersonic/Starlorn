package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.upgrades.Upgrade;

public class Pickup extends Entity {
    protected Upgrade upgrade;

    public Pickup(Upgrade up) {
        super(up.getSpriteName());
        upgrade = up;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    @Override
    public void step() {
        double xdiff = (double) world.getPlayer().getRect().getX() - getRect().getX();
        double ydiff = (double) world.getPlayer().getRect().getY() - getRect().getY();
        setXvel(xdiff / Math.abs(xdiff));
        setYvel(ydiff / Math.abs(ydiff));
        super.step();
    }

}
