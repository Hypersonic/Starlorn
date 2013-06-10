package edu.stuy.starlorn.highscores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.menu.Button;
import edu.stuy.starlorn.menu.Menu;
import edu.stuy.starlorn.menu.Star;

public class HighScoresScreen extends DefaultHook {

    private Screen screen;
    private HighScores scores;
    private Score highlight;
    private Font bigFont, smallFont;
    private ArrayList<String> data;
    private String title, header;
    private int titleOffset, dataOffset, highlightIndex;
    private Button button;
    private Star[] stars;

    public HighScoresScreen(Screen screen, HighScores scores, Score score) {
        this.screen = screen;
        this.scores = scores;
        highlight = score;
        bigFont = screen.getFont().deriveFont(48f);
        smallFont = screen.getFont().deriveFont(16f);
        data = null;
        title = "HIGH SCORES";
        titleOffset = dataOffset = 0;
        highlightIndex = -1;
        button = new Button(screen, screen.getWidth() / 2 - 95, screen.getHeight() / 2 + 270,
                            190, 80, "Back", 18f, new BackButtonCallback());
        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    public HighScoresScreen(Screen screen, HighScores scores) {
        this(screen, scores, null);
    }

    public HighScoresScreen(Screen screen) {
        this(screen, new HighScores());
        scores.load();
    }

    private class BackButtonCallback implements Menu.Callback {
        public void invoke() {
            Menu menu = new Menu(screen);
            menu.setup();
            screen.popHook();
            screen.pushHook(menu);
        }
    }

    @Override
    public void step(Graphics2D graphics) {
        if (data == null) {
            titleOffset = screen.getXOffset(graphics, bigFont, title);
            getData(graphics);
        }

        graphics.setColor(Color.WHITE);
        for (Star star : stars) {
            star.update();
            star.draw(graphics);
        }
        graphics.setColor(Color.GRAY);
        graphics.setFont(bigFont);
        graphics.drawString(title, titleOffset, screen.getHeight() / 2 - 250);
        graphics.setFont(smallFont);
        graphics.drawString(header, dataOffset, screen.getHeight() / 2 - 190);
        graphics.setColor(Color.WHITE);
        int i = 1;
        for (String datum : data) {
            if (i == highlightIndex)
                graphics.setColor(Color.YELLOW);
            graphics.drawString(datum, dataOffset, screen.getHeight() / 2 - 190 + 20 * i);
            if (i == highlightIndex)
                graphics.setColor(Color.WHITE);
            i++;
        }
        button.draw(graphics);
    }

    private void getData(Graphics2D graphics) {
        int i = 1, slen;
        data = new ArrayList<String>();
        if (scores.count() == 0)
            slen = 5;
        else
            slen = Math.max(5, scores.getHighest().getFormattedScore().length());
        header = String.format("Place%" + (slen - 4) + "sScore   Level   Name", " ");
        String longest = header;
        String tmpl = "#%2d   %" + slen + "s   %s   %s";
        for (Score score : scores) {
            String level = String.format("%2d.%-2d", score.getLevel(), score.getWave());
            String formatted = String.format(tmpl, i, score.getFormattedScore(), level, score.getName());
            if (formatted.length() > longest.length())
                longest = formatted;
            data.add(formatted);
            if (score == highlight)
                highlightIndex = i;
            i++;
        }
        dataOffset = screen.getXOffset(graphics, smallFont, longest);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_Q || event.getKeyCode() == KeyEvent.VK_B)
            new BackButtonCallback().invoke();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        button.update(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        button.update(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        button.update(event);
    }
}
