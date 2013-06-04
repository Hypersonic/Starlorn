package edu.stuy.starlorn.entities;

public class Bullet extends Entity {

    protected double speed, agility;
    protected boolean firedByPlayer, isSeeking;
    protected Ship target;

    public Bullet(String sprite, double angle, double speed) {
        super(sprite);
        this.angle = angle;
        this.speed = speed;
        firedByPlayer = isSeeking = false;
        setXvel(speed * Math.cos(angle));
        setYvel(speed * -Math.sin(angle));
    }

    @Override
    public void step() {
        if (isSeeking)
            seekTarget();
        super.step();
        if (!onScreen())
            kill();
        for (Ship that : world.getShips()) {
            if (that.isHit(this))
                explode(that);
        }
    }

    private void seekTarget() {
        if (target == null || target.isDead()) {
            isSeeking = false;
            return;
        }

        double targetx = target.getRect().x + target.getRect().width / 2,
               targety = target.getRect().y + target.getRect().height / 2,
               xdiff = targetx - (rect.x + rect.width / 2),
               ydiff = targety - (rect.y + rect.height / 2),
               targetAngle = -Math.atan2(ydiff, xdiff),
               delta = Math.atan2(Math.sin(targetAngle - angle),
                                  Math.cos(targetAngle - angle));

        if (Math.abs(delta) <= agility)
            angle += delta;
        else if (delta > 0)
            angle += agility;
        else
            angle -= agility;

        setXvel(speed * Math.cos(angle));
        setYvel(speed * -Math.sin(angle));
    }

    private void explode(Ship that) {
        this.kill();
        that.kill();
        Explosion e = new Explosion();
        double thatcx = that.getRect().x + that.getRect().width / 2,
               thatcy = that.getRect().y + that.getRect().height / 2;
        e.getRect().x = thatcx - e.getRect().width;
        e.getRect().y = thatcy - e.getRect().height;
        world.addEntity(e);
    }

    public void seek(double agility, Ship target) {
        isSeeking = true;
        this.agility = agility;
        this.target = target;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSeeking(boolean seek) {
        this.isSeeking = seek;
    }

    public boolean getSeeking() {
        return isSeeking;
    }

    public void setFiredByPlayer(boolean value) {
        firedByPlayer = value;
    }

    public boolean wasFiredByPlayer() {
        return firedByPlayer;
    }
}
