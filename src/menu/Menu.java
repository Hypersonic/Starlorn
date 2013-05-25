package edu.stuy.starlorn.menu;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import edu.stuy.starlorn.display.Screen;

public class Menu {

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

    public void loop() {
        getDelta();
        while (screen.alive()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_Q))
                break;
            update(getDelta());
            render();
            screen.tick();
        }
    }

    private void update(int delta) {
        for (Star star: stars)
            star.update(delta);
        for (Button button : buttons)
            button.update(delta);
    }

    /**
     * Calculate how many milliseconds have passed since last frame.
     *
     * @return milliseconds passed since last frame
     */
    private int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        for (Rectangle x: stars)
            x.draw();
        for (Button button : buttons)
            button.draw();
    }
}
