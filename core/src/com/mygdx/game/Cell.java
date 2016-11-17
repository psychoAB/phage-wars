package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public class Cell {
    public static final int IMAGE_SIZE = 120;
    public static final int FRAME_SIZE = 135;

    public static final int MAX_VIRUS = 50;
    public static final int MAX_REGENERATION_RATE= 3;

    private World world;

    private Player player;
    private Vector2 position;
    private int virusNumber;
    private int regenerationRate;
    private boolean mouseOn;

    public Cell(int x, int y,Player player, World world) {
        this.world = world;

        position = new Vector2(x - IMAGE_SIZE / 2, y - IMAGE_SIZE / 2);

        this.player = player;
        virusNumber = player.getInitVirusNumber();
        regenerationRate = player.getRegenerationRate();

        mouseOn = false;

        registerTimerListener();
        registerMouseListener();
    }

    public void attack(Cell cell) {
        if(cell != this) {
            int attacker = virusNumber / 2;
            for(int i = 0; i < attacker; i++) {
                world.getVirus().add(new Virus((int)position.x, (int)position.y, cell, player));
            }
            virusNumber -= virusNumber / 2;
        }
    }

    public void defend(Virus virus) {
        if(virus.getPlayer() == player) {
            virusNumber++;
            if(virusNumber > 100) {
                virusNumber = 100;
            }
        }
        else {
            virusNumber--;
            if(virusNumber <= 0) {
                player = virus.getPlayer();
                virusNumber = Math.abs(virusNumber);
                regenerationRate = MAX_REGENERATION_RATE;
            }
        }
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

    public Player getPlayer() {
        return player;
    }

    public boolean isOverlapWithCell(int x, int y) {
        return (x >= position.x && x <= position.x + IMAGE_SIZE) && (y >= position.y && y <= position.y + IMAGE_SIZE);
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
        world.worldLogic.mouseInput.registerMouseListener(new MouseInput.MouseListener() {
            @Override public void notifyMouseListener(int x, int y) {
                if(isOverlapWithCell(x, y) || world.worldLogic.getBases().contains(Cell.this)) {
                    mouseOn = true;
                }
                else { 
                    mouseOn = false;
                }
            }
        });
    }
}
