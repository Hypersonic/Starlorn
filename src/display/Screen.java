package edu.stuy.starlorn.display;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.concurrent.atomic.*;

import javax.swing.*;
import javax.swing.event.*;

import edu.stuy.starlorn.display.Hook;

public class Screen extends Canvas implements Runnable, MouseListener,
        MouseMotionListener, ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int MAX_FPS = 60;

    private boolean running;
    private long lastTick;
    private AtomicBoolean[] flags;
    private AtomicInteger turn;
    private ArrayList<Hook> hooks;
    private JFrame frame;

    public Screen() {
        addMouseListener(this);
        addMouseMotionListener(this);

        running = false;
        lastTick = 0;
        flags = new AtomicBoolean[2];
        for (int i = 0; i < flags.length; i++)
            flags[i] = new AtomicBoolean();
        turn = new AtomicInteger();
        hooks = new ArrayList<Hook>();
        frame = new JFrame();
    }

    public void setup() {
        frame.setTitle("Starlorn");
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void addHook(Hook hook) {
        hooks.add(hook);
    }

    public void removeHook(Hook hook) {
        hooks.remove(hook);
    }

    public void run() {
        running = true;
        while (alive()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_Q))     // FIXME
                break;
            render();
            tick();
        }
    }

    private void render() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(1);
            return;
        }
        Graphics graphics = strategy.getDrawGraphics();
        graphics.clearRect(0, 0, getWidth(), getHeight());  // necessary?
        for (Hook hook : hooks)
            hook.step(graphics);
        graphics.dispose();
        strategy.show();

    }

    private void tick() {
        long since = System.currentTimeMillis() - lastTick;
        if (since < 1000 / MAX_FPS) {
            try {
                Thread.sleep(1000 / MAX_FPS - since);
            } catch (InterruptedException e) {
                return;
            }
        }
        lastTick = System.currentTimeMillis();
    }

    public boolean alive() {
        return running;
    }

    public void shutdown() {
        running = false;
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    /* Acquire a lock so two threads don't modify the grid at the same time. */
    public void acquireLock(int thread) {
        int other = (thread == 0 ? 1 : 0);
        flags[thread].set(true);
        while (flags[other].get() == true) {
            if (turn.get() != thread) {
                flags[thread].set(false);
                while (turn.get() != thread) {}
                flags[thread].set(true);
            }
        }
    }

    /* Release the lock when a thread is finished modifying the grid. */
    public void releaseLock(int thread) {
        int other = (thread == 0 ? 1 : 0);
        turn.set(other);
        flags[thread].set(false);
    }
}
