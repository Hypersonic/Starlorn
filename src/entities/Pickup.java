package edu.stuy.starlorn.entities;

import java.awt.geom.Rectangle2D;

import edu.stuy.starlorn.upgrades.Upgrade;

public class Pickup extends Entity {

    protected Upgrade upgrade;
    protected double speed;

    public Pickup(Upgrade up, double x, double y) {
        super(x, y, up.getSpriteName());
        upgrade = up;
        speed = 5;
    }

    public Pickup(Upgrade up) {
        this(up, 0, 0);
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    @Override
    public void step() {
        Rectangle2D.Double playerRect = world.getPlayer().getRect();
        double playerx = playerRect.x + playerRect.width / 2,
               playery = playerRect.y + playerRect.height / 2,
               thisx = getRect().x + getRect().width / 2,
               thisy = getRect().y + getRect().height / 2,
               theta = Math.atan2(playery - thisy, playerx - thisx);

        setXvel(speed * Math.cos(theta));
        setYvel(speed * Math.sin(theta));
        super.step();
        if (playerRect.intersects(rect)) {
            world.getPlayer().addUpgrade(getUpgrade());
            kill();
        }
    }
}
