package com.aor.ghostrumble.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testGetX() {
        Random random = new Random();
        int x = random.nextInt(100 - 10) + 10;
        int y = random.nextInt(50 - 10) + 10;
        Position position = new Position(x, y);
        assertEquals(x, position.getX());
    }

    @Test
    public void testGetY() {
        Random random = new Random();
        int x = random.nextInt(100 - 10) + 10;
        int y = random.nextInt(50 - 10) + 10;
        Position position = new Position(x, y);
        assertEquals(y, position.getY());
    }

    @Test
    public void testEquals() {
        Random random = new Random();
        int x = random.nextInt(100 - 10) + 10;
        int y = random.nextInt(50 - 10) + 10;
        Position first  = new Position(x, y);
        Position second = new Position(x, y);
        assertEquals(first, second);
    }
}
