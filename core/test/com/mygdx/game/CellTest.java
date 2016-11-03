package com.mygdx.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class CellTest {
    
    @Test public void cellShouldBeCreate() {
        Cell cell = new Cell(100, 100, new World());
        assertNotEquals(null, cell);
    }

    @Test public void cellShouldBeInRightPosition() {
        Cell cell = new Cell(100, 120, new World());
        Vector2 positon = cell.getPosition();
        assertEquals(100, positon.x, 0.01);
        assertEquals(120, positon.y, 0.01);
    }

    @Test public void cellCreatedWithZeroVirusNumber() {
        Cell cell = new Cell(100, 120,new World());
        assertEquals(0, cell.getVirusNumber());
    }
    
}
