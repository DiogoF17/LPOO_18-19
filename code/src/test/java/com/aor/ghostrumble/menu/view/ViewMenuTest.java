package com.aor.ghostrumble.menu.view;

import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.view.lanterna.ViewMenuLanterna;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ViewMenuTest {

    private ViewMenu view;

    // the ViewMenu subclasses were used for the unit testing of
    // ViewMenu functions, because the ViewMenu class is abstract.
    // The functions tested here are only the functions that
    // belong to ViewMenu, i.e. that belong to all of its
    // subclasses.

    @Before
    public void initMenuView() {
        Screen screen = Mockito.mock(Screen.class);
        view = new ViewMenuLanterna(screen, 40, 40);
    }

    @Test
    public void testStartWithNullEvent() {
        assertTrue(view.getEvent() instanceof NullEvent);
    }


    @Test
    public void setEvent() {
        MenuEvent event = Mockito.mock(MenuEvent.class);
        view.setEvent(event);
        assertEquals(view.getEvent(), event);
    }


}
