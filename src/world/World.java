package edu.stuy.starlorn.world;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.entities.Entity;
import edu.stuy.starlorn.entities.PlayerShip;

/*
 * Represents a world with entities in it
 */
public class World extends DefaultHook {

    private LinkedList<Entity> entities;
    private PlayerShip player;

    public World() {
        entities = new LinkedList<Entity>();
        player = new PlayerShip();
        entities.add(player);
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
    }

}
