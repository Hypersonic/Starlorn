package edu.stuy.starlorn;
import java.io.IOException;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.menu.Menu;
import edu.stuy.starlorn.util.Preferences;

public class Starlorn {

    public static void main(String[] args) {
        Screen screen = new Screen();
        Menu menu = new Menu(screen);

        screen.setup();
        Preferences.load(screen.getWidth(), screen.getHeight());
        menu.setup();
        screen.addHook(menu);
        screen.run();
        Preferences.save();
        System.exit(0);
    }
}
