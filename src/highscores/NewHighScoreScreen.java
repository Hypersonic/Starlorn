package edu.stuy.starlorn.highscores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;

public class NewHighScoreScreen extends DefaultHook {

    private Screen screen;
    private HighScores scores;
    private long score;
    private int level, wave;
    private Date date;
    private Score scoreObj;
    private String name, message, message2;
    private Font bigFont, smallFont;
    private int timer;
    private boolean wait;

    public NewHighScoreScreen(Screen screen, HighScores scores, int level, int wave, long score) {
        this.screen = screen;
        this.scores = scores;
        this.score = score;
        this.level = level;
        this.wave = wave;
        date = new Date();
        scoreObj = null;
        name = message = message2 = null;
        bigFont = screen.getFont().deriveFont(24f);
        smallFont = screen.getFont().deriveFont(16f);
        timer = 30;
        wait = true;
    }

    public void finish() {
        HighScoresScreen scoreScreen = new HighScoresScreen(screen, scores, scoreObj);
        screen.popHook();
        screen.pushHook(scoreScreen);
    }

    @Override
    public void step(Graphics2D graphics) {
        if (wait) {
            wait = false;
            return;
        }
        if (name == null) {
            name = JOptionPane.showInputDialog(screen, "What is your name?",
                "Name", JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                finish();
                return;
            }
            scoreObj = scores.add(name, score, level, wave, date);
            while (scores.extraScores())
                getDisplaced();
            scores.save();
            message = String.format("You got a new HIGH SCORE of %s!", scoreObj.getFormattedScore());
        }

        int x = screen.getXOffset(graphics, bigFont, message);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(bigFont);
        if (message2 != null) {
            int x2 = screen.getXOffset(graphics, smallFont, message2);
            graphics.drawString(message, x, screen.getHeight() / 2 - 100);
            graphics.setColor(Color.WHITE);
            graphics.setFont(smallFont);
            graphics.drawString(message2, x2, screen.getHeight() / 2);
        }
        else {
            graphics.drawString(message, x, screen.getHeight() / 2);
        }
        if (timer > 0)
            timer--;
    }

    private void getDisplaced() {
        Score score = scores.popLowest();
        if (score.getName().equals(name))
            message2 = String.format("You beat your earlier score of %s!", score.getFormattedScore());
        else
            message2 = String.format("You knocked %s's score of %s off the board!", score.getName(), score.getFormattedScore());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (timer == 0)
            finish();
    }
}
