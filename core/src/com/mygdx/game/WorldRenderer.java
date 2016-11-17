package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class WorldRenderer {

    private PhageWarsGame phageWarsGame;

    private World world;
    
    private Texture background;
    private Texture cellImage;
    private Texture cellFrame;

    private BitmapFont font;

    public WorldRenderer(PhageWarsGame phageWarsGame, World world) {
        this.phageWarsGame = phageWarsGame;
        this.world = world;

        background = new Texture("background.png");
        cellImage = new Texture("cell.png");
        cellFrame = new Texture("frame.png");
        font = new BitmapFont();
    }

    public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;
        
        LinkedList<Cell> cells = world.getCells();

        batch.begin();

        batch.draw(background, 0, 0);

        for(Cell cell : cells) {
            Vector2 cellPosition = cell.getPosition();
            batch.draw(cellImage, cellPosition.x, cellPosition.y);
            if(cell.getVirusNumber() != 0) {
                font.draw(batch, "" + cell.getVirusNumber(), cellPosition.x + Cell.IMAGE_SIZE / 2, cellPosition.y + Cell.IMAGE_SIZE / 2);
            }
            if(cell.isMouseOn()) {
                batch.draw(cellFrame, cellPosition.x - (Cell.FRAME_SIZE - Cell.IMAGE_SIZE) / 2 , cellPosition.y- (Cell.FRAME_SIZE - Cell.IMAGE_SIZE) / 2);
            }
        }

        batch.end();
    }
}
