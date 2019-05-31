package com.aor.ghostrumble;

import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.menu.MainMenuState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void init() {

        ViewAbstractFactory factory = Mockito.mock(ViewAbstractFactory.class);
        game = new Game(factory);

    }

    @Test
    public void testInitState() {
        assertTrue(game.getState() instanceof MainMenuState);
    }

    @Test
    public void testChangeState() {

        State state = Mockito.mock(State.class);
        game.changeState(state);
        assertEquals(state, game.getState());

    }

    @Test
    public void testNewStateObserver() {

        State state = Mockito.mock(State.class);
        game.changeState(state);
        Mockito.verify(state, Mockito.times(1)).setObserver(game);

    }

     // Devido a varios fatores, o metodo run() nao foi testado. Isto deve-se a:
     //   - o facto de os metodos que sao chamados pelo metodo run() ja serem testados
     //     por si so
     //   - o facto de o metodo run() utilizar multi-threading, o que dificulta
     //     bastante o uso de mocks/spies para fiscalizar o numero de chamadas aos
     //     metodos dentro de run()
     //   - o facto de se poder comprovar o devido funcionamento deste metodo tendo em
     //     conta apenas a sua simplicidade (nao faz nenhum processamento independente,
     //     apenas mantem a "cadeia" de acoes entre as varias partes do MVC) e o
     //     correto funcionamento do jogo, quando corrido efetivamente, fora dos testes

}
