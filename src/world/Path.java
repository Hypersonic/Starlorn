package edu.stuy.starlorn.world;

import java.util.ArrayList;

public class Path {
    protected ArrayList<Integer[]> _path;

    public Path() {
        _path = new ArrayList<Integer[]>();
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
