package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.util.Preferences;

public class HoverBox {

    public static int ABOVE = 0, RIGHT = 1, BELOW = 2, LEFT = 3;

    private Rectangle rect;
    private Font font;
    private String label;
    private String value;
    private String button;
    private int xOffset, yOffset;
    private boolean hover, pressed;
    private boolean flicker;
    private boolean isflicker;
    private long old;
    private int labeldirection;

    public HoverBox(Screen screen, int x, int y, int w, int h, int dir,
                    String text, float size, String buttonname) {
        rect = new Rectangle(x, y, w, h);
        font = screen.getFont().deriveFont(size);
        label = text;
        button = buttonname;
        value = KeyEvent.getKeyText(Preferences.getValue(button));
        xOffset = yOffset = -1;
        labeldirection = dir;
        hover = false;
        flicker = false;
        isflicker = false;
        old = 0;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isHover() {
        return hover;
    }

    public void update(MouseEvent event) {
        boolean old = pressed;
        int mask = (event.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK);
        boolean buttonDown = mask == MouseEvent.BUTTON1_DOWN_MASK;

        if (pressed && !buttonDown) {
            pressed = false;
        }
        if (event.getX() >= rect.x && event.getX() <= rect.x + rect.width
         && event.getY() >= rect.y && event.getY() <= rect.y + rect.height)
            hover = true;
        else
            hover = false;

        if (hover && buttonDown)
            pressed = true;
        else
            pressed = false;

        if (!old && pressed){
            isflicker = true;
        }
        if (old && !pressed){
            isflicker = false;
        }
    }

    public void update(KeyEvent event) {
        if (isflicker && event.getKeyCode() != KeyEvent.VK_Q){
            value = event.getKeyText(event.getKeyCode());
            Preferences.put(button, event.getKeyCode());
            xOffset = -1;
        }
        pressed = false;
        isflicker = false;
    }

    public void draw(Graphics2D graphics) {
        if (xOffset == -1) {
            xOffset = (int) (rect.width - font.getStringBounds(label, graphics.getFontRenderContext()).getWidth()) / 2;
            yOffset = (int) (font.getLineMetrics(label, graphics.getFontRenderContext()).getAscent() + rect.height) / 2;
        }

        if (pressed)
            graphics.setColor(Color.RED);
        else if (hover)
            graphics.setColor(Color.GREEN);
        else
            graphics.setColor(Color.WHITE);


        graphics.fill(rect);
        graphics.setColor(Color.GRAY);
        graphics.setFont(font);
        if (isHover() && isflicker){
            xOffset = (int) (rect.width - font.getStringBounds("_", graphics.getFontRenderContext()).getWidth()) / 2;
            if (flicker){
                graphics.drawString("_", rect.x + xOffset, rect.y + yOffset);
            }
            if (java.lang.System.currentTimeMillis() - old >= 500){
                old = java.lang.System.currentTimeMillis();
                if (flicker)
                    flicker = false;
                if (!flicker)
                    flicker = true;
            }
        } else {
            xOffset = (int) (rect.width - font.getStringBounds(value, graphics.getFontRenderContext()).getWidth()) / 2;
            graphics.drawString(value, rect.x + xOffset, rect.y + yOffset);
        }

        int xcenterthis = (int) (rect.width - font.getStringBounds(label, graphics.getFontRenderContext()).getWidth()) / 2;
        int ycenterthis = (int) (rect.height + font.getStringBounds(label, graphics.getFontRenderContext()).getHeight()) / 2;
        if (labeldirection == ABOVE)
            graphics.drawString(label, rect.x + xcenterthis, rect.y - ycenterthis);
        else if (labeldirection == RIGHT)
            graphics.drawString(label, rect.x + rect.width + xcenterthis, rect.y + ycenterthis);
        else if (labeldirection == BELOW)
            graphics.drawString(label, rect.x + xcenterthis, rect.y + rect.height + ycenterthis);
        else // left (default)
            graphics.drawString(label, rect.x - rect.width + xcenterthis, rect.y + ycenterthis);
    }
}
