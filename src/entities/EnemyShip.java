package edu.stuy.starlorn.entities;

import edu.stuy.starlorn.world.Path;
import edu.stuy.starlorn.upgrades.GunUpgrade;

public class EnemyShip extends Ship {

    protected Path path;
    protected int pathIndex; // Index of our location on the path

    private boolean killedByPlayer, killedByCollision;

    public EnemyShip() {
        super("enemy/straight");
        shootRequested = true; // shoot as often as possible
        baseAim = 3 * Math.PI / 2; // Aim down
        path = null;
        pathIndex = -1;
        killedByPlayer = killedByCollision = false;
    }

    public EnemyShip(Path p) {
        this();
        path = p;
    }

    protected void clone(EnemyShip e) {
        super.clone(e);
        e.path = path;
    }

    public EnemyShip clone() {
        EnemyShip e = new EnemyShip();
        clone(e);
        return e;
    }

    public boolean isHit(Bullet b) {
        if (b.wasFiredByPlayer() && super.isHit(b)) {
            killedByPlayer = true;
            return true;
        }
        return false;
    }

    @Override
    public void step() {
        if (path != null) {
            if (pathIndex == -1) {
                pathIndex++;
                rect.x = path.getCoords(0)[0];
                rect.y = path.getCoords(0)[1];
                if (rect.x <= rect.width)  // Hack to force spawning offscreen
                    rect.x -= rect.width;
            }

            double relativeX = path.getCoords(pathIndex)[0] - rect.x,
                   relativeY = path.getCoords(pathIndex)[1] - rect.y,
                   theta = Math.atan2(relativeY, relativeX),
                   dist = Math.sqrt(Math.pow(relativeX, 2) + Math.pow(relativeY, 2));

            xvel = maxSpeed * Math.cos(theta);
            yvel = maxSpeed * Math.sin(theta);

            if (xvel > 0 && Math.abs(xvel) > Math.abs(yvel))
                updateSprite("enemy/right");
            else if (xvel < 0 && Math.abs(xvel) > Math.abs(yvel))
                updateSprite("enemy/left");
            else
                updateSprite("enemy/straight");


            if (dist <= maxSpeed)
                pathIndex++;
            if (pathIndex >= path.getPathLength()) {
                kill();
                return;
            }
        }
        super.step();
    }

    public long getScoreValue() {
        long score = 100;
        if (killedByCollision)
            score -= 50;
        score += getNumUpgrades() * 25;
        return score;
    }

    @Override
    public Ship getNearestTarget() {
        return world.getPlayer();
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

    public boolean wasKilledByPlayer() {
        return killedByPlayer;
    }

    public void setKilledByPlayer(boolean value) {
        killedByPlayer = value;
    }

    public boolean wasKilledByCollision() {
        return killedByCollision;
    }

    public void setKilledByCollision(boolean value) {
        killedByCollision = value;
    }
}
