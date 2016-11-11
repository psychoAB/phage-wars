package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;
    
    private World world;
    private WorldRenderer worldRenderer;

    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;

        world = new World();
        worldRenderer = new WorldRenderer(phageWarsGame, world);

        inputInit();
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render(delta);
    }

    private void inputInit() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            
            private boolean dragging;

            @Override public boolean touchDown(int x, int y, int pointer, int button) {
                if(button != Input.Buttons.LEFT || pointer > 0) {
                    return false;
                }
                y = PhageWarsGame.HEIGHT - y;
                dragging = true;
                world.mouseInput(x, y, world.MOUSE_PRESSED);
                return true;
            }

            @Override public boolean touchDragged(int x, int y, int pointer) {
                return dragging;
            }

            @Override public boolean touchUp(int x, int y, int pointer, int button) {
                if(button != Input.Buttons.LEFT || pointer > 0 || !dragging) {
                    return false;
                }
                y = PhageWarsGame.HEIGHT - y;
                world.mouseInput(x, y, world.MOUSE_RELEASED);
                dragging = false;
                return true;
            }
        });
    }
}
