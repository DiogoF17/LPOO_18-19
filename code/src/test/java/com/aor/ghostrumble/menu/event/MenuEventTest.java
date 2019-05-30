package com.aor.ghostrumble.menu.event;

import com.aor.ghostrumble.menu.model.MenuModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MenuEventTest {

    @Test
    public void testEventChangeOption() {
        MenuModel menuModel = Mockito.mock(MenuModel.class);
        MenuEvent event = new EventChangeOption();
        assertFalse(event.process(menuModel));
        Mockito.verify(menuModel, Mockito.times(1)).changeOption();
    }

    @Test
    public void testEventConfirmOption() {
        MenuModel menuModel = Mockito.mock(MenuModel.class);
        MenuEvent event = new EventConfirmOption();
        assertTrue(event.process(menuModel));
    }

    @Test
    public void testNullMenuEvent() {
        MenuModel menuModel = Mockito.mock(MenuModel.class);
        MenuEvent event = new NullEvent();
        assertFalse(event.process(menuModel));
    }
}
