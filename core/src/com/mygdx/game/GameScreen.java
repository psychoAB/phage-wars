package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;
    
    private World world;

    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;

        world = new World(phageWarsGame);
    }

    @Override public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;
        
        batch.begin();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.end();

        world.render(delta);
    }
}
