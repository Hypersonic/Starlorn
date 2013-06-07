package edu.stuy.starlorn.graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.event.*;

import edu.stuy.starlorn.graphics.Hook;

public class Screen extends Canvas implements Runnable, KeyListener,
                                              MouseInputListener {

    private static final long serialVersionUID = 1L;
    private static final int MAX_FPS = 60;
    private static final String FONT_FILE = "res/font.ttf";

    private boolean running;
    private long lastTick;
    private Stack<Hook> hooks;
    private JFrame frame;
    private Font font;

    public Screen() {
        running = false;
        lastTick = 0;
        hooks = new Stack<Hook>();
        frame = new JFrame();
        font = loadFont();
    }

    public void setup() {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setPreferredSize(new Dimension(bounds.width, bounds.height));

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        frame.setTitle("Starlorn");
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    private Font loadFont() {
        try {
            InputStream stream = new FileInputStream(FONT_FILE);
            return Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void run() {
        running = true;
        while (alive()) {
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
        graphics.setFont(font);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        hooks.peek().step(graphics);
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

    public void pushHook(Hook hook) {
        hooks.push(hook);
    }

    public Hook popHook() {
        return hooks.pop();
    }

    public Hook peekHook() {
        return hooks.peek();
    }

    public Font getFont() {
        return font;
    }

    public void hideCursor() {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Cursor blankCursor = kit.createCustomCursor(img, new Point(0, 0), "hidden");
        setCursor(blankCursor);
    }

    public void showCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    /* EVENT HANDLERS */

    public void keyTyped(KeyEvent event) {
        for (Hook hook : hooks)
            hook.keyTyped(event);
    }

    public void keyPressed(KeyEvent event) {
        for (Hook hook : hooks)
            hook.keyPressed(event);
    }

    public void keyReleased(KeyEvent event) {
        for (Hook hook : hooks)
            hook.keyReleased(event);
    }

    public void mouseClicked(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mouseClicked(event);
    }

    public void mouseEntered(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mouseEntered(event);
    }

    public void mouseExited(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mouseExited(event);
    }

    public void mousePressed(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mousePressed(event);
    }

    public void mouseReleased(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mouseReleased(event);
    }

    public void mouseDragged(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mouseDragged(event);
    }

    public void mouseMoved(MouseEvent event) {
        for (Hook hook : hooks)
            hook.mouseMoved(event);
    }
}
