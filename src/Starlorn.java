package edu.stuy.starlorn;
import java.io.IOException;
import edu.stuy.starlorn.graphics.Screen;
import edu.stuy.starlorn.menu.Menu;
import edu.stuy.starlorn.util.Preferences;

public class Starlorn {

    public static void main(String[] args) {
        Screen screen = new Screen();
        Menu menu = new Menu(screen);

        Preferences.load();
        screen.setup();
        menu.setup();
        screen.addHook(menu);
        screen.run();
        System.exit(0);
    }
}
