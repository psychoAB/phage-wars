package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class WorldRenderer {

    private PhageWarsGame phageWarsGame;

    private World world;
    
    private Texture [] cellImage = new Texture[World.PLAYER_NUMBER];
    private Texture [] virusImage = new Texture[World.PLAYER_NUMBER];
    private Texture background;
    private Texture cellFrame;

    private BitmapFont font;

    public WorldRenderer(PhageWarsGame phageWarsGame, World world) {
        this.phageWarsGame = phageWarsGame;
        this.world = world;

        background = new Texture("background.png");
        cellImage[Player.NATURAL] = new Texture("natural.png");
        cellImage[Player.ME] = new Texture("me.png");
        cellImage[Player.OPPONENT] = new Texture("opponent.png");
        cellFrame = new Texture("frame.png");
        virusImage[Player.NATURAL] = new Texture("virusNatural.png");
        virusImage[Player.ME] = new Texture("virusMe.png");
        virusImage[Player.OPPONENT] = new Texture("virusOpponent.png");
        font = new BitmapFont();
    }

    public void render(float delta) {
        SpriteBatch batch = phageWarsGame.batch;
        
        LinkedList<Cell> cells = world.getCells();
        LinkedList<Virus> virus = world.getVirus();

        batch.begin();

        batch.draw(background, 0, 0);

        for(Cell cell : cells) {
            Vector2 cellPosition = cell.getPosition();
            batch.draw(cellImage[cell.getPlayer().getPlayerType()], cellPosition.x, cellPosition.y);
            if(cell.getVirusNumber() != 0) {
                font.draw(batch, "" + cell.getVirusNumber(), cellPosition.x + Cell.IMAGE_SIZE / 2, cellPosition.y + Cell.IMAGE_SIZE / 2);
            }
            if(cell.isMouseOn()) {
                batch.draw(cellFrame, cellPosition.x - (Cell.FRAME_SIZE - Cell.IMAGE_SIZE) / 2 , cellPosition.y- (Cell.FRAME_SIZE - Cell.IMAGE_SIZE) / 2);
            }
        }

        for(Virus vi : virus) {
            Vector2 virusPosition = vi.getPosition();
            batch.draw(virusImage[vi.getPlayer().getPlayerType()], virusPosition.x, virusPosition.y);
        }

        batch.end();
    }
}
