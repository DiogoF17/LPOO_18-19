package com.aor.ghostrumble.menu.view.swing;

import com.aor.ghostrumble.menu.event.EventChangeOption;
import com.aor.ghostrumble.menu.event.EventConfirmOption;
import com.aor.ghostrumble.menu.event.NullEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class ViewMenuSwingTest {

    private JFrame frame;
    private ViewMenuSwing view;

    @Before
    public void initMenuSwingView() {
        frame = Mockito.mock(JFrame.class);
        view = new ViewMenuSwing(frame, 40, 30, 20, 10);
    }


    @Test
    public void testViewMenuSwingArrowUp() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_UP);

        view.keyPressed(e);
        assertTrue(view.getEvent() instanceof EventChangeOption);
    }

    @Test
    public void testViewMenuSwingArrowDown() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_DOWN);

        view.keyPressed(e);
        assertTrue(view.getEvent() instanceof EventChangeOption);
    }

    @Test
    public void testViewMenuSwingEnter() {
        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_ENTER);

        view.keyPressed(e);
        assertTrue(view.getEvent() instanceof EventConfirmOption);
    }

    @Test
    public void testViewMenuSwingIrrelevantKeyType() {

        KeyEvent e = Mockito.mock(KeyEvent.class);
        Mockito.when(e.getKeyCode()).thenReturn(VK_E);

        view.keyPressed(e);
        assertTrue(view.getEvent() instanceof NullEvent);
    }

    @Test
    public void testViewMenuSwingPrepareStateChange() {

        view.prepareStateChange();
        Mockito.verify(frame, Mockito.times(1)).remove(any(JComponent.class));
    }
}
