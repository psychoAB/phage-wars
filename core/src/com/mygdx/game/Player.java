package com.mygdx.game;

public class Player {

    private int playerType;
    private int initVirusNumber;
    private int regenerationRate;

    public Player(int initVirusNumber, int regenerationRate, int playerType) {
        this.initVirusNumber = initVirusNumber;
        this.regenerationRate = regenerationRate;
        this.playerType = playerType;
    }

    public int getInitVirusNumber() {
        return initVirusNumber;
    }

    public int getRegenerationRate() {
        return regenerationRate;
    }

    public int getPlayerType() {
        return playerType;
    }
}
