package edu.stuy.starlorn.display;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
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
        Display.setVSyncEnabled(true);
        running = true;

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glViewport(0, 0, getWidth(), getHeight());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, getWidth(), 0, getHeight(), 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
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
