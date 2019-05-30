package com.aor.ghostrumble;

import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.play.GameState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class StateTest {

    private State state;

    @Before
    public void init() {
        ViewAbstractFactory factory = Mockito.mock(ViewAbstractFactory.class);
        state = new GameState(factory);
    }

    @Test
    public void testInitKeepGoing() {
        assertTrue(state.keepGoing());
    }

    @Test
    public void testInitObserver() {
        assertNull(state.getObserver());
    }

    @Test
    public void testSetObserver() {

        StateObserver observer = Mockito.mock(StateObserver.class);
        state.setObserver(observer);
        assertEquals(observer, state.getObserver());

    }

}
