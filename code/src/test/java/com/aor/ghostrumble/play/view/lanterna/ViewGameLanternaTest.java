package com.aor.ghostrumble.play.view.lanterna;

import com.aor.ghostrumble.play.controller.event.*;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ViewGameLanternaTest {

    private Screen screen;
    private ViewGameLanterna view;

    @Before
    public void initGameLanternView() {
        screen = Mockito.mock(Screen.class);
        view = new ViewGameLanterna(screen, 40, 40);
    }


    @Test
    public void testViewGameLanternaReadInputCall() throws IOException {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.readInput()).thenReturn(key);
        view.handleInput();
        Mockito.verify(screen, Mockito.times(1)).readInput();
    }

    @Test
    public void testViewGameLanternaArrowUp() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletUp.class));
    }

    @Test
    public void testViewGameLanternaArrowDown() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletDown.class));
    }

    @Test
    public void testViewGameLanternaArrowLeft() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletLeft.class));
    }

    @Test
    public void testViewGameLanternaArrowRight() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletRight.class));
    }

    @Test
    public void testViewGameLanternaKeyW() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('w');
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerUp.class));
    }

    @Test
    public void testViewGameLanternaKeyA() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('a');
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerLeft.class));
    }

    @Test
    public void testViewGameLanternaKeyS() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('s');
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerDown.class));
    }

    @Test
    public void testViewGameLanternaKeyD() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('d');
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerRight.class));
    }

    @Test
    public void testViewGameLanternaEscape() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(1)).setClose(true);
    }

    @Test
    public void testViewGameLanternaIrrelevantKeyType() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Backspace);
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(0)).raiseEvent(any(GameEvent.class));
        Mockito.verify(eventQueue, Mockito.times(0)).setClose(true);
    }

    @Test
    public void testViewGameLanternaIrrelevantCharacter() throws IOException {

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('t');
        Mockito.when(screen.readInput()).thenReturn(key);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);
        view.handleInput();

        Mockito.verify(eventQueue, Mockito.times(0)).raiseEvent(any(GameEvent.class));
        Mockito.verify(eventQueue, Mockito.times(0)).setClose(true);
    }

    @Test
    public void testViewGameLanternaPrepareStateChange() {
        TextGraphics g = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(g);
        view.prepareStateChange();
        Mockito.verify(g, Mockito.times(1)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(g, Mockito.times(1)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), eq(' '));
    }
}
