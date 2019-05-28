package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;

public class AutoMovableTest {

    private AutoMovable autoMovable;

    // the AutoMovable subclasses were used for the unit testing
    // of AutoMovable functions, because the AutoMovable class is
    // abstract. The functions tested here are only the functions
    // that belong to AutoMovable, i.e. that belong to all of its
    // subclasses.

    @Before
    public void init() {
        autoMovable = new Zombie(10, 10);
    }

    @Test
    public void testGetSpeed() {
        assertEquals(Zombie.getZombieSpeed(), autoMovable.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        autoMovable.setSpeed(100);
        assertEquals(100, autoMovable.getSpeed());
    }

    @Test
    public void testGetLastMoved() {
        assertEquals(0, autoMovable.getLastMoved());
    }

    @Test
    public void testSetLastMoved() {
        long test = currentTimeMillis();
        autoMovable.setLastMoved(test);
        assertEquals(test, autoMovable.getLastMoved());
    }
}
