package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class World {
    
    private PhageWarsGame phageWarsGame;

    public WorldTimer worldTimer;

    private LinkedList<Cell> cells;
    
    private Texture background;
    private Texture cellImage;

    private BitmapFont font;

    public World(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;

        worldTimer = new WorldTimer();
        cells = new LinkedList<Cell>();
        background = new Texture("background.png");
        cellImage = new Texture("cell.png");
        font = new BitmapFont();

        cells.add(new Cell(100, 100, this));
    }

    public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;

        batch.begin();

        batch.draw(background, 0, 0);

        for(Cell cell : cells) {
            Vector2 cellPosition = cell.getPosition();
            batch.draw(cellImage, cellPosition.x, cellPosition.y);
            font.draw(batch, "" + cell.getVirusNumber(), cellPosition.x + Cell.IMAGE_SIZE / 2, cellPosition.y + Cell.IMAGE_SIZE / 2);
        }

        batch.end();
    }
}
