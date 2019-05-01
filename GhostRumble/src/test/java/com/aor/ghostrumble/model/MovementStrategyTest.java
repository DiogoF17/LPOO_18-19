package com.aor.ghostrumble.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovementStrategyTest {

    // The MovementStrategy subclasses were used for the unit
    // testing of MovementStrategy functions, because the
    // MovementStrategy class is abstract. The functions tested
    // here are only the functions that belong to MovementStrategy,
    // i.e. that belong to all of its subclasses.

    MovementStrategy initLinear() { return new LinearMovement(); }

    @Test
    public void testGetDeltaX() {
        MovementStrategy strategy = initLinear();
        assertEquals(0, strategy.getDeltaX());
    }

    @Test
    public void testGetDeltaY() {
        MovementStrategy strategy = initLinear();
        assertEquals(0, strategy.getDeltaY());
    }

    @Test
    public void testSetDeltaX() {
        MovementStrategy strategy = initLinear();
        strategy.setDeltaX(1);
        assertEquals(1, strategy.getDeltaX());
    }

    @Test
    public void testSetDeltaY() {
        MovementStrategy strategy = initLinear();
        strategy.setDeltaY(1);
        assertEquals(1, strategy.getDeltaY());
    }

    @Test
    public void testMove() {
        MovementStrategy strategy = initLinear();
        strategy.setDeltaX(1);
        strategy.setDeltaY(1);
        Enemy enemy = new Zombie(10, 10);
        Position position = new Position(11, 11);

        assertEquals(position, strategy.move(enemy));
    }
}
