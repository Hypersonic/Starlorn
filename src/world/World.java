package edu.stuy.starlorn.world;

import java.awt.Graphics2D;
import java.util.LinkedList;

import edu.stuy.starlorn.display.DefaultHook;
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
}
