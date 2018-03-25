package edu.stuy.starlorn.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.graphics.Sprite;
import edu.stuy.starlorn.entities.Bullet;
import edu.stuy.starlorn.entities.EnemyShip;
import edu.stuy.starlorn.entities.Entity;
import edu.stuy.starlorn.entities.Pickup;
import edu.stuy.starlorn.entities.PlayerShip;
import edu.stuy.starlorn.entities.ScorePopup;
import edu.stuy.starlorn.entities.Ship;
import edu.stuy.starlorn.highscores.HighScores;
import edu.stuy.starlorn.highscores.NewHighScoreScreen;
import edu.stuy.starlorn.menu.Menu;
import edu.stuy.starlorn.upgrades.Upgrade;
import edu.stuy.starlorn.util.Generator;
import edu.stuy.starlorn.util.Preferences;

/*
 * Represents a world with entities in it
 */
public class World extends DefaultHook {

    private Screen screen;
    private Font smallFont, mediumFont, bigFont;
    private ConcurrentLinkedQueue<Entity> entities;
    private ArrayList<Ship> ships;
    private Star[] stars;
    private PlayerShip player;
    private ArrayList<Pickup> pickups;
    private Level level;
    private Wave wave;
    private long score;
    private int lives, levelNo, waveNo, spawnedInWave, spawnedInLevel,
                killedInLevel, remaining;
    private int successfulShots, allShots, successfulShotsLevel, allShotsLevel;
    private int spawnTicks, respawnTicks;
    private boolean paused, playerAlive, waitForPickup, quitRequested;
    private Sprite lifeSprite;
    private Rectangle2D lifeRect;
    private Upgrade upgrade;

