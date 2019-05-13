package com.aor.ghostrumble.view;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.Event.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GameLanterna extends Game {

    private Screen screen;

    public GameLanterna() throws IOException {
        this(100, 35);
    }

    public GameLanterna(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();

        init(width, height);
    }

    protected void setScreen(Screen screen) {
        this.screen = screen;
    }

    protected Screen getScreen() { return this.screen; }

    @Override
    protected DrawingMethod createDrawingMethod() {
        return new DrawLanterna(screen);
    }

    private void createEvent(KeyStroke key, EventQueue eventQueue) {

        switch(key.getKeyType()) {

            case Character:

                switch(key.getCharacter()) {

                    case 'w':
                        eventQueue.addEventToQueue(new EventPlayerUp());
                        break;

                    case 'a':
                        eventQueue.addEventToQueue(new EventPlayerLeft());
                        break;

                    case 's':
                        eventQueue.addEventToQueue(new EventPlayerDown());
                        break;

                    case 'd':
                        eventQueue.addEventToQueue(new EventPlayerRight());
                        break;

                    default:
                        break;

                }
                break;

            case ArrowUp:
                eventQueue.addEventToQueue(new EventBulletUp());
                break;

            case ArrowDown:
                eventQueue.addEventToQueue(new EventBulletDown());
                break;

            case ArrowLeft:
                eventQueue.addEventToQueue(new EventBulletLeft());
                break;

            case ArrowRight:
                eventQueue.addEventToQueue(new EventBulletRight());
                break;

            case Escape:
                eventQueue.setClose(true);
                break;

            case EOF:
                eventQueue.setExit(true);
                break;

            default:
                break;

        }
    }

    @Override
    protected boolean handleInput(EventQueue eventQueue) throws IOException {

        KeyStroke key = screen.readInput();

        createEvent(key, eventQueue);

        if (eventQueue.exit())
            return false;

        if (eventQueue.close())
            screen.close();

        return true;
    }


}
