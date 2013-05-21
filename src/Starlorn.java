package edu.stuy.starlorn;

import edu.stuy.starlorn.display.Screen;
// import edu.stuy.starlorn.entities.Projectile;
import edu.stuy.starlorn.menu.Menu;
// import edu.stuy.starlorn.world.World;

public class Starlorn {
    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.setup();

        Menu menu = new Menu(screen);
        menu.setup();
        menu.loop();

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

        screen.shutdown();
    }
}
