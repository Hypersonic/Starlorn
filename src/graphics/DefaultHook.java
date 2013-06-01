package edu.stuy.starlorn.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class DefaultHook implements Hook {

    public void keyTyped(KeyEvent event) { }

    public void keyPressed(KeyEvent event) { }

    public void keyReleased(KeyEvent event) { }

    public void mouseClicked(MouseEvent event) { }

    public void mouseEntered(MouseEvent event) { }

    public void mouseExited(MouseEvent event) { }

    public void mousePressed(MouseEvent event) { }

    public void mouseReleased(MouseEvent event) { }

    public void mouseDragged(MouseEvent event) { }

    public void mouseMoved(MouseEvent event) { }
}
