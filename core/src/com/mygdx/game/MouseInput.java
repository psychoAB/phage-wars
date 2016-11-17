package com.mygdx.game;

import java.util.LinkedList;

public class MouseInput {

    private LinkedList<MouseListener> mouseListeners;

    public MouseInput() {
        mouseListeners = new LinkedList<MouseListener>();
    }

    public interface MouseListener {
        void notifyMouseListener(int x, int y);
    }

    public void registerMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void notifyMouseListeners(int x, int y) {
        for(MouseListener listener : mouseListeners) {
            listener.notifyMouseListener(x, y);
        }
    }
}

