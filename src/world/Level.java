package edu.stuy.world;

import edu.stuy.starlorn.entities.Entity;

import java.util.LinkedList;

public class Level {
    protected LinkedList<Entity> _enemies;

    public Level() {
        _enemies = new LinkedList<Entity>();
    }
}
