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
        _path.add(new Integer[]{x, y});
    }
    
    public int getPathLength() {
        return _path.size();
    }
}
