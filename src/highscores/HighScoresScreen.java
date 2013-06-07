package edu.stuy.starlorn.highscores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.menu.Menu;
import edu.stuy.starlorn.menu.Star;

public class HighScoresScreen extends DefaultHook {

    private Screen screen;
    private HighScores scores;
    private Font bigFont, smallFont;
    private ArrayList<String> data;
    private String title;
    private int titleOffset, dataOffset;
    private Star[] stars;

    public HighScoresScreen(Screen screen, HighScores scores) {
        this.screen = screen;
        this.scores = scores;
        bigFont = screen.getFont().deriveFont(48f);
        smallFont = screen.getFont().deriveFont(16f);
        data = null;
        title = "HIGH SCORES";
        titleOffset = dataOffset = 0;
        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    public HighScoresScreen(Screen screen) {
        this(screen, new HighScores());
        scores.load();
    }

    @Override
    public void step(Graphics2D graphics) {
        if (data == null) {
            titleOffset = (int) (screen.getWidth() - bigFont.getStringBounds(
                title, graphics.getFontRenderContext()).getWidth()) / 2;
            getData(graphics);
        }
        int i = 0;

        graphics.setColor(Color.WHITE);
        for (Star star : stars) {
            star.update();
            star.draw(graphics);
        }
        graphics.setColor(Color.GRAY);
        graphics.setFont(bigFont);
        graphics.drawString(title, titleOffset, screen.getHeight() / 2 - 250);
        graphics.setColor(Color.WHITE);
        graphics.setFont(smallFont);
        for (String datum : data) {
            graphics.drawString(datum, dataOffset, screen.getHeight() / 2 - 190 + 20 * i);
            i++;
        }
    }

    private void getData(Graphics2D graphics) {
        int i = 1;
        String longest = "";
        data = new ArrayList<String>();
        for (Score score : scores) {
            String formatted = String.format("#%2d   %12s   %s", i,
                score.getFormattedScore(), score.getName());
            if (formatted.length() > longest.length())
                longest = formatted;
            data.add(formatted);
            i++;
        }
        dataOffset = (int) (screen.getWidth() - smallFont.getStringBounds(
            longest, graphics.getFontRenderContext()).getWidth()) / 2;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_Q) {
            Menu menu = new Menu(screen);
            menu.setup();
            screen.popHook();
            screen.pushHook(menu);
        }
    }
}
