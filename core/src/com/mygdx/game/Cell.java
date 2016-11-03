package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Cell {
    
    private Vector2 position;

    public Cell(int x, int y) {
        position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }
}
