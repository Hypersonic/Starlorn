package edu.stuy.starlorn.menu;

import java.awt.Graphics;

import edu.stuy.starlorn.display.*;

public class Menu implements Hook {

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
        buttons[0] = new Button(cx - 200, cy + 20, 400, 120, "Play", 48);
        buttons[1] = new Button(cx - 200, cy - 80, 400, 80, "High Scores", 36);
        buttons[2] = new Button(cx - 200, cy - 180, 190, 80, "Settings", 24);
        buttons[3] = new Button(cx + 10, cy - 180, 190, 80, "Quit", 24);

        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    public void step(Graphics graphics) {
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
