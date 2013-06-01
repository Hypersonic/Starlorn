package edu.stuy.starlorn.world;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.entities.Entity;
import edu.stuy.starlorn.entities.PlayerShip;

/*
 * Represents a world with entities in it
 */
public class World extends DefaultHook {

    private Screen screen;
    private ConcurrentLinkedQueue<Entity> entities;
    private PlayerShip player;

    public World(Screen scr) {
        screen = scr;
        entities = new ConcurrentLinkedQueue<Entity>();
        player = new PlayerShip(screen.getWidth(), screen.getHeight());
        player.setWorld(this);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void step(Graphics2D graphics) {
        for (Entity e : entities) {
            e.preStep();
        }
        for (Entity e : entities) {
            e.step();
        }
        for (Entity e : entities) {
            e.postStep();
        }
        for (Entity e : entities) {
            e.draw(graphics);
        }
    }

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W)
            player.setGoingUp(true);
        else if (event.getKeyCode() == KeyEvent.VK_A)
            player.setGoingLeft(true);
        else if (event.getKeyCode() == KeyEvent.VK_S)
            player.setGoingDown(true);
        else if (event.getKeyCode() == KeyEvent.VK_D)
            player.setGoingRight(true);
        else if (event.getKeyCode() == KeyEvent.VK_SPACE)
            player.setShootRequested(true);
    }

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W)
            player.setGoingUp(false);
        else if (event.getKeyCode() == KeyEvent.VK_A)
            player.setGoingLeft(false);
        else if (event.getKeyCode() == KeyEvent.VK_S)
            player.setGoingDown(false);
        else if (event.getKeyCode() == KeyEvent.VK_D)
            player.setGoingRight(false);
        else if (event.getKeyCode() == KeyEvent.VK_SPACE)
            player.setShootRequested(false);
    }

    public Screen getScreen() {
        return screen;
    }
}
