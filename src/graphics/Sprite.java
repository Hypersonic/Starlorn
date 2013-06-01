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
        Sprite sprite = new Sprite(name, getRect(name));
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
            return new Rectangle(312, 608, 56, 52);
        else if (name.equals("enemy/right"))
            return new Rectangle(376, 610, 44, 58);

        else if (name.equals("bullet/blue/long"))
            return new Rectangle(13, 267, 8, 20);

        return new Rectangle(0, 0, 0, 0);
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
