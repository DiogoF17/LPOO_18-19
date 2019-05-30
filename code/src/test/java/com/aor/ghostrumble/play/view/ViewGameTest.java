package com.aor.ghostrumble.play.view;

import com.aor.ghostrumble.play.controller.event.EventQueue;
import com.aor.ghostrumble.play.view.lanterna.ViewGameLanterna;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;

public class ViewGameTest {

    private ViewGame view;

    // the ViewGame subclasses were used for the unit testing of
    // ViewGame functions, because the ViewGame class is abstract.
    // The functions tested here are only the functions that
    // belong to ViewGame, i.e. that belong to all of its
    // subclasses.

    @Before
    public void initGameView() {
        Screen screen = Mockito.mock(Screen.class);
        view = new ViewGameLanterna(screen, 40, 40);
    }

    @Test
    public void testGameViewGetEventQueue() {
        EventQueue queue = Mockito.mock(EventQueue.class);
        view.setQueue(queue);
        assertEquals(view.getQueue(), queue);
    }
}
