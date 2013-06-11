package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;

public class Settings extends DefaultHook {

    private Screen screen;
    private Font font;
    private long lastFrame;
    private HoverBox[] hoverBoxes;
    private Button[] buttons;
    private Star[] stars;

    public Settings(Screen scr) {
        screen = scr;
        font = screen.getFont().deriveFont(48f);
    }

    public void setup() {
        int cx = screen.getWidth() / 2,
            cy = screen.getHeight() / 2;

        buttons = new Button[1];

        buttons[0] = new Button(screen, cx - 95, cy + 300, 190, 80, "Back",
            18f, new BackButtonCallback());

        hoverBoxes = new HoverBox[6];
        hoverBoxes[0] = new HoverBox(screen, cx - 200, cy - 50, 100, 50, HoverBox.ABOVE, "Up"   , 18f, "upKey"   );
        hoverBoxes[1] = new HoverBox(screen, cx - 200, cy     , 100, 50, HoverBox.BELOW, "Down" , 18f, "downKey" );
        hoverBoxes[2] = new HoverBox(screen, cx - 300, cy     , 100, 50, HoverBox.BELOW, "Left" , 18f, "leftKey" );
        hoverBoxes[3] = new HoverBox(screen, cx - 100, cy     , 100, 50, HoverBox.BELOW, "Right", 18f, "rightKey");
        hoverBoxes[4] = new HoverBox(screen, cx + 200, cy - 50, 150, 50, HoverBox.LEFT , "Shoot", 18f, "shootKey");
        hoverBoxes[5] = new HoverBox(screen, cx + 200, cy + 25, 150, 50, HoverBox.LEFT , "Pause", 18f, "pauseKey");

        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    private class BackButtonCallback implements Menu.Callback {
        public void invoke() {
            Menu menu = new Menu(screen);
            menu.setup();
            screen.popHook();
            screen.pushHook(menu);
        }
    }

    @Override
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
        String text = "SETTINGS";
        int xOffset = screen.getXOffset(graphics, font, text);
        graphics.setColor(Color.GRAY);
        graphics.setFont(font);
        graphics.drawString(text, xOffset, screen.getHeight() / 2 - 250);
    }

    @Override
    public void keyReleased(KeyEvent event) {
         for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
        if (event.getKeyCode() == KeyEvent.VK_Q)
            new BackButtonCallback().invoke();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
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