    public World(Screen scr) {
        screen = scr;
        smallFont = screen.getFont().deriveFont(14f);
        mediumFont = screen.getFont().deriveFont(24f);
        bigFont = screen.getFont().deriveFont(36f);
        entities = new ConcurrentLinkedQueue<Entity>();
        ships = new ArrayList<Ship>();
        stars = new Star[250];
        for (int i = 0; i < 250; i++)
            stars[i] = new Star(Math.random() * screen.getWidth(),
                                Math.random() * screen.getHeight());
        player = new PlayerShip(screen.getWidth(), screen.getHeight());
        player.setInvincibility(0);
        player.setWorld(this);
        ships.add(player);
        pickups = new ArrayList<Pickup>();
        level = Generator.generateLevel(1);
        wave = level.popWave();

        lives = 3;
        levelNo = waveNo = 1;
        score = spawnedInWave = spawnedInLevel = killedInLevel = remaining = 0;
        successfulShots = allShots = successfulShotsLevel = allShotsLevel = 0;
        spawnTicks = respawnTicks = 0;
        playerAlive = true;
        paused = waitForPickup = quitRequested = false;
        lifeSprite = Sprite.getSprite("hud/lives");
        lifeRect = new Rectangle2D.Double(50, screen.getHeight() - 50 - lifeSprite.getHeight() / 2,
            lifeSprite.getWidth(), lifeSprite.getHeight());
        upgrade = null;
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    @Override
    public void step(Graphics2D graphics) {
        stepLevelProgress();
        stepStars(graphics);
        stepEntities(graphics);
        drawHUD(graphics);
    }

    private void stepLevelProgress() {
        if (paused || quitRequested)
            return;
        if (spawnedInWave < wave.getNumEnemies()) {
            if (spawnTicks < wave.getIntermission())
                spawnTicks++;
            else
                spawnEnemy();
        }
        else if (remaining == 0) {
            if (!level.isLastWave() && spawnTicks == 120)
                spawnNextWave();
            else if (level.isLastWave() && spawnTicks == 300)
                spawnNextLevel();
            else if (!waitForPickup && playerAlive)
                spawnTicks++;
        }
        if (!playerAlive) {
            if (lives > 0 && respawnTicks == 60)
                spawnPlayer();
            else if (lives == 0 && respawnTicks == 180)
                endGame();
            else
                respawnTicks++;
        }
    }

    private void spawnEnemy() {
        spawnedInWave++;
        spawnedInLevel++;
        remaining++;
        spawnTicks = 0;
        EnemyShip ship = wave.getEnemyType().clone();
        int speed = ship.getMovementSpeed() + ((int) (2 * (Math.random() - .5)));
        ship.setMovementSpeed(speed);
        ship.setPath(Generator.generatePath(ship.getPath().getPathLength(),
                     ship.getPath().getCoords(0)[0], ship.getPath().getCoords(0)[1]));
        int rarity = 1;
        while (Math.random() * rarity < ((double) levelNo) / (5 + levelNo)) {
            ship.addUpgrade(Generator.getRandomUpgrade());
            rarity++;
        }
        ship.setWorld(this);
        ships.add(ship);
    }

    private void spawnNextWave() {
        waveNo++;
        spawnTicks = spawnedInWave = remaining = 0;
        wave = level.popWave();
    }

    private void spawnNextLevel() {
        levelNo++;
        waveNo = 1;
        spawnTicks = spawnedInWave = spawnedInLevel = killedInLevel = remaining = 0;
        successfulShotsLevel = allShotsLevel = 0;
        upgrade = null;
        level = Generator.generateLevel(levelNo);
        wave = level.popWave();
    }

    private void spawnPlayer() {
        player = new PlayerShip(screen.getWidth(), screen.getHeight());
        player.setWorld(this);
        ships.add(player);
        playerAlive = true;
        respawnTicks = 0;
    }

    private void stepStars(Graphics2D graphics) {
        for (Star star : stars) {
            if (paused || quitRequested) {
                star.draw(graphics);
                continue;
            }
            star.step();
            if (star.y >= screen.getHeight()) {
                star.y = 0;
                star.x = Math.random() * screen.getWidth();
            }
            star.draw(graphics);
        }
    }

    private void stepEntities(Graphics2D graphics) {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity entity = it.next();
            if (paused || quitRequested) {
                entity.draw(graphics);
                continue;
            }
            entity.step();
            if (entity.isDead()) {
                if (entity instanceof PlayerShip)
                    killPlayer((PlayerShip) entity);
                else if (entity instanceof EnemyShip)
                    killEnemy((EnemyShip) entity);
                else if (entity instanceof Bullet)
                    killBullet((Bullet) entity);
                else if (entity instanceof Pickup) {
                    waitForPickup = false;
                    upgrade = ((Pickup) entity).getUpgrade();
                    double yoff = 150 - 8 + 25 * pickups.size();
                    pickups.add(new Pickup(upgrade, 75, yoff));
                }
                it.remove();
            }
            else
                entity.draw(graphics);
        }
    }

    private void killPlayer(PlayerShip player) {
        playerAlive = false;
        lives--;
        ships.remove(player);
        pickups.clear();
    }

    private void killEnemy(EnemyShip enemy) {
        remaining--;
        if (enemy.wasKilledByPlayer()) {
            score += enemy.getScoreValue();
            killedInLevel++;
            ScorePopup popup = new ScorePopup(screen, enemy);
            popup.setWorld(this);
            if (level.isLastWave() && killedInLevel == spawnedInLevel &&
                    spawnedInWave == wave.getNumEnemies())
                spawnPickup(enemy);
        }
        ships.remove(enemy);
    }

    private void killBullet(Bullet bullet) {
        if (bullet.wasFiredByPlayer()) {
            if (bullet.wasSuccessful()) {
                successfulShots++;
                successfulShotsLevel++;
            }
            allShots++;
            allShotsLevel++;
        }
    }

