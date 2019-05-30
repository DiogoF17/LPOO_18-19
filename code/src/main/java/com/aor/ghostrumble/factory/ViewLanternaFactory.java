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
    private int screenWidth;
    private int screenHeight;

    public ViewLanternaFactory() {
        this(100, 35);
    }


    public ViewLanternaFactory(int screenWidth, int screenHeight) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(screenWidth, screenHeight)).createTerminal();
            this.screen = new TerminalScreen(terminal);

            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setScreen(Screen screen) {
        this.screen = screen;
    }


    public int getScreenWidth() { return screenWidth; }
    public int getScreenHeight() { return screenHeight; }

    public ViewMenu createMenuView() {
        return new ViewMenuLanterna(screen, screenWidth, screenHeight);
    }

    public ViewGame createGameView() {
        return new ViewGameLanterna(screen, screenWidth, screenHeight);
    }

    public void close() {

        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
