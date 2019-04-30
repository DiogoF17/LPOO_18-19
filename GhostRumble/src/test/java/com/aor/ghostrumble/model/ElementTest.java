package com.aor.ghostrumble.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;

public class ElementTest {

    private int randomX;
    private int randomY;
    private Element element;

    @Before
    public void init() {
        Random random = new Random();
        this.randomX = random.nextInt(100 - 10) + 10;
        this.randomY = random.nextInt(40 - 10) + 10;
        this.element = new Element(randomX, randomY);
    }

    @Test
    public void testInitRemoveFlag() {
        assertFalse(element.flaggedForRemoval());
    }

    @Test
    public void testSetRemoveFlag() {
        element.setRemoveFlag(true);
        assertTrue(element.flaggedForRemoval());
    }

    @Test
    public void testPosition() {
        Position position = new Position(randomX, randomY);
        assertEquals(position, element.getPosition());
    }

    @Test
    public void testSetPosition() {
        Position position = Mockito.mock(Position.class);
        element.setPosition(position);
        assertEquals(position, element.getPosition());
    }
}
