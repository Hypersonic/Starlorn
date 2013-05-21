package edu.stuy.starlorn.menu;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import edu.stuy.starlorn.display.Screen;

public class Menu {

    private Screen screen;
    private long lastFrame;

    private Star[] stars;
    private Button[] button;

    public Menu(Screen scr) {
        screen = scr;
    }

    public void setup() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, screen.getWidth(), 0, screen.getHeight(), 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        stars = new Star[400];
        GL11.glColor3d(1, 1, 1);
        for (int i = 0; i < 400; i++) {
            stars[i] = new Star(1080, 720, 0.3);
        }

        GL11.glColor3d(.5, 0, 0);
        button = new Button[1];
        button[0] = new Button(400, 300, 100, 100);
    }

    public void loop() {
        getDelta();
        while (screen.alive()) {
            update(getDelta());
            render();
            screen.tick();
        }
    }

    private void update(int delta) {
        for (Star star: stars)
            star.update(delta);
        button[0].update(delta);
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
        GL11.glColor3f(1f, 1.0f, 1f);
        for (Rectangle x: stars)
            x.draw();
        if (button[0].isHover())
            GL11.glColor3d(0, .5, 0);
        if (button[0].isPressed())
            GL11.glColor3d(.5, 0, 0);
        button[0].draw();
    }
}
