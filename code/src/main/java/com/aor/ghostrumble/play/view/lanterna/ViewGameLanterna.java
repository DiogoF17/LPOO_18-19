package com.aor.ghostrumble.play.view.lanterna;

import com.aor.ghostrumble.play.controller.event.*;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.view.ViewGame;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ViewGameLanterna extends ViewGame {

    private Screen screen;
    private DrawLanternaGame drawer;

    /*public ViewGameLanterna() {
        this(100, 35);
    }*/

    /*public ViewGameLanterna(int width, int height)  {
        /*try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            this.screen = new TerminalScreen(terminal);

            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();

            // init(width, height);

            this.drawer = new DrawLanternaGame(screen);

        } catch(IOException e ) {
            e.printStackTrace();
        }


    }*/

    public ViewGameLanterna(Screen screen) {
        this.screen = screen;
        this.drawer = new DrawLanternaGame(screen);
    }

    private void createEvent(KeyStroke key) {

        switch(key.getKeyType()) {

            case Character:

                switch(key.getCharacter()) {

                    case 'w':
                        queue.raiseEvent(new EventPlayerUp());
                        break;

                    case 'a':
                        queue.raiseEvent(new EventPlayerLeft());
                        break;

                    case 's':
                        queue.raiseEvent(new EventPlayerDown());
                        break;

                    case 'd':
                        queue.raiseEvent(new EventPlayerRight());
                        break;

                    default:
                        break;

                }
                break;

            case ArrowUp:
                queue.raiseEvent(new EventBulletUp());
                break;

            case ArrowDown:
                queue.raiseEvent(new EventBulletDown());
                break;

            case ArrowLeft:
                queue.raiseEvent(new EventBulletLeft());
                break;

            case ArrowRight:
                queue.raiseEvent(new EventBulletRight());
                break;

            case Escape:
                queue.setClose(true);
                break;

            case EOF:
                queue.setExit(true);
                break;

            default:
                break;

        }
    }

    @Override
    public void handleInput() {
        try {
            KeyStroke key = screen.readInput();
            createEvent(key);

/*
            if (queue.exit())
                return false;

            if (queue.close())
                screen.close();
*/

        } catch(IOException e) {
            e.printStackTrace();
        }
/*

        return true;
*/
    }


    @Override
    public void drawAll(HauntedHouse house) {
        try {
            drawer.drawAll(house);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
