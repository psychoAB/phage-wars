package com.mygdx.game;

import java.util.LinkedList;
import java.util.Random;

public class Opponent {

    private static final int compensate = Cell.MAX_REGENERATION_RATE * 4;

    private World world;

    private Player player;

    private LinkedList<Cell> opponentCell;
    private LinkedList<Cell> remainingCell;

    public Opponent(Player player, World world) {
        this.world= world;
        this.player = player;

        opponentCell = new LinkedList<Cell>();
        remainingCell = new LinkedList<Cell>();
    }

    public void work() {
        LinkedList<Cell> cells = world.getCells();

        for(Cell cell : cells) {
            if(cell.getPlayer() == player) {
                opponentCell.add(cell);
            }
            else {
                remainingCell.add(cell);
            }
        }

        int manpower = 0;
        for(Cell cell : opponentCell) {
            manpower += cell.getVirusNumber();
        }

        Random rand = new Random();
        if(remainingCell.size() > 0) {
            int target = rand.nextInt(remainingCell.size());
            if(manpower > remainingCell.get(target).getVirusNumber() + compensate) {
                for(Cell cell : opponentCell) {
                    cell.attack(remainingCell.get(target));
                }
            }
        }
        opponentCell.clear();
        remainingCell.clear();
    }
}
