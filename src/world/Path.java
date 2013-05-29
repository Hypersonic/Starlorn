package edu.stuy.starlorn.world;

import java.util.ArrayList;

import edu.stuy.starlorn.util.Preferences;

public class Path {
    protected ArrayList<Integer[]> _path;

    public Path() {
        _path = new ArrayList<Integer[]>();
    }

    public void generatePath(int numPoints) {
        //TODO: Get these from Preferences
        int screenWidth = 100;
        int screenHeight = 100;
        for (int i = 0; i < numPoints; i++) {
            int[] coords = new int[2];
            coords[0] = (int) (Math.random() * screenWidth);
            coords[1] = (int) (Math.random() * screenHeight);
            _path.add(coords);
        }
    }

    public void generatePath() {
        generatePath(10);
    }
}
