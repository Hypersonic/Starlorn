package edu.stuy.starlorn.util;

import edu.stuy.starlorn.world.Path;
import edu.stuy.starlorn.world.Wave;
import edu.stuy.starlorn.world.Level;
import edu.stuy.starlorn.entities.EnemyShip;

public class Generator {
    public static Path generatePath(int numPoints) {
        Path p = new Path();
        int screenWidth = 100;
        int screenHeight = 100;
        for (int i = 0; i < numPoints; i++) {
            int x = (int) (Math.random() * screenWidth);
            int y = (int) (Math.random() * screenHeight);
            p.addCoords(x, y);
        }
        return p;
    }

    public static Path generatePath() {
        return Path.generatePath(10);
    }

    public static EnemyShip generateEnemy() {
        EnemyShip enemy = new EnemyShip();
        Path path = generatePath();
        int damage = (int) (Math.random() * 4); 
        int shotSpeed = (int) (Math.random() * 4);
        int health = (int) (Math.random() * 20);
        int cooldown = (int) (Math.random() * 20);
        int cooldownRate = (int) (Math.random() * 5);
        int movementSpeed = (int) (Math.random() * 4);
        enemy.setPath(path);
        enemy.setBaseDamage(damage);
        enemy.setBaseShotSpeed(shotSpeed);
        enemy.setMaxHealth(health);
        enemy.setCooldown(cooldown);
        enemy.setCooldownRate(cooldownRate);
        enemy.setMovementSpeed(movementSpeed);
        return enemy;
    }
}
