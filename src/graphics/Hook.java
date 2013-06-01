package edu.stuy.starlorn.graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Hook {
    void step(Graphics2D graphics);
    void keyTyped(KeyEvent event);
    void keyPressed(KeyEvent event);
    void keyReleased(KeyEvent event);
    void mouseClicked(MouseEvent event);
    void mouseEntered(MouseEvent event);
    void mouseExited(MouseEvent event);
    void mousePressed(MouseEvent event);
    void mouseReleased(MouseEvent event);
    void mouseDragged(MouseEvent event);
    void mouseMoved(MouseEvent event);
}
