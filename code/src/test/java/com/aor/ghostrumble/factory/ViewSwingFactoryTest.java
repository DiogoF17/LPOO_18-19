package com.aor.ghostrumble.factory;

import com.aor.ghostrumble.menu.view.swing.ViewMenuSwing;
import com.aor.ghostrumble.play.view.swing.ViewGameSwing;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ViewSwingFactoryTest {

    private ViewSwingFactory fac;

    @Before
    public void initSwingFac() {
        fac = new ViewSwingFactory(40, 30, 20, 10);
    }


    @Test
    public void testReturnSwingViewMenu() {
        assertTrue(fac.createMenuView() instanceof ViewMenuSwing);
    }


    @Test
    public void testReturnSwingViewGame() {
        assertTrue(fac.createGameView() instanceof ViewGameSwing);
    }


    @Test
    public void testSwingFactoryWidth() {
        assertEquals(fac.getScreenWidth(), 40);
    }

    @Test
    public void testSwingFactoryHeight() {
        assertEquals(fac.getScreenHeight(), 30);
    }

    @Test
    public void testSwingFactoryTileSize() {
        assertEquals(fac.getTileSize(), 20);
    }

    @Test
    public void testSwingFactoryBorderOffset() {
        assertEquals(fac.getBorderOffset(), 10);
    }

    @Test
    public void testFactorySwingClose() {
        JFrame frame = Mockito.mock(JFrame.class);
        fac.setFrame(frame);

        fac.close();

        Mockito.verify(frame, Mockito.times(1)).removeAll();
        Mockito.verify(frame, Mockito.times(1)).dispose();
    }

}
