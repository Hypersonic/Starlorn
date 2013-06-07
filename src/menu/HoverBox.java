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

    private Rectangle rect;
    private Font font;
    private String label;
    private String name;
    private int xOffset, yOffset;
    private boolean hover;
    private boolean flicker;
    private boolean isflicker;
    private long old;

    public HoverBox(Screen screen, int x, int y, int w, int h, String text,
                  float size, String buttonname) {
        rect = new Rectangle(x, y, w, h);
        font = screen.getFont().deriveFont(size);
        name = buttonname;
        label = KeyEvent.getKeyText(Preferences.getValue(name));
        xOffset = yOffset = -1;
        hover = false;
        flicker = false;
        isflicker = false;
        old = 0;
    }

    public boolean isHover() {
        return hover;
    }

    public void update(MouseEvent event) {
        boolean old = hover;
        if (event.getX() >= rect.x && event.getX() <= rect.x + rect.width
         && event.getY() >= rect.y && event.getY() <= rect.y + rect.height)
            hover = true;
        else
            hover = false;
        if (!old && hover){
            isflicker = true;
        }
    }

    public void update(KeyEvent event) {
        if (isHover() && event.getKeyCode() != KeyEvent.VK_Q){
            label = event.getKeyText(event.getKeyCode());
            Preferences.put(name, event.getKeyCode());
            xOffset = -1;
            isflicker = false;
        }

    }

    public void draw(Graphics2D graphics) {
        if (xOffset == -1) {
            xOffset = (int) (rect.width - font.getStringBounds(label, graphics.getFontRenderContext()).getWidth()) / 2;
            yOffset = (int) (font.getLineMetrics(label, graphics.getFontRenderContext()).getAscent() + rect.height) / 2;
        }

        if (isHover())
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
        }
        else {
            xOffset = (int) (rect.width - font.getStringBounds(label, graphics.getFontRenderContext()).getWidth()) / 2;
        graphics.drawString(label, rect.x + xOffset, rect.y + yOffset);
        }
        graphics.drawString(name, rect.x - rect.width, rect.y + yOffset);
    }
}
