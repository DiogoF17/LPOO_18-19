package com.aor.ghostrumble.menu.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuModelTest {

    private MenuModel model;

    @Before
    public void initMenuModel() {
        model = new MenuModel("LPOO", "é", "top!");
    }


    @Test
    public void testWillPlay() {
        assertTrue(model.willPlay());
    }


    @Test
    public void testGetTitle() {
        assertEquals(model.getTitle(), "LPOO");
    }

    @Test
    public void testGetFirst() {
        assertEquals(model.getFirst(), "é");
    }

    @Test
    public void testGetSecond() {
        assertEquals(model.getSecond(), "top!");
    }


    @Test
    public void testChangeOption() {
        assertTrue(model.willPlay());
        model.changeOption();
        assertFalse(model.willPlay());
        model.changeOption();
        assertTrue(model.willPlay());
    }
}
