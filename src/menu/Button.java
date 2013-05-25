package edu.stuy.starlorn.menu;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Button extends Rectangle {

    private TrueTypeFont font;
    private String label;
    private boolean pressed;
    private boolean hover;

    private static final String FONT_FILE = "res/font/prstartk.ttf";

    public Button(double x, double y, double w, double h, String text, float size) {
        super(x, y, w, h);
        label = text;
        loadFont(size);
        pressed = false;
    }

    private void loadFont(float size) {
        try {
            InputStream stream = ResourceLoader.getResourceAsStream(FONT_FILE);
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, stream);
            awtFont = awtFont.deriveFont(size);
            font = new TrueTypeFont(awtFont, true);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        else
            hover = false;

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

        GL11.glEnable(GL11.GL_BLEND);
        font.drawString((int) getXcor(), (int) getYcor(), label, Color.gray);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
