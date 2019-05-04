package com.aor.ghostrumble.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HorizontalBulletTest {

    @Test
    public void testMove() {
        Bullet bullet = new HorizontalBullet(10, 10, 1);
        Position position = new Position(11, 10);
        assertEquals(position, bullet.move());
    }

}
