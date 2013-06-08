package edu.stuy.starlorn.graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import javax.swing.event.MouseInputListener;

public interface Hook extends KeyListener, MouseInputListener {
    void step(Graphics2D graphics);
}
