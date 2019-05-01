package com.aor.ghostrumble.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MovableTest {

    private Movable movable;

    // the Movable subclasses were used for the unit testing of
    // Movable functions, because the Movable class is abstract.
    // The functions tested here are only the functions that
    // belong to Movable, i.e. that belong to all of its
    // subclasses.

    @Before
    public void init() {
        movable = new Player(10, 10);
    }

    @Test
    public void testMoveCustom() {
        Position expected = new Position(15, 15);
        assertTrue(expected.equals(movable.moveCustom(5, 5)));
    }

    @Test
    public void testMoveUp() {
        Position expected = new Position(10, 9);
        assertTrue(expected.equals(movable.moveUp()));
    }

    @Test
    public void testMoveDown() {
        Position expected = new Position(10, 11);
        assertTrue(expected.equals(movable.moveDown()));
    }

    @Test
    public void testMoveLeft() {
        Position expected = new Position(9, 10);
        assertTrue(expected.equals(movable.moveLeft()));
    }

    @Test
    public void testMoveRight() {
        Position expected = new Position(11, 10);
        assertTrue(expected.equals(movable.moveRight()));
    }
}
