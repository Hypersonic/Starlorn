package edu.stuy.starlorn.entities;

public class Bullet extends Entity {

    protected int speed;
    protected boolean firedByPlayer, isSeeking;

    public Bullet(double angle, int speed, String sprite) {
        super(sprite);
        this.angle = angle;
        this.speed = speed;
        firedByPlayer = false;
        isSeeking = false;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
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
