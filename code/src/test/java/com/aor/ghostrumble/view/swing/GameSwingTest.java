package com.aor.ghostrumble.view.swing;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.event.EventPlayerUp;
import com.aor.ghostrumble.controller.event.EventQueue;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

public class GameSwingTest {

    private GameSwing game;

    @Before
    public void init() {
        game = new GameSwing();
    }

    @Test
    public void testCreateSwingDrawingMethod() {
        assertTrue(game.createDrawingMethod() instanceof DrawSwing);
    }


    @Test
    public void testCreateSwingEventEscape() throws IOException {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_ESCAPE);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);

        game.handleInput(queue);

        Mockito.verify(frame, times(1)).setVisible(false);
        Mockito.verify(frame, times(1)).dispose();
        assertTrue(queue.close());
    }


    @Test
    public void testCreateSwingEventClose() throws IOException {

        EventQueue queue = new EventQueue();
        queue.setExit(true);
        game.setEventQueue(queue);

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

}
