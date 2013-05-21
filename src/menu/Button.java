package edu.stuy.starlorn.menu;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Button extends Rectangle {

    private boolean pressed;
    private boolean hover;

    public Button(double x, double y, double h, double w) {
        super(x, y, h, w);
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean setPressed(boolean p) {
        boolean temp = pressed;
        pressed = p;
        return temp;
    }

    public boolean isHover() {
        return hover;
    }

    public boolean setHover(boolean p) {
        boolean temp = hover;
        hover = p;
        return temp;
    }

    public void update(int delta) {
        super.update(delta);
        if (Mouse.getX() >= getXcor() && Mouse.getX() <= getXcor() + getWidth()
                && Mouse.getY() >= getYcor()
                && Mouse.getY() <= getYcor() + getHeight()) {
            hover = true;
        }
        else hover = false;

        if (Mouse.isButtonDown(0) && hover)
            pressed = true;
        else
            pressed = false;
    }
}
