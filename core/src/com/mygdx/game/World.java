package com.mygdx.game;

import java.util.LinkedList;

public class World {
    
    private static final int [][] initCellPosition = new int [][] {
        {100, PhageWarsGame.HEIGHT / 2},
        {PhageWarsGame.WIDTH - 100, PhageWarsGame.HEIGHT / 2},
        {PhageWarsGame.WIDTH / 2, PhageWarsGame.HEIGHT / 2},
        {PhageWarsGame.WIDTH / 4 + 50, PhageWarsGame.HEIGHT / 4},
        {PhageWarsGame.WIDTH / 4 + 50, PhageWarsGame.HEIGHT * 3 / 4},
        {PhageWarsGame.WIDTH * 3 / 4 - 50, PhageWarsGame.HEIGHT / 4},
        {PhageWarsGame.WIDTH * 3 / 4 - 50, PhageWarsGame.HEIGHT * 3 / 4}
    };

    public WorldTimer worldTimer;

    private LinkedList<Cell> cells;
    
    public World() {
        worldTimer = new WorldTimer();
        cells = new LinkedList<Cell>();

        for(int [] cellPosition : initCellPosition) {
            cells.add(new Cell(cellPosition[0], cellPosition[1], this));
        }
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }
}
