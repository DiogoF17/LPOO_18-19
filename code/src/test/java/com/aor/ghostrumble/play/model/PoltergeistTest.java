package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PoltergeistTest {

    private Poltergeist poltergeist;

    @Before
    public void init() {
        poltergeist = new Poltergeist(10, 20);
    }

    @Test
    public void testPoltergeistSpeed() {
        assertEquals(Poltergeist.getPoltergeistSpeed(), poltergeist.getSpeed());
    }

    @Test
    public void testPoltergeistDamage() {
        assertEquals(Poltergeist.getPoltergeistDamage(), poltergeist.getDamage());
    }

    @Test
    public void testPoltergeistMovement() {
        MovementStrategy strat = poltergeist.createMovStrategy();
        assertTrue(strat instanceof LinearMovement || strat instanceof FreeMovement);
    }
}
