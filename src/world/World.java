package edu.stuy.starlorn.world;

import edu.stuy.starlorn.entities.Entity;

import java.util.ArrayList;

/*
 * Represents a world with entities in it
 */
public class World {
    private ArrayList<Entity> _entities;

    public World() {
        _entities = new ArrayList<Entity>();
    }

    public void addEntity(Entity e) {
        _entities.add(e);
    }

    public void stepAll() {
        for (Entity e : _entities) {
            e.preStep();
            e.step();
            e.postStep();
        }
    }
}
