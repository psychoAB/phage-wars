package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;
    
    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;
    }

    @Override public void render(float delta) {
        System.out.println(delta);
    }
}
