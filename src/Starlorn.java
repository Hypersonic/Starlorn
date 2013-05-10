package edu.stuy.starlorn;

import edu.stuy.starlorn.world.World;
import edu.stuy.starlorn.entities.Entity;

public class Starlorn {
    public static void main(String[] args) {
        World w = new World();
        w.addEntity(new Entity());
        for (int i = 0; i < 10000; i++) {
            w.stepAll();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
