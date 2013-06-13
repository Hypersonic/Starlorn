package edu.stuy.starlorn.util;

import edu.stuy.starlorn.world.Path;
import edu.stuy.starlorn.world.Wave;
import edu.stuy.starlorn.world.Level;
import edu.stuy.starlorn.entities.EnemyShip;
import edu.stuy.starlorn.upgrades.*;

public class Generator {
    private static final GunUpgrade[] upgrades = {
        new ScatterShotUpgrade(),
        new TripleShotUpgrade(),
        new DoubleShotUpgrade(),
        new DualShotUpgrade(),
        new SpeedShotUpgrade(),
        new LawnSprinklerUpgrade(),
        new GuidedMissileUpgrade(),
        new SideShotUpgrade(),
        new RapidFireUpgrade()
    };

    public static Path generatePath(int numPoints, int firstx, int firsty) {
        Path p = new Path();
        int screenWidth = Preferences.getValue("screenWidth");
        int screenHeight = Preferences.getValue("screenHeight") / 2;
        p.addCoords(firstx, firsty);
        for (int i = 0; i < numPoints; i++) {
            // Pick locations within the size of the screen / 10 nearby the previous value, and make sure it's in the range of the screen
            int x = (int) (Math.random() * screenWidth);
            int y = (int) (Math.random() * screenHeight);
            p.addCoords(x, y);
        }
        p.addCoords(Math.abs(firstx - screenWidth), (int) (Math.random() *screenHeight)); // Add a point somewhere on the opposite side of spawn
        return p;
    }
    public static Path generatePath(int numPoints) {
        int screenWidth = Preferences.getValue("screenWidth");
        int screenHeight = Preferences.getValue("screenHeight") / 2;
        int firstx = (int) Math.round(Math.random()) * screenWidth; // Spawn us at one edge
        int firsty = (int) (Math.random() * screenHeight);
        return generatePath(numPoints, firstx, firsty);
    }

    public static Path generatePath() {
        return generatePath(1000);
    }

    public static Wave generateWave(int difficulty) {
        Wave wave = new Wave();
        int numEnemies = 2 + difficulty/2;
        EnemyShip enemyType = generateEnemy(difficulty);
        int intermission = (int) Math.ceil(200.0 / difficulty);
        wave.setEnemyType(enemyType);
        wave.setNumEnemies(numEnemies);
        wave.setIntermission(intermission);
        return wave;
    }

    public static Wave generateWave() {
        return generateWave(1);
    }

    public static Level generateLevel(int numWaves, int difficulty) {
        Level level = new Level();
        for (int i = 0; i < numWaves; i++) {
            level.addWave(generateWave(difficulty));
        }
        return level;
    }

    public static Level generateLevel(int number) {
        return generateLevel(number + 1, number * 3);
    }

    public static EnemyShip generateEnemy(int difficulty) {
        Path path = generatePath(difficulty + 5);
        EnemyShip enemy = new EnemyShip(path);
        int shotSpeed = (int) (1 + Math.random() * difficulty) + 5;
        int cooldown = (int) ((Math.random() * (100 / difficulty) + 35) / (Math.log10(difficulty) + 1));
        int maxSpeed = 3 + (int) Math.ceil(Math.random() * Math.log10(difficulty)) * 5;
        enemy.setBaseShotSpeed(shotSpeed);
        enemy.setBaseCooldown(cooldown);
        enemy.setMovementSpeed(maxSpeed);
        return enemy;
    }

    public static EnemyShip generateEnemy() {
        return generateEnemy(1);
    }

    public static GunUpgrade getRandomUpgrade() {
        return (GunUpgrade) upgrades[(int) (Math.random() * upgrades.length)].clone();
    }
}