    private void drawHUD(Graphics2D graphics) {
        graphics.setFont(smallFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("Score: %s",
                            new DecimalFormat("#,###").format(score)), 50, 50);
        graphics.drawString(String.format("Level %d, Wave %d/%d", levelNo,
                            waveNo, level.numWaves()), 50, 75);

        if (pickups.size() > 0) {
            graphics.drawString("Upgrades:", 50, 125);
            for (Pickup pickup : pickups) {
                Upgrade up = pickup.getUpgrade();
                Rectangle2D rect = pickup.getRect();
                int yoff = (int) (rect.getY() + rect.getHeight() - 2);
                pickup.draw(graphics);
                graphics.setColor(Color.WHITE);
                graphics.drawString(up.getName(), 100, yoff);
            }
        }

        String text = "x" + lives;
        int yOffset = (int) (smallFont.getLineMetrics(text, graphics.getFontRenderContext()).getAscent() / 2);
        graphics.drawString(text, 60 + lifeSprite.getWidth(), screen.getHeight() - 50 + yOffset);
        graphics.setPaint(lifeSprite.getPaint(lifeRect));
        graphics.fill(lifeRect);

        if (Preferences.getValue("devMode") == 1)
            drawDevUI(graphics);
        if (paused)
            drawPaused(graphics);
        if (quitRequested)
            drawQuitConfirmation(graphics);
        if (lives == 0 || (spawnedInWave == wave.getNumEnemies() && remaining == 0))
            drawLevelProgress(graphics);
    }

    private void drawDevUI(Graphics2D graphics) {
        int xoff = screen.getWidth() - 400;
        graphics.setColor(Color.GRAY);
        graphics.drawString(String.format("Entities: %d", entities.size()), xoff, 50);
        graphics.drawString(String.format("Time until next spawn: %d", wave.getIntermission()-spawnTicks), xoff, 75);
        graphics.drawString(String.format("Enemies left in wave: %d", wave.getNumEnemies()-spawnedInWave), xoff, 100);
        graphics.drawString(String.format("Cooldown timer: %d", player.getCooldownTimer()), xoff, 125);
        graphics.drawString(String.format("Accuracy: %4d/%4d = %.1f%%", successfulShots, allShots, getAccuracy()), xoff, 150);
        graphics.drawString(String.format(" (level): %4d/%4d = %.1f%%", successfulShotsLevel, allShotsLevel, getAccuracyLevel()), xoff, 175);
    }

    private double getAccuracy() {
        if (allShots == 0)
            return 0;
        return ((double) successfulShots) / allShots * 100;
    }

    private double getAccuracyLevel() {
        if (allShotsLevel == 0)
            return 0;
        return ((double) successfulShotsLevel) / allShotsLevel * 100;
    }

    private void drawPaused(Graphics2D graphics) {
        String message = "PAUSED";
        int xOffset = screen.getXOffset(graphics, bigFont, message);
        graphics.setFont(bigFont);
        graphics.setColor(Color.GRAY);
        graphics.drawString(message, xOffset, screen.getHeight() / 2 - 100);
    }

    private void drawQuitConfirmation(Graphics2D graphics) {
        String message1 = "REALLY QUIT?";
        String message2 = "Press 'q' to quit, 'esc' to cancel";
        int xOffset1 = screen.getXOffset(graphics, bigFont, message1);
        int xOffset2 = screen.getXOffset(graphics, smallFont, message2);
        double fontHeight = bigFont.getStringBounds(message1, graphics.getFontRenderContext()).getHeight();
        double boxX = xOffset2 - 50;
        double boxY = screen.getHeight() / 2 - 100 - fontHeight;
        Rectangle2D box = new Rectangle2D.Double(boxX, boxY, screen.getWidth() - 2 * boxX, screen.getHeight() / 2 - boxY + 50);

        graphics.setColor(Color.BLACK);
        graphics.fill(box);
        graphics.setColor(Color.WHITE);
        graphics.draw(box);
        graphics.setFont(bigFont);
        graphics.drawString(message1, xOffset1, screen.getHeight() / 2 - 50);
        graphics.setFont(smallFont);
        graphics.drawString(message2, xOffset2, screen.getHeight() / 2);
    }

