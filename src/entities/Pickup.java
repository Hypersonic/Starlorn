package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.stuy.starlorn.upgrades.Upgrade;

public class Pickup extends Entity {

    protected Upgrade upgrade;
    protected double speed;
    protected boolean pickedUp;
    protected int lifetime;

    public Pickup(Upgrade up, double x, double y) {
        super(x, y, up.getSpriteName());
        upgrade = up;
        speed = 5;
        pickedUp = false;
        lifetime = 300;
    }

    public Pickup(Upgrade up) {
        this(up, 0, 0);
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public boolean wasPickedUp() {
        return pickedUp;
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (lifetime > 120 || (lifetime / 2) % 3 != 1)
            super.draw(graphics);
    }

    @Override
    public void step() {
        lifetime--;
        if (lifetime <= 0) {
            kill();
            return;
        }

        Rectangle2D.Double target;
        if (world.isPlayerAlive())
            target = world.getPlayer().getRect();
        else
            target = new Rectangle2D.Double(world.getScreen().getWidth() / 2,
                world.getScreen().getHeight() + 32, 0, 0);

        double playerx = target.x + target.width / 2,
               playery = target.y + target.height / 2,
               thisx = getRect().x + getRect().width / 2,
               thisy = getRect().y + getRect().height / 2,
               theta = Math.atan2(playery - thisy, playerx - thisx);

        setXvel(speed * Math.cos(theta));
        setYvel(speed * Math.sin(theta));
        super.step();

        if (world.isPlayerAlive() && target.intersects(rect)) {
            pickedUp = true;
            world.getPlayer().addUpgrade(getUpgrade());
            kill();
        }
    }
}
