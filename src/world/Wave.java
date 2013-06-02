package edu.stuy.starlorn.world;

import edu.stuy.starlorn.entities.EnemyShip;

public class Wave {
    protected Path _path;
    protected EnemyShip _enemyType;
    protected int _numEnemies;
    protected int _intermission;

    public Wave(Path path, EnemyShip enemyType, int numEnemies, int intermission) {
        _path = path;
        _enemyType = enemyType;
        _numEnemies = numEnemies;
        _intermission = intermission;
    }

    public Wave() {
        this(new Path(), new EnemyShip(), 10, 60);
    }

    public Path getPath() {
        return _path;
    }

    public void setPath(Path p) {
        _path = p;
    }

    public EnemyShip getEnemyType() {
        return _enemyType;
    }

    public void setEnemyType(EnemyShip e) {
        _enemyType = e;
    }

    public int getNumEnemies() {
        return _numEnemies;
    }

    public void setNumEnemies(int n) {
        _numEnemies = n;
    }

    public int getIntermission() {
        return _intermission;
    }

    public void setIntermission(int intermission) {
        _intermission = intermission;
    }
}
