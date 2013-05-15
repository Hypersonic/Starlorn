package edu.stuy.starlorn;

// import edu.stuy.starlorn.world.World;
// import edu.stuy.starlorn.entities.Projectile;
import edu.stuy.starlorn.menu.Menu;

public class Starlorn {
    public static void main(String[] args) {
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
        Menu menu = new Menu();
        menu.start();
    }
}
