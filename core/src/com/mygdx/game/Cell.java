package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Cell {

    private World world;

    private Vector2 position;
    private int virusNumber;
    private int regenerationRate;

    public Cell(int x, int y, World world) {
        this.world = world;

        position = new Vector2(x, y);

        virusNumber = 0;
        regenerationRate = 3;

        registerTimerListener();
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getVirusNumber() {
        return virusNumber;
    }

    private void registerTimerListener() {
        world.registerTimerListener(new World.TimerListener() {
            @Override public void notifyTimerListener() {
                virusNumber += regenerationRate;
            }
        });
    }
}
