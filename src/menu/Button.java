package edu.stuy.starlorn.menu;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Button extends Rectangle {

    private boolean pressed;
    private boolean hover;

    public Button(double x, double y, double w, double h) {
        super(x, y, w, h);
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isHover() {
        return hover;
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

    @Override
    public void draw() {
        if (isPressed())
            GL11.glColor3d(.5, 0, 0);
        else if (isHover())
            GL11.glColor3d(0, .5, 0);
        else
            GL11.glColor3d(1, 1, 1);
        super.draw();
    }
}
