package com.mygdx.game;

public class Player {

    private int initVirusNumber;
    private int regenerationRate;

    public Player(int initVirusNumber, int regenerationRate) {
        this.initVirusNumber = initVirusNumber;
        this.regenerationRate = regenerationRate;
    }

    public int getInitVirusNumber() {
        return initVirusNumber;
    }

    public int getRegenerationRate() {
        return regenerationRate;
    }
}
