package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ZombieTest {

    private Zombie zombie;

    @Before
    public void init() {
        zombie = new Zombie(10, 20);
    }

    @Test
    public void testZombieSpeed() {
        assertEquals(Zombie.getZombieSpeed(), zombie.getSpeed());
    }

    @Test
    public void testZombieDamage() {
        assertEquals(Zombie.getZombieDamage(), zombie.getDamage());
    }

    @Test
    public void testZombieMovement() {
        assertTrue(zombie.createMovStrategy() instanceof LinearMovement);
    }
}
