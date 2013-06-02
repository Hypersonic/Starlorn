package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;

import edu.stuy.starlorn.graphics.Anchor;
import edu.stuy.starlorn.upgrades.GunUpgrade;

public class PlayerShip extends Ship {

    private static final int FRAMES_PER_SPRITE = 3;

    private boolean goingUp, goingDown, goingLeft, goingRight;
    private int frame, invincibility;

    public PlayerShip(double displayx, double displayy) {
        super("player/straight/still");
        rect.x = displayx / 2 - rect.width / 2;
        rect.y = displayy - 200;
        goingUp = goingDown = goingLeft = goingRight = false;
        frame = 0;
        invincibility = 90;
    }

    protected Bullet spawnBullet(GunUpgrade topShot, int shotSpeed) {
        Bullet b = super.spawnBullet(topShot, shotSpeed);
        b.setFiredByPlayer(true);
        return b;
    }

    public boolean isHit(Bullet b) {
        return !b.wasFiredByPlayer() && invincibility == 0 && super.isHit(b);
    }

    public void draw(Graphics2D graphics) {
        if ((invincibility / 2) % 3 != 1)
            super.draw(graphics);
    }

    public void step() {
        if (goingUp) {
            yvel--;
            if (yvel < -maxSpeed)
                yvel = -maxSpeed;
        }
        if (goingDown) {
            yvel++;
            if (yvel > maxSpeed)
                yvel = maxSpeed;
        }
        if (!goingUp && !goingDown) {
            if (yvel > 0)
                yvel--;
            else if (yvel < 0)
                yvel++;
        }

        if (goingLeft) {
            xvel--;
            if (xvel < -maxSpeed)
                xvel = -maxSpeed;
        }
        if (goingRight) {
            xvel++;
            if (xvel > maxSpeed)
                xvel = maxSpeed;
        }
        if (!goingLeft && !goingRight) {
            if (xvel > 0)
                xvel--;
            else if (xvel < 0)
                xvel++;
        }
        super.step();
        keepOnScreen();
        if (invincibility > 0)
            invincibility--;

        String spritename = "player/";
        Anchor anchor;
        if (xvel < 0) {
            spritename += "left/";
            anchor = Anchor.TOP_RIGHT;
        }
        else if (xvel > 0) {
            spritename += "right/";
            anchor = Anchor.TOP_LEFT;
        }
        else {
            spritename += "straight/";
            if (sprite.getName().startsWith("player/left"))
                anchor = Anchor.TOP_RIGHT;
            else if (sprite.getName().startsWith("player/right"))
                anchor = Anchor.TOP_LEFT;
            else
                anchor = Anchor.TOP_CENTER;
        }
        if (yvel == 0)
            spritename += "still";
        else if (frame < FRAMES_PER_SPRITE)
            spritename += "fast";
        else
            spritename += "slow";
        updateSprite(spritename, anchor);
        frame++;
        if (frame >= FRAMES_PER_SPRITE * 2)
            frame = 0;
    }

    public void setGoingUp(boolean value) {
        goingUp = value;
    }

    public void setGoingDown(boolean value) {
        goingDown = value;
    }

    public void setGoingLeft(boolean value) {
        goingLeft = value;
    }

    public void setGoingRight(boolean value) {
        goingRight = value;
    }
}
