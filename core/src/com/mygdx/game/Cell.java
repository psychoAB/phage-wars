package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Cell {
    
    private Vector2 position;
    private int virusNumber;
    private int regenerationRate;

    public Cell(int x, int y) {
        position = new Vector2(x, y);

        virusNumber = 0;
        regenerationRate = 0;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getVirusNumber() {
        return virusNumber;
    }
}
