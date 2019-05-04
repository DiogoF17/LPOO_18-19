package com.aor.ghostrumble.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BulletTest {

    // the Bullet subclasses were used for the unit testing
    // of Bullet functions, because the Bullet class is
    // abstract. The functions tested here are only the functions
    // that belong to Bullet, i.e. that belong to all of its
    // subclasses.

    @Test
    public void testGetDelta() {
        Bullet bullet = new HorizontalBullet(10, 10, 1);
        assertEquals(1, bullet.getDelta());
    }

    @Test
    public void testKillFlagBeginning() {
        Bullet bullet = new VerticalBullet(10, 10, 1);
        assertFalse(bullet.getKillFlag());
    }

    @Test
    public void testSetKillFlag() {
        Bullet bullet = new HorizontalBullet(10, 10, 1);
        bullet.setKillFlag(true);
        assertTrue(bullet.getKillFlag());
    }

}
