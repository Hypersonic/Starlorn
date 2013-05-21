package edu.stuy.starlorn.display;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;

public class Screen {

    private DisplayMode mode;
    private boolean running;

    private static final int MAX_FPS = 60;

    public Screen() {
        mode = null;
        running = false;
    }

    public void setup() {
        mode = Display.getDesktopDisplayMode();
        try {
            Display.setDisplayModeAndFullscreen(mode);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        running = true;
    }

    public void tick() {
        Display.update();
        Display.sync(MAX_FPS);
    }

    public boolean alive() {
        return running && !Display.isCloseRequested();
    }

    public void shutdown() {
        running = false;
        Display.destroy();
    }

    public int getWidth() {
        return mode.getWidth();
    }

    public int getHeight() {
        return mode.getHeight();
    }
}
