package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GhostTest {

    private Ghost ghost;

    @Before
    public void init() {
        ghost = new Ghost(10, 20);
    }

    @Test
    public void testGhostSpeed() {
        assertEquals(Ghost.getGhostSpeed(), ghost.getSpeed());
    }

    @Test
    public void testGhostDamage() {
        assertEquals(Ghost.getGhostDamage(), ghost.getDamage());
    }

    @Test
    public void testGhostMovement() {
        assertTrue(ghost.createMovStrategy() instanceof FreeMovement);
    }

}
