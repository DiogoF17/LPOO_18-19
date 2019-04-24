package com.aor.ghostrumble;

import com.aor.ghostrumble.view.GameLanterna;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;

public class GameTest {

    @Test
    public void testLoopEndLanterna() throws IOException {
        Game game = Mockito.mock(GameLanterna.class);
        Mockito.when(game.handleInput()).thenReturn(false);
        game.run();
        verify(game).run();
    }

}
