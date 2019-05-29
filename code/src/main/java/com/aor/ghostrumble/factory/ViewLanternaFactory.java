package com.aor.ghostrumble.factory;

import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.menu.view.lanterna.ViewMenuLanterna;
import com.aor.ghostrumble.play.view.ViewGame;
import com.aor.ghostrumble.play.view.lanterna.ViewGameLanterna;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ViewLanternaFactory implements ViewAbstractFactory {

    private Screen screen;

    private final static int SCREEN_WIDTH = 100;
    private final static int SCREEN_HEIGHT = 35;

    public ViewLanternaFactory() {

        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(SCREEN_WIDTH, SCREEN_HEIGHT)).createTerminal();
            this.screen = new TerminalScreen(terminal);

            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getScreenWidth() { return SCREEN_WIDTH; }
    public int getScreenHeight() { return SCREEN_HEIGHT; }

    public ViewMenu createMenuView() {
        return new ViewMenuLanterna(screen, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public ViewGame createGameView() {
        return new ViewGameLanterna(screen, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void close() {

        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
