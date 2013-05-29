package edu.stuy.world;

import edu.stuy.starlorn.entities.Entity;
import edu.stuy.starlorn.world.Wave;

import java.util.LinkedList;

public class Level {
    protected LinkedList<Wave> _waves;

    public Level() {
        _waves = new LinkedList<Wave>();
    }

    public void addWave(Wave w) {
        _waves.add(w);
    }

    public Wave peekWave() {
        return _waves.peek();
    }
    
    public Wave popWave() {
        return _waves.pop();
    }
}
