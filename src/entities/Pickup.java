package edu.stuy.starlorn.entities;

import java.awt.geom.Rectangle2D;

import edu.stuy.starlorn.upgrades.Upgrade;

public class Pickup extends Entity {

    protected Upgrade upgrade;
    protected double speed;

    public Pickup(Upgrade up) {
        super(up.getSpriteName());
        upgrade = up;
        speed = 5;
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
               theta = Math.atan2(playery - thisy, playerx - thisx),
               dist = Math.sqrt(Math.pow(playerx - thisx, 2) + Math.pow(playery - thisy, 2));

        setXvel(speed * Math.cos(theta));
        setYvel(speed * Math.sin(theta));
        super.step();
        if (dist < 50) {
            world.getPlayer().addUpgrade(getUpgrade());
            kill();
        }
    }
}
