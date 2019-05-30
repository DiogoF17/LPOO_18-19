package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.factory.ViewSwingFactory;
import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class MainMenuStateTest {

    private MainMenuState state;

    @Before
    public void initMainMenuState() {
        ViewAbstractFactory fac = new ViewSwingFactory();
        state = new MainMenuState(fac);
    }


    @Test
    public void testKeepGoingInitial() {
        assertTrue(state.keepGoing());
    }

    @Test
    public void testUpdate() {
        MenuModel model = Mockito.mock(MenuModel.class);
        state.setModel(model);
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);
        MenuEvent event = Mockito.mock(MenuEvent.class);
        Mockito.when(event.process(model)).thenReturn(true);

        // continuar
    }
}
