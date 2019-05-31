package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.StateObserver;
import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.factory.ViewSwingFactory;
import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.play.GameState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class MainMenuStateTest {

    private MainMenuState state;

    @Before
    public void initMainMenuState() {

        ViewAbstractFactory fac = Mockito.mock(ViewSwingFactory.class);
        state = new MainMenuState(fac);

    }

    @Test
    public void testKeepGoingInitial() {
        assertTrue(state.keepGoing());
    }

    @Test
    public void testUpdatePrepareStateChange() {

        MenuModel model = Mockito.mock(MenuModel.class);
        state.setModel(model);
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);
        MenuEvent event = Mockito.mock(MenuEvent.class);
        Mockito.when(event.process(model)).thenReturn(true);
        Mockito.when(view.getEvent()).thenReturn(event);

        state.update();

        Mockito.verify(view, times(1)).prepareStateChange();

    }

    @Test
    public void testUpdateSwitchToGameState() {

        MenuModel model = Mockito.mock(MenuModel.class);
        state.setModel(model);
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);
        MenuEvent event = Mockito.mock(MenuEvent.class);
        Mockito.when(event.process(model)).thenReturn(true);
        Mockito.when(view.getEvent()).thenReturn(event);
        Mockito.when(model.willPlay()).thenReturn(true);
        StateObserver observer = Mockito.mock(StateObserver.class);
        state.setObserver(observer);

        state.update();

        Mockito.verify(observer, times(1)).changeState(any(GameState.class));

    }


    @Test
    public void testUpdateEndGame() {

        MenuModel model = Mockito.mock(MenuModel.class);
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);
        MenuEvent event = Mockito.mock(MenuEvent.class);
        Mockito.when(event.process(model)).thenReturn(true);
        Mockito.when(view.getEvent()).thenReturn(event);
        Mockito.when(model.willPlay()).thenReturn(false);
        state.setModel(model);

        state.update();

        Mockito.verify(state.getFactory(), times(1)).close();

        assertFalse(state.keepGoing());

    }

    @Test
    public void testResetEvent() {

        MenuModel model = Mockito.mock(MenuModel.class);
        ViewMenu view = Mockito.spy(ViewMenu.class);
        state.setView(view);
        MenuEvent event = Mockito.mock(MenuEvent.class);
        Mockito.when(event.process(model)).thenReturn(false);
        view.setEvent(event);
        state.setModel(model);

        state.update();

        assertTrue(view.getEvent() instanceof NullEvent);

    }

    @Test
    public void testFactoryClose() {

    }

}
