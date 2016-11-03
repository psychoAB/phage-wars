package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;
    
    private World world;

    private Cell cell;
    
    private Texture background;
    private Texture cellImage;

    private BitmapFont font;
    
    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;

        world = new World();
        cell = new Cell(100, 100, world);
        background = new Texture("background.png");
        cellImage = new Texture("cell.png");
        font = new BitmapFont();
    }

    @Override public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;

        Vector2 cellPosition = cell.getPosition();
        
        batch.begin();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.draw(background, 0, 0);
        batch.draw(cellImage, cellPosition.x, cellPosition.y);
        font.draw(batch, "" + cell.getVirusNumber(), cellPosition.x + cellImage.getWidth() / 2, cellPosition.y + cellImage.getHeight() / 2);
        batch.end();
    }
}
