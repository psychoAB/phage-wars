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

    @Override public boolean mouseMoved(int x, int y) {
        y = GameScreen.screenCoordinateToTextureCoordinate(y);
        world.mouseInput(x, y, world.MOUSE_MOVE);
        return true;
    }

    @Override public boolean touchDown(int x, int y, int pointer, int button) {
        y = GameScreen.screenCoordinateToTextureCoordinate(y);
        if(button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        dragging = true;
        world.mouseInput(x, y, world.MOUSE_PRESSED);
        return true;
    }

    @Override public boolean touchDragged(int x, int y, int pointer) {
        y = GameScreen.screenCoordinateToTextureCoordinate(y);
        if(dragging) {
            world.mouseInput(x, y, world.MOUSE_DRAG);
        }
        return dragging;
    }

    @Override public boolean touchUp(int x, int y, int pointer, int button) {
        y = GameScreen.screenCoordinateToTextureCoordinate(y);
        if(button != Input.Buttons.LEFT || pointer > 0 || !dragging) {
            return false;
        }
        dragging = false;
        world.mouseInput(x, y, world.MOUSE_RELEASED);
        return true;
    }
}
