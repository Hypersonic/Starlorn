package edu.stuy.starlorn.world;

import java.util.ArrayList;

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
            Integer[] coords = new Integer[2];
            coords[0] = (int) (Math.random() * screenWidth);
            coords[1] = (int) (Math.random() * screenHeight);
            _path.add(coords);
        }
    }

    public void generatePath() {
        generatePath(10);
    }

    public Integer[] getCoords(int n) {
        return _path.get(n);
    }
    
    public int getPathLength() {
        return _path.size();
    }
}
