package edu.stuy.starlorn.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.stuy.starlorn.graphics.DefaultHook;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.world.World;

public class Settings extends DefaultHook {

    private Screen screen;
    private Font bigFont, smallFont;
    private long lastFrame;
    private HoverBox[] hoverBoxes;
    private Button[] buttons;
    private Star[] stars;

    public Settings(Screen scr) {
        screen = scr;
        bigFont = screen.getFont().deriveFont(48f);
        smallFont = screen.getFont().deriveFont(11f);
    }

    public void setup() {
        int cx = screen.getWidth() / 2,
            cy = screen.getHeight() / 2;

        buttons = new Button[1];

        buttons[0] = new Button(screen, cx - 95, cy + 300, 190, 80, "Back", 18f,
                                new BackButtonCallback());

        hoverBoxes = new HoverBox[6];
        hoverBoxes[0] = new HoverBox(screen, cx - 300, cy - 200, 190, 80, "W", 18f,
                                "upKey");
        hoverBoxes[1] = new HoverBox(screen, cx - 300, cy - 50 , 190, 80, "W", 18f,
                                "downKey");
        hoverBoxes[2] = new HoverBox(screen, cx - 300, cy + 100, 190, 80, "W", 18f,
                                "leftKey");
        hoverBoxes[3] = new HoverBox(screen, cx + 210, cy - 200, 190, 80, "W", 18f,
                                "rightKey");
        hoverBoxes[4] = new HoverBox(screen, cx + 210, cy -  50, 190, 80, "W", 18f,
                                "shootKey");
        hoverBoxes[5] = new HoverBox(screen, cx + 210, cy + 100, 190, 80, "W", 18f,
                                "pauseKey");

        stars = new Star[400];
        for (int i = 0; i < 400; i++)
            stars[i] = new Star(screen.getWidth(), screen.getHeight());
    }

    private class BackButtonCallback implements Menu.Callback {
        public void invoke() {
            Menu menu = new Menu(screen);
            menu.setup();
            screen.popHook();
            screen.pushHook(menu);
        }
    }

    public void step(Graphics2D graphics) {
        drawTitle(graphics);
        graphics.setColor(Color.WHITE);
        for (Star star : stars) {
            star.update();
            star.draw(graphics);
        }
        for (Button button : buttons)
            button.draw(graphics);
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.draw(graphics);
    }

    private void drawTitle(Graphics2D graphics) {
        String text1 = "SETTINGS";
        String text2 = "";
        int xOffset1 = (int) (screen.getWidth() - bigFont.getStringBounds(text1, graphics.getFontRenderContext()).getWidth()) / 2;
        int xOffset2 = (int) (screen.getWidth() - smallFont.getStringBounds(text2, graphics.getFontRenderContext()).getWidth()) / 2;
        graphics.setColor(Color.GRAY);
        graphics.setFont(bigFont);
        graphics.drawString(text1, xOffset1, screen.getHeight() / 2 - 250);
        graphics.setColor(Color.WHITE);
        graphics.setFont(smallFont);
        graphics.drawString(text2, xOffset2, screen.getHeight() / 2 - 200);
    }

    @Override
    public void keyReleased(KeyEvent event) {
         for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
        if (event.getKeyCode() == KeyEvent.VK_Q)
            new BackButtonCallback().invoke();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (Button button : buttons)
            button.update(event);
        for (HoverBox hoverbox : hoverBoxes)
            hoverbox.update(event);
    }
}
