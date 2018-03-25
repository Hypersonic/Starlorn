package edu.stuy.starlorn.graphics;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Sprite {

    private static final String SPRITES_FILE = "res/graphics.png";
    private static BufferedImage spritesheet;
    private static HashMap<String, Sprite> sprites;

    private String name;
    private int width, height;
    private BufferedImage image;

    private Sprite(String n, Rectangle rect) {
        name = n;
        width = rect.width;
        height = rect.height;
        image = spritesheet.getSubimage(rect.x, rect.y, width, height);
    }

    public static Sprite getSprite(String name) {
        if (spritesheet == null)
            load();
        if (sprites == null)
            sprites = new HashMap<String, Sprite>();
        if (sprites.containsKey(name))
            return sprites.get(name);
        Rectangle rect = getRect(name);
        if (rect == null)
            return null;
        Sprite sprite = new Sprite(name, rect);
        sprites.put(name, sprite);
        return sprite;
    }

    private static void load() {
        try {
            InputStream stream = new FileInputStream(SPRITES_FILE);
            spritesheet = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Rectangle getRect(String name) {
        if (name.equals("player/left/still"))
            return new Rectangle(10, 6, 60, 72);
        else if (name.equals("player/straight/still"))
            return new Rectangle(84, 6, 78, 72);
        else if (name.equals("player/right/still"))
            return new Rectangle(178, 6, 60, 72);
        else if (name.equals("player/left/fast"))
            return new Rectangle(10, 86, 60, 86);
        else if (name.equals("player/straight/fast"))
            return new Rectangle(84, 86, 78, 86);
        else if (name.equals("player/right/fast"))
            return new Rectangle(178, 86, 60, 86);
        else if (name.equals("player/left/slow"))
            return new Rectangle(10, 178, 60, 86);
        else if (name.equals("player/straight/slow"))
            return new Rectangle(84, 178, 78, 86);
        else if (name.equals("player/right/slow"))
            return new Rectangle(178, 178, 60, 86);

        else if (name.equals("enemy/left"))
            return new Rectangle(260, 610, 44, 58);
        else if (name.equals("enemy/straight"))
            return new Rectangle(312, 610, 56, 51);
        else if (name.equals("enemy/right"))
            return new Rectangle(376, 610, 44, 58);

        else if (name.equals("explosion/1"))
            return new Rectangle(18, 336, 14, 14);
        else if (name.equals("explosion/2"))
            return new Rectangle(50, 328, 30, 30);
        else if (name.equals("explosion/3"))
            return new Rectangle(96, 322, 42, 42);
        else if (name.equals("explosion/4"))
            return new Rectangle(154, 308, 78, 70);
        else if (name.equals("explosion/5"))
            return new Rectangle(6, 394, 90, 82);
        else if (name.equals("explosion/6"))
            return new Rectangle(108, 390, 106, 98);
        else if (name.equals("explosion/7"))
            return new Rectangle(250, 390, 106, 98);
        else if (name.equals("explosion/8"))
            return new Rectangle(386, 396, 106, 92);

        else if (name.equals("bullet/blue/long"))
            return new Rectangle(13, 267, 8, 20);
        else if (name.equals("bullet/purple/long"))
            return new Rectangle(458, 566, 6, 20);
        else if (name.equals("bullet/missile/blue/1"))
            return new Rectangle(38, 268, 8, 14);
        else if (name.equals("bullet/missile/blue/2"))
            return new Rectangle(54, 268, 8, 18);
        else if (name.equals("bullet/missile/blue/3"))
            return new Rectangle(70, 268, 8, 22);
        else if (name.equals("bullet/missile/blue/4"))
            return new Rectangle(86, 268, 8, 26);
        else if (name.equals("bullet/missile/blue/5"))
            return new Rectangle(100, 268, 12, 30);
        else if (name.equals("bullet/missile/purple/1"))
            return new Rectangle(148, 268, 8, 14);
        else if (name.equals("bullet/missile/purple/2"))
            return new Rectangle(164, 268, 8, 18);
        else if (name.equals("bullet/missile/purple/3"))
            return new Rectangle(180, 268, 8, 22);
        else if (name.equals("bullet/missile/purple/4"))
            return new Rectangle(196, 268, 8, 26);
        else if (name.equals("bullet/missile/purple/5"))
            return new Rectangle(210, 268, 12, 30);

        else if (name.equals("hud/lives"))
            return new Rectangle(280, 512, 39, 36);
        else if (name.equals("upgrade/generic"))
            return new Rectangle(444, 610, 16, 20);
        else if (name.equals("upgrade/doubleshot"))
            return new Rectangle(256, 680, 16, 20);
        else if (name.equals("upgrade/dualshot"))
            return new Rectangle(280, 680, 16, 20);
        else if (name.equals("upgrade/guidedmissile"))
            return new Rectangle(304, 680, 16, 20);
        else if (name.equals("upgrade/lawnsprinkler"))
            return new Rectangle(328, 680, 16, 20);
        else if (name.equals("upgrade/rapidfire"))
            return new Rectangle(352, 680, 16, 20);
        else if (name.equals("upgrade/scattershot"))
            return new Rectangle(376, 680, 16, 20);
        else if (name.equals("upgrade/sideshot"))
            return new Rectangle(400, 680, 16, 20);
        else if (name.equals("upgrade/speedshot"))
            return new Rectangle(424, 680, 16, 20);
        else if (name.equals("upgrade/stuttershot"))
            return new Rectangle(424, 680, 16, 20); // same as speedshot
        else if (name.equals("upgrade/tripleshot"))
            return new Rectangle(448, 680, 16, 20);
        return null;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TexturePaint getPaint(Rectangle2D offset) {
        return new TexturePaint(image, offset);
    }
}
