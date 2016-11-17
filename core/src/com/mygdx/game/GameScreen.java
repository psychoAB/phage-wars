package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;

public class GameScreen extends ScreenAdapter {
    private PhageWarsGame phageWarsGame;

    private GameInput gameInput;
    
    private World world;
    private WorldRenderer worldRenderer;

    private Opponent opponent;

    public GameScreen(PhageWarsGame phageWarsGame) {
        this.phageWarsGame = phageWarsGame;
        
        world = new World();
        gameInput = new GameInput(world);
        worldRenderer = new WorldRenderer(phageWarsGame, world);
        opponent = new Opponent(world.player[Player.OPPONENT], world);

        Gdx.input.setInputProcessor(gameInput);
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.worldLogic.update();
        opponent.work();
        worldRenderer.render(delta);

        if(world.worldLogic.isGameOver() != WorldLogic.NOT_OVER) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            worldRenderer.gameOver(world.worldLogic.isGameOver());
            if(Gdx.input.isKeyPressed(Keys.ENTER)) {
                world = new World();
                gameInput = new GameInput(world);
                worldRenderer = new WorldRenderer(phageWarsGame, world);
                opponent = new Opponent(world.player[Player.OPPONENT], world);

                Gdx.input.setInputProcessor(gameInput);
            }
        }
    }

    public static int screenCoordinateToTextureCoordinate(int screenY) {
        return PhageWarsGame.HEIGHT - screenY;
    }
}