    private void drawLevelProgress(Graphics2D graphics) {
        Color color;
        String message;
        if (lives == 0) {
            color = Color.RED;
            message = "GAME OVER";
        }
        else if (level.isLastWave() && spawnTicks >= 30 && spawnTicks <= 270) {
            color = Color.YELLOW;
            message = String.format("LEVEL %d COMPLETE", levelNo);
        }
        else if (spawnTicks >= 30 && spawnTicks <= 90) {
            color = Color.WHITE;
            message = String.format("WAVE %d OF %d COMPLETE", waveNo, level.numWaves());
        }
        else
            return;
        int xOffset = screen.getXOffset(graphics, bigFont, message);
        graphics.setFont(bigFont);
        graphics.setColor(color);
        graphics.drawString(message, xOffset, screen.getHeight() / 2 - 10);
        if (lives == 0)
            drawAccuracyMessage(graphics);
        else if (upgrade != null)
            drawUpgradeMessage(graphics);
    }

    private void drawAccuracyMessage(Graphics2D graphics) {
        for (Entity entity : entities) {
            if (entity instanceof Bullet && ((Bullet) entity).wasFiredByPlayer()) {
                respawnTicks = 0;  // Don't end until player sees accuracy
                return;
            }
        }
        String message = String.format("YOUR ACCURACY: %.1f%%", getAccuracy());
        int xOffset = screen.getXOffset(graphics, mediumFont, message);
        graphics.setColor(Color.RED);
        graphics.setFont(mediumFont);
        graphics.drawString(message, xOffset, screen.getHeight() / 2 + 50);
    }

    private void drawUpgradeMessage(Graphics2D graphics) {
        String message1 = "YOU GOT: " + upgrade.getName().toUpperCase();
        String message2 = upgrade.getDescription().toUpperCase();
        int xOffset1 = screen.getXOffset(graphics, mediumFont, message1);
        int xOffset2 = screen.getXOffset(graphics, smallFont, message2);
        graphics.setColor(Color.WHITE);
        graphics.setFont(mediumFont);
        graphics.drawString(message1, xOffset1, screen.getHeight() / 2 + 50);
        graphics.setFont(smallFont);
        graphics.drawString(message2, xOffset2, screen.getHeight() / 2 + 80);
    }

    private void spawnPickup(Entity source) {
        Upgrade up = Generator.getRandomUpgrade();
        Rectangle2D.Double rect = source.getRect();
        Pickup bonus = new Pickup(up, rect.x + rect.width / 2,
                                      rect.y + rect.height / 2);
        bonus.setWorld(this);
        waitForPickup = true;
    }

    private void endGame() {
        HighScores scores = new HighScores();
        scores.load();
        screen.showCursor();
        if (score > 0 && scores.displaces(score)) {
            NewHighScoreScreen hs = new NewHighScoreScreen(screen, scores, levelNo, waveNo, score);
            screen.popHook();
            screen.pushHook(hs);
        }
        else {
            Menu menu = new Menu(screen);
            menu.setup();
            screen.popHook();
            screen.pushHook(menu);
        }
    }

    @Override
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
        else if (event.getKeyCode() == Preferences.getValue("pauseKey"))
            paused = !paused;
        else if (event.getKeyCode() == Preferences.getValue("debugKey"))
            Preferences.put("devMode", Math.abs(Preferences.getValue("devMode")-1));
    }

    @Override
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
        else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (quitRequested)
                quitRequested = false;
            else if (lives > 0)
                quitRequested = true;
            else
                endGame();
        }
        else if (event.getKeyCode() == KeyEvent.VK_Q) {
            if (quitRequested) {
                player.explode();
                killPlayer(player);
                entities.remove(player);
                lives = 0;
                quitRequested = false;
            }
            else if (lives > 0)
                quitRequested = true;
            else
                endGame();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public PlayerShip getPlayer() {
        return player;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public boolean isPlayerAlive() {
        return playerAlive;
    }
}
