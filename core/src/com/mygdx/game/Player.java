package com.mygdx.game;

public class Player {

    public static final int NATURAL = 0;
    public static final int ME = 1;
    public static final int OPPONENT = 2;

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
