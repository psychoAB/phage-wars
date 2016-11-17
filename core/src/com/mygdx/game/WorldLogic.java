package com.mygdx.game;

import java.util.LinkedList;

public class WorldLogic {
    
    public static final int MOUSE_MOVE = 0;
    public static final int MOUSE_PRESSED= 1;
    public static final int MOUSE_DRAG = 2;
    public static final int MOUSE_RELEASED = 3;

    private World world;
    public MouseInput mouseInput;

    private LinkedList<Cell> bases;
    private Cell target;
    private boolean aimming;
    
    public WorldLogic(World world) {
        this.world = world;

        mouseInput = new MouseInput();
        bases = new LinkedList<Cell>();

        aimming = false;
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
            if(tempCell != null && tempCell.getPlayer() == bases.get(0).getPlayer() && !bases.contains(tempCell)) {
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
        LinkedList<Cell> cells = world.getCells();
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
