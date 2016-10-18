package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PhageWarsGame extends Game {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 800;

    SpriteBatch batch;

    @Override public void create () {
        batch = new SpriteBatch();
    }

    @Override public void render () {
        super.render();
    }

    @Override public void dispose () {
        batch.dispose();
    }
}
