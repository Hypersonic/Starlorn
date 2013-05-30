package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Graphics2D;

import edu.stuy.starlorn.display.*;

public class Menu extends DefaultHook {

    private Screen screen;
    private long lastFrame;

    private Button[] buttons;
    private Star[] stars;

    public Menu(Screen scr) {
        screen = scr;
    }

    public void setup() {
        int cx = screen.getWidth() / 2,
            cy = screen.getHeight() / 2;

        buttons = new Button[4];
        buttons[0] = new Button(cx - 200, cy - 160, 400, 120, "Play", 48, screen);
        buttons[1] = new Button(cx - 200, cy - 20, 400, 80, "High Scores", 28, screen);
        buttons[2] = new Button(cx - 200, cy + 80, 190, 80, "Settings", 18, screen);
        buttons[3] = new Button(cx + 10, cy + 80, 190, 80, "Quit", 18, screen);

        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    public void step(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        for (Star star: stars) {
            star.update();
            star.draw(graphics);
        }
        for (Button button : buttons) {
            button.update();
            button.draw(graphics);
        }
    }

}
