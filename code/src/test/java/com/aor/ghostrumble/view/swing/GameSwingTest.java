package com.aor.ghostrumble.view.swing;

import com.aor.ghostrumble.controller.event.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

public class GameSwingTest {

    /*private GameSwing game;

    @Before
    public void init() {
        game = new GameSwing();
    }

    @Test
    public void testCreateSwingDrawingMethod() {
        assertTrue(game.createDrawingMethod() instanceof DrawSwingGame);
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
    public void testCreateSwingEventW() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_W);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);

        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerUp);
    }


    @Test
    public void testCreateSwingEventA() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_A);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);

        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerLeft);
    }


    @Test
    public void testCreateSwingEventS() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_S);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerDown);
    }


    @Test
    public void testCreateSwingEventD() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_D);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().peek() instanceof EventPlayerRight);
    }


    @Test
    public void testCreateSwingEventUP() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_UP);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletUp);
    }


    @Test
    public void testCreateSwingEventDOWN() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_DOWN);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletDown);
    }


    @Test
    public void testCreateSwingEventLEFT() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_LEFT);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletLeft);
    }


    @Test
    public void testCreateSwingEventRIGHT() {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_RIGHT);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().peek() instanceof EventBulletRight);
    }


    @Test
    public void testCreateSwingIrrelevantKey() throws IOException {
        JFrame frame = Mockito.mock(JFrame.class);
        game.setFrame(frame);

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_ENTER);

        EventQueue queue = new EventQueue();
        game.setEventQueue(queue);

        game.keyPressed(e);
        assertTrue(queue.getEventQueue().isEmpty());
        assertTrue(game.handleInput(queue));
    }*/

}
