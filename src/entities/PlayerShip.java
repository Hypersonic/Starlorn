package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.graphics.Sprite;

public class PlayerShip extends Ship {

    private boolean goingUp, goingDown, goingLeft, goingRight;

    public PlayerShip() {
        super(600, 200, "playerShip");
        goingUp = goingDown = goingLeft = goingRight = false;
    }

    public void step() {
        super.step();
        if (goingUp)
            rect.y -= 5;
        if (goingDown)
            rect.y += 5;
        if (goingLeft)
            rect.x -= 5;
        if (goingRight)
            rect.x += 5;
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
