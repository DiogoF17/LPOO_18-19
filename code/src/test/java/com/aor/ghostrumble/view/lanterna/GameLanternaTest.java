package com.aor.ghostrumble.view.lanterna;

import com.aor.ghostrumble.controller.event.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class GameLanternaTest {

    /*private GameLanterna game;

    @Before
    public void init() throws IOException {
        game = new GameLanterna();
    }

    @Test
    public void testCreateLanternaDrawingMethod() {
        assertTrue(game.createDrawingMethod() instanceof DrawLanterna);
    }

    @Test
    public void testCreateLanternaEventEscape() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        game.handleInput(queue);

        Mockito.verify(screen, times(1)).close();
        assertTrue(queue.close());
    }

    @Test
    public void testCreateLanternaEventEOF() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.EOF);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertFalse(game.handleInput(queue));
    }

    @Test
    public void testCreateLanternaEventW() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('w');

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerUp);
    }

    @Test
    public void testCreateLanternaEventA() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('a');

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerLeft);
    }

    @Test
    public void testCreateLanternaEventS() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('s');

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerDown);
    }

    @Test
    public void testCreateLanternaEventD() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('d');

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerRight);
    }

    @Test
    public void testCreateLanternaEventUP() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletUp);
    }

    @Test
    public void testCreateLanternaEventDOWN() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletDown);
    }

    @Test
    public void testCreateLanternaEventLEFT() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowLeft);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletLeft);
    }

    @Test
    public void testCreateLanternaEventRIGHT() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowRight);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletRight);
    }

    @Test
    public void testLanternaEventIrrelevantCharacter() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('k');

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().isEmpty());
    }

    @Test
    public void testLanternaEventIrrelevantKeyType() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        game.setScreen(screen);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);

        Mockito.when(screen.readInput()).thenReturn(key);

        EventQueue queue = new EventQueue();

        assertTrue(game.handleInput(queue));
        assertTrue(queue.getEventQueue().isEmpty());
    }*/

}
