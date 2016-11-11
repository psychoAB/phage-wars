package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class World {

    public  static final int MOUSE_MOVE = 0;
    public  static final int MOUSE_PRESSED= 1;
    public  static final int MOUSE_DRAG = 2;
    public  static final int MOUSE_RELEASED = 3;
    
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
    private Cell base;
    private Cell target;
    private boolean aimming;
    
    public World() {
        worldTimer = new WorldTimer();
        cells = new LinkedList<Cell>();

        for(int [] cellPosition : initCellPosition) {
            cells.add(new Cell(cellPosition[0], cellPosition[1], this));
        }

        aimming = false;
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }

    public void mouseInput(int x, int y, int type) {
        if(type == MOUSE_MOVE) {

        }
        else if(type == MOUSE_PRESSED) {
            base = overlapWithCell(x, y);
            if(base != null) {
                aimming = true;
            }
        }
        else if(type == MOUSE_DRAG) {
            
        }
        else if(type == MOUSE_RELEASED) {
            target = overlapWithCell(x, y);
            if(target != null && base != null) {
                aimming = false;
                base.attack(target);
            }
            base = null;
            target = null;
        }
    }

    private Cell overlapWithCell(int x, int y) {
        for(Cell cell : cells) {
            Vector2 cellPosition = cell.getPosition();
            if(x >= cellPosition.x && x <= cellPosition.x + Cell.IMAGE_SIZE) {
                if(y >= cellPosition.y && y <= cellPosition.y + Cell.IMAGE_SIZE) {
                    return cell;
                }
            }
        }
        return null;
    }
}
