package com.aor.ghostrumble.menu.view.lanterna;

import com.aor.ghostrumble.menu.event.EventChangeOption;
import com.aor.ghostrumble.menu.event.EventConfirmOption;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ViewMenuLanternaTest {

    private Screen screen;
    private ViewMenuLanterna view;

    @Before
    public void initMenuLanternaView() {
        screen = Mockito.mock(Screen.class);
        view = new ViewMenuLanterna(screen, 40, 40);
    }

    @Test
    public void testViewMenuLanternaReadInputCall() throws IOException {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        Mockito.verify(screen, Mockito.times(1)).readInput();
    }


    @Test
    public void testViewMenuLanternaArrowUp() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        assertTrue(view.getEvent() instanceof EventChangeOption);
    }

    @Test
    public void testViewMenuLanternaArrowDown() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        assertTrue(view.getEvent() instanceof EventChangeOption);
    }

    @Test
    public void testViewMenuLanternaEnter() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        assertTrue(view.getEvent() instanceof EventConfirmOption);
    }

    @Test
    public void testViewMenuLanternaIrrelevantKeyType() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        assertTrue(view.getEvent() instanceof NullEvent);
    }

    @Test
    public void testViewMenuLanternaIrrelevantCharacter() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(key.getCharacter()).thenReturn('a');
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        assertTrue(view.getEvent() instanceof NullEvent);
    }


    @Test
    public void testViewMenuLanternaPrepareStateChange() {
        TextGraphics g = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(g);
        view.prepareStateChange();
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(g, Mockito.times(1)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), eq(' '));
    }
}
