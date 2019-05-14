package com.aor.ghostrumble;

import com.aor.ghostrumble.controller.event.EventQueue;
import com.aor.ghostrumble.view.GameLanterna;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;

import java.io.IOException;

public class GameTest {

    @Test
    public void testLoopEnd() throws IOException {
        Game game = Mockito.mock(GameLanterna.class);
        Mockito.when(game.handleInput(any(EventQueue.class))).thenReturn(false);
        game.run();
        Mockito.verify(game).run();
    }

}
