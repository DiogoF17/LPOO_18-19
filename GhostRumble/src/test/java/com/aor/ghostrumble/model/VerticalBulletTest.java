package com.aor.ghostrumble.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerticalBulletTest {

    @Test
    public void testMove() {
        Bullet bullet = new VerticalBullet(10, 10, 1);
        Position position = new Position(10, 11);
        assertEquals(position, bullet.move());
    }

    @Test
    public void testHorBulletSpeed() {

        Bullet bullet = new VerticalBullet(10, 10, 1);
        assertEquals(VerticalBullet.getVertBulletSpeed(), bullet.getSpeed());
    }

}
