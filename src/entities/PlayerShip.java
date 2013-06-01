package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.graphics.Anchor;

public class PlayerShip extends Ship {

    private static final int FRAMES_PER_SPRITE = 3;

    private boolean goingUp, goingDown, goingLeft, goingRight;
    private int frame;

    public PlayerShip() {
        super(600, 200, "player/straight/still");
        goingUp = goingDown = goingLeft = goingRight = false;
        frame = 0;
    }

    public void step() {
        super.step();
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
