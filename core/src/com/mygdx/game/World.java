package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class World {

    public static final int MOUSE_MOVE = 0;
    public static final int MOUSE_PRESSED= 1;
    public static final int MOUSE_DRAG = 2;
    public static final int MOUSE_RELEASED = 3;
    
    private static final int [][] initCell= new int [][] {
        {100, PhageWarsGame.HEIGHT / 2, Cell.PLAYER},
        {PhageWarsGame.WIDTH - 100, PhageWarsGame.HEIGHT / 2, Cell.OPPONENT},
        {PhageWarsGame.WIDTH / 2, PhageWarsGame.HEIGHT / 2, Cell.NATURAL},
        {PhageWarsGame.WIDTH / 4 + 50, PhageWarsGame.HEIGHT / 4, Cell.NATURAL},
        {PhageWarsGame.WIDTH / 4 + 50, PhageWarsGame.HEIGHT * 3 / 4, Cell.NATURAL},
        {PhageWarsGame.WIDTH * 3 / 4 - 50, PhageWarsGame.HEIGHT / 4, Cell.NATURAL},
        {PhageWarsGame.WIDTH * 3 / 4 - 50, PhageWarsGame.HEIGHT * 3 / 4, Cell.NATURAL}
    };

    public WorldTimer worldTimer;
    public MouseInput mouseInput;

    private LinkedList<Cell> cells;
    private LinkedList<Cell> bases;
    private Cell target;
    private boolean aimming;
    
    public World() {
        worldTimer = new WorldTimer();
        mouseInput = new MouseInput();
        cells = new LinkedList<Cell>();
        bases = new LinkedList<Cell>();

        for(int [] cell: initCell) {
            cells.add(new Cell(cell[0], cell[1], cell[2], this));
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
            Cell tempCell = overlapWithCell(x, y);
            if(tempCell != null) {
                bases.add(tempCell);
                aimming = true;
            }
        }
        else if(mouseInputType == MOUSE_DRAG) {
            Cell tempCell = overlapWithCell(x, y);
            if(tempCell != null && tempCell.getCellType() == bases.get(0).getCellType() && !bases.contains(tempCell)) {
                bases.add(tempCell);
            }
            mouseInput.notifyMouseListeners(x, y);
        }
        else if(mouseInputType == MOUSE_RELEASED) {
            target = overlapWithCell(x, y);
            if(target != null && bases.size() != 0) {
                aimming = false;
                for(Cell cell : bases) {
                    cell.attack(target);
                }
            }
            bases.clear();
            target = null;
            mouseInput.notifyMouseListeners(x, y);
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

    public LinkedList<Cell> getBases() {
        return bases;
    }
}
