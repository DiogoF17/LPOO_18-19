package com.aor.ghostrumble.view;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.Event;
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

    @Override
    protected DrawingMethod createDrawingMethod() {
        return new DrawLanterna(screen);
    }

    private void createEvent(KeyStroke key, Event event) {

        switch(key.getKeyType()) {

            case Character:

                switch(key.getCharacter()) {

                    case 'w':
                        event.setType(Event.TYPE.PLAYER_UP);
                        break;

                    case 'a':
                        event.setType(Event.TYPE.PLAYER_LEFT);
                        break;

                    case 's':
                        event.setType(Event.TYPE.PLAYER_DOWN);
                        break;

                    case 'd':
                        event.setType(Event.TYPE.PLAYER_RIGHT);
                        break;

                    default:
                        event.setType(Event.TYPE.NO_EVENT);
                        break;

                }
                break;

            case ArrowUp:
                event.setType(Event.TYPE.BULLET_UP);
                break;

            case ArrowDown:
                event.setType(Event.TYPE.BULLET_DOWN);
                break;

            case ArrowLeft:
                event.setType(Event.TYPE.BULLET_LEFT);
                break;

            case ArrowRight:
                event.setType(Event.TYPE.BULLET_RIGHT);
                break;


            case Escape:
                event.setType(Event.TYPE.CLOSE);
                break;

            case EOF:
                event.setType(Event.TYPE.EXIT);
                break;

            default:
                event.setType(Event.TYPE.NO_EVENT);
                break;

        }
    }

    @Override
    protected boolean handleInput(Event event) throws IOException {

        KeyStroke key = this.screen.readInput();

        createEvent(key, event);

        if (event.getType() == Event.TYPE.CLOSE)
            screen.close();

        if (event.getType() == Event.TYPE.EXIT)
            return false;

        return true;
    }


}
