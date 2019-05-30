package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.factory.ViewSwingFactory;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuStateTest {

    private MenuState state;

    // the MenuState subclasses were used for the unit testing of
    // MenuState functions, because the MenuState class is abstract.

    // Also, the methods tested do not depend on what the drawing
    // interface is (Swing or Lanterna), so the factory chose to
    // initiate the state is not relevant.


    @Before
    public void initMenuState() {
        ViewAbstractFactory fac = new ViewSwingFactory();
        state = new GameOverState(fac, 0);
    }

    @Test
    public void testMenuStateKeepGoing() {
        assertTrue(state.keepGoing());
    }

    @Test
    public void testMenuStateGetModel() {
        MenuModel model = Mockito.mock(MenuModel.class);
        state.setModel(model);
        assertEquals(state.getModel(), model);
    }

    @Test
    public void testMenuStateGetView() {
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);
        assertEquals(state.getView(), view);
    }


    @Test
    public void testMenuStateHandleInput() {
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);

        state.handleInput();
        Mockito.verify(view, Mockito.times(1)).handleInput();
    }

    @Test
    public void testMenuStateDraw() {
        MenuModel model = Mockito.mock(MenuModel.class);
        state.setModel(model);
        ViewMenu view = Mockito.mock(ViewMenu.class);
        state.setView(view);

        state.draw();

        Mockito.verify(view, Mockito.times(1)).drawAll(model);
    }

}
