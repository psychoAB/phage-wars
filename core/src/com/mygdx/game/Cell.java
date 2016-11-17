package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public class Cell {
    public static final int IMAGE_SIZE = 120;
    public static final int FRAME_SIZE = 135;

    public static final int NATURAL = 0;
    public static final int PLAYER = 1;
    public static final int OPPONENT = 2;

    private static final int MAX_VIRUS = 50;
    private static final int MAX_REGENERATION_RATE= 3;

    private World world;

    private int cellType;
    private Vector2 position;
    private int virusNumber;
    private int regenerationRate;
    private boolean mouseOn;

    public Cell(int x, int y,int cellType, World world) {
        this.world = world;

        position = new Vector2(x - IMAGE_SIZE / 2, y - IMAGE_SIZE / 2);

        this.cellType = cellType;
        if(cellType != NATURAL) {
            virusNumber = MAX_VIRUS;
            regenerationRate = MAX_REGENERATION_RATE;
        }
        else {
            virusNumber = 0;
            regenerationRate = 0;
        }
        mouseOn = false;

        registerTimerListener();
        registerMouseListener();
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getVirusNumber() {
        return virusNumber;
    }
    
    public boolean isMouseOn() {
        return mouseOn;
    }

    public int getCellType() {
        return cellType;
    }
    
    public void attack(Cell cell) {
        if(cell != this) {
            cell.defend(virusNumber / 2, cellType);
            virusNumber -= virusNumber / 2;
        }
    }

    public void defend(int attacker, int attackerType) {
        if(attackerType == cellType) {
            virusNumber += attacker;
            if(virusNumber > 100) {
                virusNumber = 100;
            }
        }
        else {
            virusNumber -= attacker;
            if(virusNumber <= 0) {
                cellType = attackerType;
                virusNumber = Math.abs(virusNumber);
                regenerationRate = MAX_REGENERATION_RATE;
            }
        }
    }

    private void registerTimerListener() {
        world.worldTimer.registerTimerListener(new WorldTimer.TimerListener() {
            @Override public void notifyTimerListener() {
                virusNumber += regenerationRate;
                if(virusNumber > 100) {
                    virusNumber = 100;
                }
            }
        });
    }
    
    private void registerMouseListener() {
        world.mouseInput.registerMouseListener(new MouseInput.MouseListener() {
            @Override public void notifyMouseListener(int x, int y) {
                if(isOverlapWithCell(x, y) || world.getBases().contains(Cell.this)) {
                    mouseOn = true;
                }
                else { 
                    mouseOn = false;
                }
            }
        });
    }

    public boolean isOverlapWithCell(int x, int y) {
        return (x >= position.x && x <= position.x + IMAGE_SIZE) && (y >= position.y && y <= position.y + IMAGE_SIZE);
    }
}
