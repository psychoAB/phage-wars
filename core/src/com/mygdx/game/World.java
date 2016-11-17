package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class World {

    public static final int MOUSE_MOVE = 0;
    public static final int MOUSE_PRESSED= 1;
    public static final int MOUSE_DRAG = 2;
    public static final int MOUSE_RELEASED = 3;
    
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
    public MouseInput mouseInput;

    private LinkedList<Cell> cells;
    private Cell base;
    private Cell target;
    private boolean aimming;
    
    public World() {
        worldTimer = new WorldTimer();
        mouseInput = new MouseInput();
        cells = new LinkedList<Cell>();

        for(int [] cellPosition : initCellPosition) {
            cells.add(new Cell(cellPosition[0], cellPosition[1], this));
        }

        aimming = false;
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }

    public void mouseInput(int x, int y, int mouseInputType) {
        if(mouseInputType == MOUSE_MOVE) {
            mouseInput.notifyMouseListeners(x, y);
        }
        else if(mouseInputType == MOUSE_PRESSED) {
            base = overlapWithCell(x, y);
            if(base != null) {
                aimming = true;
            }
        }
        else if(mouseInputType == MOUSE_DRAG) {

        }
        else if(mouseInputType == MOUSE_RELEASED) {
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
            if(cell.isOverlapWithCell(x, y)) {
                return cell;
            }
        }
        return null;
    }
}
