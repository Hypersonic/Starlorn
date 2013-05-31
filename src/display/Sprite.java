package edu.stuy.starlorn.display;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Sprite {

    private static BufferedImage spritesheet;
    private static HashMap<String, Sprite> sprites;
    private static final String SPRITES_FILE = "res/gfx/graphics.png";

    private TexturePaint paint;

    private Sprite(Rectangle rect) {
        paint = new TexturePaint(spritesheet, rect);
    }

    public static Sprite getSprite(String name) {
        if (spritesheet == null)
            load();
        if (sprites == null)
            sprites = new HashMap<String, Sprite>();
        if (sprites.containsKey(name))
            return sprites.get(name);
        Sprite sprite = new Sprite(getRect(name));
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
        return new Rectangle(0, 0, 100, 100);
    }

    public TexturePaint getPaint() {
        return paint;
    }
}
