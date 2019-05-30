package com.aor.ghostrumble.play.view.swing;

import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.view.swing.ViewMenuSwing;
import com.aor.ghostrumble.play.controller.event.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class ViewGameSwingTest {

    private JFrame frame;
    private ViewGameSwing view;

    @Before
    public void initGameSwingView() {
        frame = Mockito.mock(JFrame.class);
        view = new ViewGameSwing(frame, 40, 30, 20, 10);
    }

    @Test
    public void testViewGameSwingArrowUp() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_UP);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletUp.class));
    }

    @Test
    public void testViewGameSwingArrowDown() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_DOWN);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletDown.class));
    }

    @Test
    public void testViewGameSwingArrowLeft() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_LEFT);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletLeft.class));
    }

    @Test
    public void testViewGameSwingArrowRight() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_RIGHT);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventBulletRight.class));
    }

    @Test
    public void testViewGameSwingKeyW() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_W);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerUp.class));
    }

    @Test
    public void testViewGameSwingKeyA() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_A);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerLeft.class));
    }


    @Test
    public void testViewGameSwingKeyS() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_S);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerDown.class));
    }


    @Test
    public void testViewGameSwingKeyD() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_D);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).raiseEvent(any(EventPlayerRight.class));
    }


    @Test
    public void testViewGameSwingKeyEscape() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_ESCAPE);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(1)).setClose(true);
    }

    @Test
    public void testViewGameSwingIrrelevantKeyType() {

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_E);
        EventQueue eventQueue = Mockito.mock(EventQueue.class);
        view.setQueue(eventQueue);

        view.keyPressed(e);

        Mockito.verify(eventQueue, Mockito.times(0)).raiseEvent(any(GameEvent.class));
        Mockito.verify(eventQueue, Mockito.times(0)).setClose(true);
    }


    @Test
    public void testViewGameSwingPrepareStateChange() {
        view.prepareStateChange();
        Mockito.verify(frame, Mockito.times(1)).remove(any(JComponent.class));
    }

}
