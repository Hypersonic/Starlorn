package edu.stuy.starlorn.entities;

public class Explosion extends Entity {

    private String[] _sprites;
    private int index, tick;
    private static final int TICKS_PER_FRAME = 2;

    public Explosion() {
        super();
        _sprites = new String[8];
        for (int i = 0; i < 8; i++) {
            _sprites[i] = "explosion/" + (i+1);
        }
        index = tick = 0;
        updateSprite(_sprites[index]);
    }

    @Override
    public void step() {
        tick++;
        if (tick == TICKS_PER_FRAME) {
            index++;
            tick = 0;
            if (index == _sprites.length)
                this.kill();
            else
                updateSprite(_sprites[index]);
        }
    }
}
