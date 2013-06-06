package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.world.World;

public class Settings extends DefaultHook {

    private Screen screen;
    private Font bigFont, smallFont;
    private long lastFrame;
    private HoverBox[] hoverBoxes;
    private Button[] buttons;
    private Star[] stars;

    public Settings(Screen scr) {
        screen = scr;
        bigFont = screen.getFont().deriveFont(64f);
        smallFont = screen.getFont().deriveFont(11f);
    }

    public void setup() {
        int cx = screen.getWidth() / 2,
            cy = screen.getHeight() / 2;

        buttons = new Button[1];
   
        buttons[0] = new Button(screen, cx + 10, cy + 80, 190, 80, "Back", 18f,
                                new BackButtonCallback());

        hoverBoxes = new HoverBox[1];
        hoverBoxes[0] = new HoverBox(screen, cx - 200, cy - 160, 190, 80, "W", 18f);

        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    public interface Callback {
        void invoke();
    }

    private class BackButtonCallback implements Callback {
        public void invoke() {
            Menu menu = new Menu(screen);
            menu.setup();
            screen.removeHook(Settings.this);
            screen.addHook(menu);
        }
    }

    public void step(Graphics2D graphics) {
        drawTitle(graphics);
        graphics.setColor(Color.WHITE);
        for (Star star : stars) {
            star.update();
            star.draw(graphics);
        }
        for (Button button : buttons)
            button.draw(graphics);
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.draw(graphics);
    }

    private void drawTitle(Graphics2D graphics) {
        String text1 = "SETTINGS";
        String text2 = "";
        int xOffset1 = (int) (screen.getWidth() - bigFont.getStringBounds(text1, graphics.getFontRenderContext()).getWidth()) / 2;
        int xOffset2 = (int) (screen.getWidth() - smallFont.getStringBounds(text2, graphics.getFontRenderContext()).getWidth()) / 2;
        graphics.setColor(Color.GRAY);
        graphics.setFont(bigFont);
        graphics.drawString(text1, xOffset1, screen.getHeight() / 2 - 250);
        graphics.setColor(Color.WHITE);
        graphics.setFont(smallFont);
        graphics.drawString(text2, xOffset2, screen.getHeight() / 2 - 200);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_Q)
            new BackButtonCallback().invoke();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
    }
}
