package com.aor.ghostrumble.factory;

import com.aor.ghostrumble.menu.view.lanterna.ViewMenuLanterna;
import com.aor.ghostrumble.play.view.lanterna.ViewGameLanterna;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ViewLanternaFactoryTest {

    private ViewLanternaFactory fac;

    @Before
    public void initLanternaFac() {
        fac = new ViewLanternaFactory(40, 20);
    }

    @Test
    public void testReturnLanternaViewMenu() {
        assertTrue(fac.createMenuView() instanceof ViewMenuLanterna);
    }

    @Test
    public void testReturnLanternaViewGame() {
        assertTrue(fac.createGameView() instanceof ViewGameLanterna);
    }


    @Test
    public void testFactoryLanternaWidth() {
        assertEquals(fac.getScreenWidth(), 40);
    }

    @Test
    public void testFactoryLanternaHeight() {
        assertEquals(fac.getScreenHeight(), 20);
    }


    @Test
    public void testFactoryLanternaClose() {
        Screen screen = Mockito.mock(Screen.class);
        fac.setScreen(screen);

        fac.close();

        try {
            Mockito.verify(screen, Mockito.times(1)).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
