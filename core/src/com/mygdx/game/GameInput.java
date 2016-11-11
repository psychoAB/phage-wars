package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input;

public class GameInput extends InputAdapter {

    private World world;

    private boolean dragging;

    public GameInput(World world) {
        this.world = world;

        dragging = false;
    }

    @Override public boolean touchDown(int x, int y, int pointer, int button) {
        if(button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        dragging = true;
        y = PhageWarsGame.HEIGHT - y;
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
        dragging = false;
        y = PhageWarsGame.HEIGHT - y;
        world.mouseInput(x, y, world.MOUSE_RELEASED);
        return true;
    }
}
