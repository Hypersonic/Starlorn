package edu.stuy.starlorn;
import java.io.IOException;
import edu.stuy.starlorn.graphics.Screen;
// import edu.stuy.starlorn.entities.Projectile;
import edu.stuy.starlorn.menu.Menu;
// import edu.stuy.starlorn.world.World;
import edu.stuy.starlorn.util.Preferences;

public class Starlorn {

	private Preferences _preferences;

    public static void main(String[] args) {
        Screen screen = new Screen();
        Menu menu = new Menu(screen);

        screen.setup();
        menu.setup();
        screen.addHook(menu);
        screen.run();
        System.exit(0);

        // World w = new World();
        // Projectile p = new Projectile();
        // p.setAngle(Math.PI/2);
        // w.addEntity(p);
        // for (int i = 0; i < 10; i++) {
        //     w.stepAll();
        //     try {
        //         Thread.sleep(100);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }


    }

    public void init() {
    	_preferences = new Preferences();
    	_preferences.loadPreferences();
        _preferences.writePreferences();
    }



}
