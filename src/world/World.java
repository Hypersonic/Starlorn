package edu.stuy.starlorn.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.entities.Entity;
import edu.stuy.starlorn.entities.EnemyShip;
import edu.stuy.starlorn.entities.PlayerShip;
import edu.stuy.starlorn.util.Generator;
import edu.stuy.starlorn.util.Preferences;

/*
 * Represents a world with entities in it
 */
public class World extends DefaultHook {

    private Screen screen;
    private Font smallFont, bigFont;
    private ConcurrentLinkedQueue<Entity> entities;
    private PlayerShip player;
    private Level level;
    private Wave wave;
    private int ticks, levelNo, waveNo, spawnedInWave;

    public World(Screen scr) {
        screen = scr;
        smallFont = screen.getFont().deriveFont(12f);
        bigFont = screen.getFont().deriveFont(36f);
        entities = new ConcurrentLinkedQueue<Entity>();
        player = new PlayerShip(screen.getWidth(), screen.getHeight());
        player.setWorld(this);
        level = Generator.generateLevel(1);
        wave = level.popWave();
        ticks = spawnedInWave = 0;
        levelNo = waveNo = 1;
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void step(Graphics2D graphics) {
        doProgress();
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity entity = it.next();
            entity.step();
            if (entity.isDead())
                it.remove();
            else
                entity.draw(graphics);
        }

        graphics.setFont(smallFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Level " + levelNo + ", Wave " + waveNo, 50, 50);
        graphics.drawString("Remaining: " + countLivingEnemies() + "/" + spawnedInWave, 50, 80);
        graphics.setColor(Color.GRAY);
        graphics.drawString("Ticks: " + ticks, 50, 110);

        if (spawnedInWave == wave.getNumEnemies() && countLivingEnemies() == 0) {
            graphics.setFont(bigFont);
            graphics.setColor(Color.YELLOW);
            if (level.peekWave() == null && ticks >= 30 && ticks <= 270)
                graphics.drawString("LEVEL " + levelNo + " COMPLETE", 500, 350);
            else if (ticks >= 30 && ticks <= 90)
                graphics.drawString("WAVE " + waveNo + " COMPLETE", 500, 350);
        }
    }

    private void doProgress() {
        if (spawnedInWave < wave.getNumEnemies()) {
            if (ticks < wave.getIntermission())
                ticks++;
            else {
                spawnedInWave++;
                ticks = 0;
                EnemyShip ship = wave.getEnemyType().clone();
                ship.setWorld(this);
            }
        }
        else if (countLivingEnemies() == 0) {
            if (level.peekWave() == null && ticks == 300) {
                levelNo++;
                waveNo = 1;
                ticks = 0;
                spawnedInWave = 0;
                level = Generator.generateLevel(levelNo);
                wave = level.popWave();
            }
            else if (level.peekWave() != null && ticks == 120) {
                waveNo++;
                ticks = 0;
                spawnedInWave = 0;
                wave = level.popWave();
            }
            else {
                ticks++;
            }
        }
    }

    private int countLivingEnemies() {
        int num = 0;
        for (Entity entity : entities) {
            if (entity instanceof EnemyShip)
                num++;
        }
        return num;
    }

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == Preferences.getValue("upKey"))
            player.setGoingUp(true);
        else if (event.getKeyCode() == Preferences.getValue("leftKey"))
            player.setGoingLeft(true);
        else if (event.getKeyCode() == Preferences.getValue("downKey"))
            player.setGoingDown(true);
        else if (event.getKeyCode() == Preferences.getValue("rightKey"))
            player.setGoingRight(true);
        else if (event.getKeyCode() == Preferences.getValue("shootKey"))
            player.setShootRequested(true);
    }

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == Preferences.getValue("upKey"))
            player.setGoingUp(false);
        else if (event.getKeyCode() == Preferences.getValue("leftKey"))
            player.setGoingLeft(false);
        else if (event.getKeyCode() == Preferences.getValue("downKey"))
            player.setGoingDown(false);
        else if (event.getKeyCode() == Preferences.getValue("rightKey"))
            player.setGoingRight(false);
        else if (event.getKeyCode() == Preferences.getValue("shootKey"))
            player.setShootRequested(false);
    }

    public Screen getScreen() {
        return screen;
    }

    public PlayerShip getPlayer() {
        return player;
    }
}
