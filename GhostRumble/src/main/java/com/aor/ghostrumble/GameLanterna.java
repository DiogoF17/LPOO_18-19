package com.aor.ghostrumble;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GameLanterna extends Game {

    private Screen screen;

    public GameLanterna() throws IOException {
        this(80, 24);
    }

    public GameLanterna(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();

        init(width, height);
    }

    @Override
    protected DrawingMethod createDrawingMethod() { return new DrawLanterna(screen); }

    @Override
    protected boolean handleInput() throws IOException {

        KeyStroke key = this.screen.readInput();

        if (key.getKeyType() == KeyType.Escape)
            screen.close();

        if(key.getKeyType() == KeyType.EOF)
            return false;
/*
               this.processKey(key);
*/
        return true;
    }

    /*
        private void processKey(KeyStroke key) throws IOException {
            arena.processKey(key);
        }
    */
}
