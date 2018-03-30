package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import edu.stuy.starlorn.upgrades.Upgrade;

public class Pickup extends Entity {

    protected Upgrade upgrade;
    protected double speed;
    protected boolean pickedUp, lost;
    protected int lifetime;
    protected Rectangle2D.Double offscreenTarget;

    public Pickup(Upgrade up, double x, double y) {
        super(x, y, up.getSpriteName());
        upgrade = up;
        speed = 5;
        pickedUp = false;
        lost = false;
        lifetime = 300;
        offscreenTarget = new Rectangle2D.Double(0, 0, 0, 0);
        setTargetAngle(2 * Math.PI * (Math.random() - 0.5));
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

    public boolean wasLost() {
        return lost;
    }

    public void setLost(boolean val) {
        lost = val;
    }

    public void setTargetAngle(double angle) {
        final double FAR_AWAY = 1000000000.;
        offscreenTarget.x = Math.cos(angle) * FAR_AWAY;
        offscreenTarget.y = Math.sin(angle) * FAR_AWAY;
    }

    @Override
    public void draw(Graphics2D graphics) {
        if ((!lost && lifetime > 120) || (lifetime / 2) % 3 != 1)
            super.draw(graphics);
    }

    @Override
    public void step() {
        lifetime--;
        if (lifetime <= 0) {
            kill();
            return;
        }

        Rectangle2D.Double target = getTarget();
        double targetx = target.x + target.width / 2,
               targety = target.y + target.height / 2,
               thisx = getRect().x + getRect().width / 2,
               thisy = getRect().y + getRect().height / 2,
               theta = Math.atan2(targety - thisy, targetx - thisx);

        setXvel(getSpeed() * Math.cos(theta));
        setYvel(getSpeed() * Math.sin(theta));
        super.step();

        if (!lost && world.isPlayerAlive() && target.intersects(rect)) {
            pickedUp = true;
            world.getPlayer().addUpgrade(getUpgrade());
            kill();
        }
    }

    protected Rectangle2D.Double getTarget() {
        if (!lost && world.isPlayerAlive())
            return world.getPlayer().getRect();
        return offscreenTarget;
    }

    protected double getSpeed() {
        if (lost)
            return speed * 2;
        return speed;
    }
}
