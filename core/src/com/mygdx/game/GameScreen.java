package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;
    
    private Texture background;
    
    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;

        background = new Texture("background.png");
    }

    @Override public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;

        batch.begin();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.draw(background, 0, 0);
        batch.end();
    }
}
