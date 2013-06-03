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
        setXvel(speed * Math.cos(-angle));
        setYvel(speed * Math.sin(-angle));
    }

    @Override
    public void step() {
        if (isSeeking){
            setXvel(speed * Math.cos(-angle));
            setYvel(speed * Math.sin(-angle));
        }
        super.step();
        if (!onScreen())
            kill();

        for (Ship that : world.getShips()) {
            if (that.isHit(this)) {
                this.kill();
                that.kill();
                Explosion e = new Explosion();
                double thatcx = that.getRect().x + that.getRect().width / 2,
                       thatcy = that.getRect().y + that.getRect().height / 2;
                e.getRect().x = thatcx - e.getRect().width;
                e.getRect().y = thatcy - e.getRect().height;
                world.addEntity(e);
            }
        }
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
