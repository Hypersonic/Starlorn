package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.world.Path;
import edu.stuy.starlorn.upgrades.GunUpgrade;

public class EnemyShip extends Ship {
    protected Path _path;
    protected int _pathIndex; // Index of our location on the path

    public EnemyShip() {
        super();
        _shootRequested = true; // shoot as often as possible
        _baseAim = Math.PI/2; // Aim down
        _path = null;
        _pathIndex = 0;
    }

    public EnemyShip(Path p) {
        this();
        _path = p;
    }

    public EnemyShip clone() {
        EnemyShip e = new EnemyShip();
        e.setXY(_xcor, _ycor);
        e.setPath(_path);
        return e;
    }

    @Override
    public void preStep() {
        super.preStep();
        // If we're at our goal coordinate on the path, advance our goal and set our velocity to aim at the next goal
        if (Math.round(_xcor) == Math.round(_path.getCoords(_pathIndex)[0])
        && Math.round(_ycor) == Math.round(_path.getCoords(_pathIndex)[1])) {
            _pathIndex++;
            int relativeX = (int) _xcor - _path.getCoords(_pathIndex)[0];
            int relativeY = (int) _ycor - _path.getCoords(_pathIndex)[1];
            double theta = Math.atan2(relativeX, relativeY);
            _xvel = _movementSpeed * Math.cos(theta);
            _yvel = _movementSpeed * Math.sin(theta);
        }

        // If we're done with our path, just despawn
        if (_pathIndex >= _path.getPathLength()) {
            _world.removeEntity(this);
        }
    }

    public void setPath(Path p) {
        _path = p;
    }

    public Path getPath() {
        return _path;
    }

    public int getPathIndex() {
        return _pathIndex;
    }

}
