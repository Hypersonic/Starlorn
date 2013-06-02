package edu.stuy.starlorn.entities;

public class Bullet extends Entity {

    protected int speed;
    protected boolean firedByPlayer;

    public Bullet() {
        super("bullet/blue/long");
    }

    public Bullet(double angle, int speed) {
        this();
        this.angle = angle;
        this.speed = speed;
        firedByPlayer = false;
    }

    @Override
    public void step() {
        setXvel(speed * Math.cos(-angle));
        setYvel(speed * Math.sin(-angle));
        super.step();
        if (!onScreen())
            kill();

        for (Ship that : world.getShips()) {
            if (that.isHit(this)) {
                this.kill();
                that.kill();
                Explosion e = new Explosion();
                e.getRect().x = this.getRect().x + this.getRect().width/2;
                e.getRect().y = this.getRect().y + this.getRect().height/2;
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

    public void setFiredByPlayer(boolean value) {
        firedByPlayer = value;
    }

    public boolean wasFiredByPlayer() {
        return firedByPlayer;
    }
}
