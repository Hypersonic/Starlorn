package edu.stuy.starlorn.menu;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Button extends Rectangle {

  private boolean _pressed;

    public Button(double x, double y, double h, double w) {
        super(x, y, h, w);
        _pressed = false;
    }

    public boolean isPressed() { return _pressed; }

    public boolean setPressed(boolean p){
        boolean temp = _pressed;
        _pressed = p;
        return temp;
    }

    public void update(int delta){
        super.update(delta);
        if (Mouse.isButtonDown(0)){
            _pressed = true;
        }
        else
            _pressed = false;
    }
}
