package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;

    private Cell cell;
    
    private Texture background;
    private Texture cellImage;
    
    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;

        cell = new Cell(100, 100);
        background = new Texture("background.png");
        cellImage = new Texture("cell.png");
    }

    @Override public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;

        Vector2 cellPosition = cell.getPosition();
        
        batch.begin();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.draw(background, 0, 0);
        batch.draw(cellImage, cellPosition.x, cellPosition.y);
        batch.end();
    }
}
