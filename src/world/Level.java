package edu.stuy.starlorn.world;

import edu.stuy.starlorn.world.Wave;

import java.util.LinkedList;

public class Level {
    protected LinkedList<Wave> _waves;
    protected int _num;

    public Level() {
        _waves = new LinkedList<Wave>();
        _num = 0;
    }

    public void addWave(Wave w) {
        _waves.add(w);
        _num++;
    }

    public Wave peekWave() {
        return _waves.peek();
    }

    public Wave popWave() {
        return _waves.pop();
    }

    public int numWaves() {
        return _num;
    }

    public boolean isLastWave() {
        return peekWave() == null;
    }
}
