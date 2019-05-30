package com.aor.ghostrumble.play;

import com.aor.ghostrumble.State;
import com.aor.ghostrumble.StateObserver;
import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.factory.ViewSwingFactory;
import com.aor.ghostrumble.menu.GameOverState;
import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.controller.event.EventQueue;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.view.ViewGame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class GameStateTest {

    private GameState state;

    @Before
    public void initGameState() {
        ViewAbstractFactory fac = new ViewSwingFactory();
        state = new GameState(fac);
    }

    @Test
    public void testGameStateGetModel() {
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        state.setModel(house);
        assertEquals(state.getModel(), house);
    }

    @Test
    public void testGameStateGetView() {
        ViewGame view = Mockito.mock(ViewGame.class);
        state.setView(view);
        assertEquals(state.getView(), view);
    }

    @Test
    public void testGameStateGetController() {
        Updater updater = Mockito.mock(Updater.class);
        state.setController(updater);
        assertEquals(state.getController(), updater);
    }

    @Test
    public void testGameStateHandleInput() {
        ViewGame view = Mockito.mock(ViewGame.class);
        state.setView(view);

        state.handleInput();
        Mockito.verify(view, Mockito.times(1)).handleInput();
    }


    @Test
    public void testGameStateDraw() {
        HauntedHouse model = Mockito.mock(HauntedHouse.class);
        state.setModel(model);
        ViewGame view = Mockito.mock(ViewGame.class);
        state.setView(view);

        state.draw();

        Mockito.verify(view, Mockito.times(1)).drawAll(model);
    }


    @Test
    public void testGameStateUpdateContinue() {
        HauntedHouse model = Mockito.mock(HauntedHouse.class);
        state.setModel(model);
        ViewGame view = Mockito.mock(ViewGame.class);
        EventQueue queue = Mockito.mock(EventQueue.class);
        Mockito.when(view.getQueue()).thenReturn(queue);
        state.setView(view);
        Updater updater = Mockito.mock(Updater.class);
        Mockito.when(updater.update(queue, model)).thenReturn(true);
        state.setController(updater);
        StateObserver observer = Mockito.mock(StateObserver.class);
        state.setObserver(observer);

        state.update();

        Mockito.verify(view, Mockito.times(0)).prepareStateChange();
        Mockito.verify(observer, Mockito.times(0)).changeState(any(State.class));
    }


    @Test
    public void testGameStateUpdateSwitchToGameOver() {
        HauntedHouse model = Mockito.mock(HauntedHouse.class);
        state.setModel(model);
        ViewGame view = Mockito.mock(ViewGame.class);
        EventQueue queue = Mockito.mock(EventQueue.class);
        Mockito.when(view.getQueue()).thenReturn(queue);
        state.setView(view);
        Updater updater = Mockito.mock(Updater.class);
        Mockito.when(updater.update(queue, model)).thenReturn(false);
        state.setController(updater);
        StateObserver observer = Mockito.mock(StateObserver.class);
        state.setObserver(observer);

        state.update();

        Mockito.verify(view, Mockito.times(1)).prepareStateChange();
        Mockito.verify(observer, Mockito.times(1)).changeState(any(GameOverState.class));

        // to verify if the final score is being passed to the new state
        Mockito.verify(model, Mockito.times(1)).getScore();
    }

}
