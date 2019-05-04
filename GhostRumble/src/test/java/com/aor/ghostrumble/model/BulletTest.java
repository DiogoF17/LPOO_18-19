package com.aor.ghostrumble.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testGetBulletSpeed() {

        /**
         * NA DÚVIDA EM RELAÇÃO AO USO DA FUNÇÃO
         * GETSPEED(), DA CLASS AUTOMOVABLE
         */

        Bullet bullet = new VerticalBullet(10, 10, 1);
        assertEquals(Bullet.getBulletSpeed(), bullet.getSpeed());
    }
}
