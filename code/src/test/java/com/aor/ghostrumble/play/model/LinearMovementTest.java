package com.aor.ghostrumble.play.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearMovementTest {

    @Test
    public void testUpdateDirection() {
        MovementStrategy strategy = new LinearMovement();
        Position target = new Position(15, 11);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(1, 0);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

}
