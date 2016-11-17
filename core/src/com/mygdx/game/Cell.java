package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Cell {
    public static final int IMAGE_SIZE = 120;
    public static final int FRAME_SIZE = 135;

    private World world;

    private Vector2 position;
    private int virusNumber;
    private int regenerationRate;
    private boolean mouseOn;

    public Cell(int x, int y, World world) {
        this.world = world;

        position = new Vector2(x - IMAGE_SIZE / 2, y - IMAGE_SIZE / 2);

        virusNumber = 0;
        regenerationRate = 3;
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
    
    public void attack(Cell cell) {
        if(cell != this) {
            cell.defend(virusNumber / 2);
            virusNumber -= virusNumber / 2;
        }
    }

    public void defend(int attacker) {
        virusNumber -= attacker;
    }

    private void registerTimerListener() {
        world.worldTimer.registerTimerListener(new WorldTimer.TimerListener() {
            @Override public void notifyTimerListener() {
                virusNumber += regenerationRate;
            }
        });
    }
    
    private void registerMouseListener() {
        world.mouseInput.registerMouseListener(new MouseInput.MouseListener() {
            @Override public void notifyMouseListener(int x, int y) {
                if(isOverlapWithCell(x, y)) {
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
