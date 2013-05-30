package edu.stuy.starlorn.world;

import java.util.ArrayList;

public class Path {
    protected ArrayList<Integer[]> _path;

    public Path() {
        _path = new ArrayList<Integer[]>();
    }

    public static Path generatePath(int numPoints) {
        Path p = new Path();
        int screenWidth = 100;
        int screenHeight = 100;
        for (int i = 0; i < numPoints; i++) {
            int x = (int) (Math.random() * screenWidth);
            int y = (int) (Math.random() * screenHeight);
            p.addCoords(x, y);
        }
        return p;
    }

    public static Path generatePath() {
        return Path.generatePath(10);
    }

    public Integer[] getCoords(int n) {
        return _path.get(n);
    }

    public void addCoords(int x, int y) {
        Integer[] coords = new Integer[2];
        coords[0] = x;
        coords[1] = y;
        _path.add(coords);
    }
    
    public int getPathLength() {
        return _path.size();
    }
}
