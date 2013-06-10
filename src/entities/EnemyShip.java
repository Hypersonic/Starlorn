package edu.stuy.starlorn.entities;

import java.awt.Graphics2D;
import java.awt.Color;

import edu.stuy.starlorn.world.Path;
import edu.stuy.starlorn.util.Preferences;

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

    @Override
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

            double targetxvel = maxSpeed * Math.cos(theta);
            double targetyvel = maxSpeed * Math.sin(theta);

            updateSprite("enemy/straight");

            if (targetxvel < 0 && xvel > targetxvel) {
                xvel -= .3;
                updateSprite("enemy/left");
            } else if (targetxvel > 0 && xvel < targetxvel) {
                xvel += .3;
                updateSprite("enemy/right");
            }
            if (targetyvel < 0 && yvel > targetyvel) {
                yvel -= .3;
            } else if (targetyvel > 0 && yvel < targetyvel) {
                yvel += .3;
            }

            if (dist <= maxSpeed)
                pathIndex++;
            if (pathIndex >= path.getPathLength()) {
                kill();
                return;
            }
        }
        super.step();
    }

    @Override
    public void draw(Graphics2D graphics) {
        super.draw(graphics);
        if (Preferences.getValue("devMode") == 1) {
            graphics.setColor(Color.GREEN);
            graphics.drawLine((int) getRect().x + (int) getRect().width/2, (int) getRect().y + (int) getRect().height/2, getPath().getCoords(pathIndex)[0], getPath().getCoords(pathIndex)[1]);
        }
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
