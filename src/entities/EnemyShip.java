package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.world.Path;
import edu.stuy.starlorn.upgrades.GunUpgrade;

public class EnemyShip extends Ship {
    protected Path path;
    protected int pathIndex; // Index of our location on the path

    public EnemyShip() {
        super("enemy/straight");
        shootRequested = true; // shoot as often as possible
        baseAim = 3 * Math.PI / 2; // Aim down
        path = null;
        pathIndex = 0;
    }

    public EnemyShip(Path p) {
        this();
        path = p;
    }

    public EnemyShip clone() {
        EnemyShip e = (EnemyShip) super.clone();
        e.path = path;
        return e;
    }

    @Override
    public void step() {
        if (path != null) {
            // If we're at our goal coordinate on the path, advance our goal
            // and set our velocity to aim at the next goal
            if (Math.round(rect.x) == Math.round(path.getCoords(pathIndex)[0])
             && Math.round(rect.y) == Math.round(path.getCoords(pathIndex)[1])) {
                pathIndex++;
                int relativeX = (int) rect.x - path.getCoords(pathIndex)[0];
                int relativeY = (int) rect.y - path.getCoords(pathIndex)[1];
                double theta = Math.atan2(relativeX, relativeY);
                xvel = maxSpeed * Math.cos(theta);
                yvel = maxSpeed * Math.sin(theta);
            }

            // If we're done with our path, just despawn
            if (pathIndex >= path.getPathLength()) {
                kill();
                return;
            }
        }
        super.step();
    }

    public void setPath(Path p) {
        path = p;
    }

    public Path getPath() {
        return path;
    }

    public int getPathIndex() {
        return pathIndex;
    }

}
