package edu.stuy.starlorn.display;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import edu.stuy.starlorn.display.Hook;

public class Screen extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final int MAX_FPS = 60;

    private boolean running;
    private long lastTick;
    private ArrayList<Hook> hooks;
    private JFrame frame;

    public Screen() {
        // addMouseListener(this);

        running = false;
        lastTick = 0;
        hooks = new ArrayList<Hook>();
        frame = new JFrame();
    }

    public void setup() {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setPreferredSize(new Dimension(bounds.width, bounds.height));
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
            // if (Keyboard.isKeyDown(Keyboard.KEY_Q))     // FIXME
            //     break;
            render();
            tick();
        }
    }

    private void render() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(2);
            return;
        }
        Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
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
}
