package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.world.World;

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
        buttons[0] = new Button(screen, cx - 200, cy - 160, 400, 120, "Play", 48,
                                new PlayButtonCallback());
        buttons[1] = new Button(screen, cx - 200, cy - 20, 400, 80, "High Scores", 28,
                                new HighScoresButtonCallback());
        buttons[2] = new Button(screen, cx - 200, cy + 80, 190, 80, "Settings", 18,
                                new SettingsButtonCallback());
        buttons[3] = new Button(screen, cx + 10, cy + 80, 190, 80, "Quit", 18,
                                new QuitButtonCallback());

        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    public interface Callback {
        void invoke();
    }

    private class PlayButtonCallback implements Callback {
        public void invoke() {
            World world = new World(screen);
            screen.removeHook(Menu.this);
            screen.addHook(world);
        }
    }

    private class HighScoresButtonCallback implements Callback {
        public void invoke() {
            System.out.println("Not implemented yet!");
        }
    }

    private class SettingsButtonCallback implements Callback {
        public void invoke() {
            System.out.println("Not implemented yet!");
        }
    }

    private class QuitButtonCallback implements Callback {
        public void invoke() {
            screen.shutdown();
        }
    }

    public void step(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        for (Star star : stars) {
            star.update();
            star.draw(graphics);
        }
        for (Button button : buttons)
            button.draw(graphics);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_P)
            new PlayButtonCallback().invoke();
        else if (event.getKeyCode() == KeyEvent.VK_H)
            new HighScoresButtonCallback().invoke();
        else if (event.getKeyCode() == KeyEvent.VK_S)
            new SettingsButtonCallback().invoke();
        else if (event.getKeyCode() == KeyEvent.VK_Q)
            new QuitButtonCallback().invoke();
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
    }
}
