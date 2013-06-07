package edu.stuy.starlorn.highscores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.menu.Menu;

public class NewHighScoreScreen extends DefaultHook {

    private Screen screen;
    private HighScores scores;
    private long score;
    private Date date;
    private String name, message, message2;
    private Font bigFont, smallFont;
    private boolean wait;

    public NewHighScoreScreen(Screen screen, HighScores scores, long score) {
        this.screen = screen;
        this.scores = scores;
        this.score = score;
        date = new Date();
        name = message = message2 = null;
        bigFont = screen.getFont().deriveFont(24f);
        smallFont = screen.getFont().deriveFont(16f);
        wait = true;
    }

    public void finish() {
        Menu menu = new Menu(screen);           /// HIGH SCORES MENU
        menu.setup();
        screen.popHook();
        screen.pushHook(menu);
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
            scores.add(name, score, date);
            while (scores.extraScores())
                getDisplaced();
            scores.save();
            message = String.format("You got a new HIGH SCORE of %d!", score);
        }

        int x = getXOffset(graphics, bigFont, message);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(bigFont);
        if (message2 != null) {
            int x2 = getXOffset(graphics, smallFont, message2);
            graphics.drawString(message, x, screen.getHeight() / 2 - 100);
            graphics.setColor(Color.WHITE);
            graphics.setFont(smallFont);
            graphics.drawString(message2, x2, screen.getHeight() / 2);
        }
        else {
            graphics.drawString(message, x, screen.getHeight() / 2);
        }
    }

    private void getDisplaced() {
        Score score = scores.popLowest();
        if (score.getName().equals(name))
            message2 = String.format("This displaces your earlier score of %d!", score.getScore());
        else
            message2 = String.format("This displaces a score of %d by %s!", score.getScore(), score.getName());
    }

    private int getXOffset(Graphics2D graphics, Font font, String message) {
        double fontWidth = font.getStringBounds(message, graphics.getFontRenderContext()).getWidth();
        return (int) (screen.getWidth() - fontWidth) / 2;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_Q)
            finish();
    }
}
