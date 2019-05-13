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

    private void createEvent(KeyStroke key, Event event) {

        switch(key.getKeyType()) {

            case Character:

                switch(key.getCharacter()) {

                    case 'w':
                        event.setEventType(new EventPlayerUp());
                        break;

                    case 'a':
                        event.setEventType(new EventPlayerLeft());
                        break;

                    case 's':
                        event.setEventType(new EventPlayerDown());
                        break;

                    case 'd':
                        event.setEventType(new EventPlayerRight());
                        break;

                    default:
                        event.setEventType(new NoEvent());
                        break;

                }
                break;

            case ArrowUp:
                event.setEventType(new EventBulletUp());
                break;

            case ArrowDown:
                event.setEventType(new EventBulletDown());
                break;

            case ArrowLeft:
                event.setEventType(new EventBulletLeft());
                break;

            case ArrowRight:
                event.setEventType(new EventBulletRight());
                break;

            case Escape:
                event.setClose(true);
                break;

            case EOF:
                event.setExit(true);
                break;

            default:
                event.setEventType(new NoEvent());
                break;

        }
    }

    @Override
    protected boolean handleInput(Event event) throws IOException {

        KeyStroke key = screen.readInput();

        createEvent(key, event);

        if (event.eventClose())
            screen.close();

        if (event.eventExit())
            return false;

        return true;
    }


}
