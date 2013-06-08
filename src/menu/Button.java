package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import edu.stuy.starlorn.graphics.Screen;

public class Button {

    private Rectangle rect;
    private Menu.Callback callback;
    private Font font;
    private String label;
    private int xOffset, yOffset;
    private boolean pressed, hover;

    public Button(Screen screen, int x, int y, int w, int h, String text,
                  float size, Menu.Callback cb) {
        rect = new Rectangle(x, y, w, h);
        callback = cb;
        font = screen.getFont().deriveFont(size);
        label = text;
        xOffset = yOffset = -1;
        pressed = hover = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isHover() {
        return hover;
    }

    public void update(MouseEvent event) {
        int mask = (event.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK);
        boolean buttonDown = mask == MouseEvent.BUTTON1_DOWN_MASK;

        if (pressed && !buttonDown) {
            pressed = false;
            callback.invoke();
            return;
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
    }

    public void draw(Graphics2D graphics) {
        if (xOffset == -1) {
            xOffset = (int) (rect.width - font.getStringBounds(label, graphics.getFontRenderContext()).getWidth()) / 2;
            yOffset = (int) (font.getLineMetrics(label, graphics.getFontRenderContext()).getAscent() + rect.height) / 2;
        }

        if (isPressed())
            graphics.setColor(Color.RED);
        else if (isHover())
            graphics.setColor(Color.GREEN);
        else
            graphics.setColor(Color.WHITE);
        graphics.fill(rect);
        graphics.setColor(Color.GRAY);
        graphics.setFont(font);
        graphics.drawString(label, rect.x + xOffset, rect.y + yOffset);
    }
}
