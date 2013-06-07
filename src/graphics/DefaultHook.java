package edu.stuy.starlorn.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class DefaultHook implements Hook {

    @Override
    public void keyTyped(KeyEvent event) { }

    @Override
    public void keyPressed(KeyEvent event) { }

    @Override
    public void keyReleased(KeyEvent event) { }

    @Override
    public void mouseClicked(MouseEvent event) { }

    @Override
    public void mouseEntered(MouseEvent event) { }

    @Override
    public void mouseExited(MouseEvent event) { }

    @Override
    public void mousePressed(MouseEvent event) { }

    @Override
    public void mouseReleased(MouseEvent event) { }

    @Override
    public void mouseDragged(MouseEvent event) { }

    @Override
    public void mouseMoved(MouseEvent event) { }
}
