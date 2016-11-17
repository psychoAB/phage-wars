package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class World {
    
    public static final int PLAYER_NUMBER = 3;

    public Player [] player = new Player [PLAYER_NUMBER];

    private static final int [][] initCell= new int [][] {
        {100, PhageWarsGame.HEIGHT / 2, Player.ME},
        {PhageWarsGame.WIDTH - 100, PhageWarsGame.HEIGHT / 2, Player.OPPONENT},
        {PhageWarsGame.WIDTH / 2, PhageWarsGame.HEIGHT / 2, Player.NATURAL},
        {PhageWarsGame.WIDTH / 4 + 50, PhageWarsGame.HEIGHT / 4, Player.NATURAL},
        {PhageWarsGame.WIDTH / 4 + 50, PhageWarsGame.HEIGHT * 3 / 4, Player.NATURAL},
        {PhageWarsGame.WIDTH * 3 / 4 - 50, PhageWarsGame.HEIGHT / 4, Player.NATURAL},
        {PhageWarsGame.WIDTH * 3 / 4 - 50, PhageWarsGame.HEIGHT * 3 / 4, Player.NATURAL}
    };

    public WorldTimer worldTimer;
    public WorldLogic worldLogic;

    private LinkedList<Cell> cells;
    public LinkedList<Virus> virus;
    
    public World() {
        player[Player.NATURAL] = new Player(0, 0, Player.NATURAL);
        player[Player.ME] = new Player(Cell.MAX_VIRUS, Cell.MAX_REGENERATION_RATE, Player.ME);
        player[Player.OPPONENT] = new Player(Cell.MAX_VIRUS, Cell.MAX_REGENERATION_RATE, Player.OPPONENT);
        worldTimer = new WorldTimer();
        worldLogic = new WorldLogic(this);
        cells = new LinkedList<Cell>();
        virus = new LinkedList<Virus>();

        for(int [] cell: initCell) {
            cells.add(new Cell(cell[0], cell[1], player[cell[2]], this));
        }
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }

    public LinkedList<Virus> getVirus() {
        return virus;
    }
}
