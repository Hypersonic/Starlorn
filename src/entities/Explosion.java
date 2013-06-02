package edu.stuy.starlorn.entities;

public class Explosion extends Entity {
    private String[] _sprites;
    private int index;

    public Explosion() {
        super();
        _sprites = new String[8];
        for (int i = 0; i < 7; i++) {
            _sprites[i] = "explosion/" + (i+1);
        }
        index = 0;
    }

    @Override
    public void step() {
        if (index >= _sprites.length - 1) {
            this.kill();
        } else {
            updateSprite(_sprites[index]);
            index++;
        }
    }
}
