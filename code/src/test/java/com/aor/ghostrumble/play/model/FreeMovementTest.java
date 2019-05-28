package com.aor.ghostrumble.play.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FreeMovementTest {

    @Test
    public void testUpdateDirection() {
        MovementStrategy strategy = new FreeMovement();
        Position target = new Position(15, 11);
        Position mover = new Position(10, 10);
        strategy.updateDirection(target, mover);

        Position expected = new Position(1, 1);
        Position relative = new Position(strategy.getDeltaX(), strategy.getDeltaY());

        assertTrue(expected.equals(relative));
    }

}
