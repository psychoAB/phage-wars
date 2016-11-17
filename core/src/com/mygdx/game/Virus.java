package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Virus {

    public static final int VIRUS_SPEED = 1;

    private Player player;
    private Vector2 position;
    private Cell targetCell;

    public Virus(int x, int y,Cell targetCell, Player player) {
        this.player = player;
        this.targetCell = targetCell;

        Random rand = new Random();
        int shiftX = rand.nextInt(80) - 40;
        int shiftY = rand.nextInt(80) - 40;

        position = new Vector2(x + Cell.IMAGE_SIZE / 2 + shiftX, y + Cell.IMAGE_SIZE / 2 + shiftY);
    }

    public void update() {
        Random rand = new Random();
        int shiftX = rand.nextInt(150) - 75;
        int shiftY = rand.nextInt(150) - 75;
        int shiftSpeed = rand.nextInt(3) - 1;

        Vector2 targetPosition = new Vector2(targetCell.getPosition().x + (Cell.IMAGE_SIZE / 2) - position.x + shiftX, targetCell.getPosition().y + (Cell.IMAGE_SIZE / 2) - position.y + shiftY);

        if(targetPosition.x >= 0) {
            position.x += VIRUS_SPEED + shiftSpeed;
        }
        else {
            position.x -= VIRUS_SPEED + shiftSpeed;
        }
        if(targetPosition.y >= 0) {
            position.y += VIRUS_SPEED + shiftSpeed;
        }
        else {
            position.y -= VIRUS_SPEED + shiftSpeed;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }

    public Cell getTargetCell() {
        return targetCell;
    }
}
