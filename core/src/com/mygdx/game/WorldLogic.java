package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
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
            if(tempCell != null && isAllies(tempCell, world.player[Player.ME])) {
                bases.add(tempCell);
                aimming = true;
            }
        }
        else if(mouseInputType == MOUSE_DRAG) {
            Cell tempCell = overlapWithCell(x, y);
            if(tempCell != null && isAllies(tempCell, world.player[Player.ME]) && !bases.contains(tempCell)) {
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

    public void update() {
        LinkedList<Virus> virus = world.getVirus();
        LinkedList<Virus> virusAtDestination = new LinkedList<Virus>();

        for(Virus vi : virus) {
            vi.update();
            Vector2 virusPosition = vi.getPosition();
            Cell tempCell = overlapWithCell((int)virusPosition.x, (int)virusPosition.y);
            if(tempCell != null && tempCell == vi.getTargetCell()) {
                tempCell.defend(vi);
                virusAtDestination.add(vi);
            }
        }

        for(Virus vi : virusAtDestination) {
            world.getVirus().remove(vi);
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

    public boolean isAllies(Cell cell, Player player) {
        return cell.getPlayer() == player;
    }
}
