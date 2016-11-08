package com.mygdx.game;

import java.util.LinkedList;

public class World {
    
    public WorldTimer worldTimer;

    private LinkedList<Cell> cells;
    
    public World() {
        worldTimer = new WorldTimer();
        cells = new LinkedList<Cell>();

        cells.add(new Cell(0, 0, this));
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }
}
