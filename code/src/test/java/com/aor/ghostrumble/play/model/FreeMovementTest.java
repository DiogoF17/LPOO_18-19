package com.aor.ghostrumble.play.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FreeMovementTest {

    @Test
    public void testUpdateDirectionDown() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(10, 11);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(0, 1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionUp() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(10, 9);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(0, -1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionRight() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(11, 10);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(1, 0);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionLeft() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(9, 10);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(-1, 0);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionDownRight() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(15, 11);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(1, 1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionDownLeft() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(5, 11);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(-1, 1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionUpRight() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(15, 9);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(1, -1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

    @Test
    public void testUpdateDirectionUpLeft() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(5, 9);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(-1, -1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

}
