package edu.stuy.starlorn.world;

import edu.stuy.starlorn.entities.Entity;

import java.util.LinkedList;

/*
 * Represents a world with entities in it
 */
public class World {
    private LinkedList<Entity> _entities;

    public World() {
        _entities = new LinkedList<Entity>();
    }

    public void addEntity(Entity e) {
        _entities.add(e);
    }

    public void removeEntity(Entity e) {
        _entities.remove(e);
    }

    public void stepAll() {
        for (Entity e : _entities) {
            e.preStep();
        }
        for (Entity e : _entities) {
            e.step();
        }
        for (Entity e : _entities) {
            e.postStep();
        }
    }
}
