package edu.stuy.starlorn.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import edu.stuy.starlorn.entities.EnemyShip;
import edu.stuy.starlorn.graphics.Screen;

public class ScorePopup extends Entity {

    private String score;
    private Font font;
    private int life;

    public ScorePopup(Screen screen, EnemyShip enemy) {
        super(enemy.getRect().x + enemy.getRect().getWidth() / 2,
              enemy.getRect().y + enemy.getRect().getHeight() / 2);
        score = enemy.getScoreValue() + "";
        font = screen.getFont().deriveFont(12f);
        life = 60;
    }

    public void draw(Graphics2D graphics) {
        int x = (int) (rect.x - font.getStringBounds(score, graphics.getFontRenderContext()).getWidth() / 2);
        int y = (int) (rect.y - font.getLineMetrics(score, graphics.getFontRenderContext()).getAscent() / 2);
        float opacity = Math.min(life / 30f, 1f);
        graphics.setFont(font);
        graphics.setColor(new Color(opacity, opacity, opacity));
        graphics.drawString(score, x, y);
    }

    public void step() {
        life--;
        if (life <= 0)
            kill();
    }
}
