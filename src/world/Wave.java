package edu.stuy.starlorn.world;

import edu.stuy.starlorn.entities.EnemyShip;

public class Wave {
    protected Path _path;
    protected EnemyShip _enemyType;
    protected int _numEnemies;
    
    public Wave(Path path, EnemyShip enemyType, int numEnemies) {
        _path = path;
        _enemyType = enemyType;
        _numEnemies = numEnemies;
    }

    public Wave() {
        this(new Path(), new EnemyShip(), 10);
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
}
