package com.aor.ghostrumble.menu.view.lanterna;

import com.aor.ghostrumble.menu.event.EventChangeOption;
import com.aor.ghostrumble.menu.event.EventConfirmOption;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.play.controller.event.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class ViewMenuLanterna extends ViewMenu {

    Screen screen;
    int width;
    int height;

    public ViewMenuLanterna(Screen screen, int width, int height) {
        this.screen = screen;
        this.width = width;
        this.height = height;
    }

    private void createEvent(KeyStroke key) {

        switch(key.getKeyType()) {

            case ArrowUp: case ArrowDown:
                event = new EventChangeOption();


            case Enter:
                event = new EventConfirmOption();
        }
    }


    @Override
    public void handleInput() {
        try {
            KeyStroke key = screen.readInput();
            createEvent(key);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawAll(MenuModel model) {

        TextGraphics graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#CEC20F"));
        graphics.putString(new TerminalPosition(width / 3, height / 3), "Ghost Rumble (GR)");

        if(model.willPlay()) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#009999"));
        }

        graphics.putString(new TerminalPosition(width / 3, (int) (1.5 * height) / 3), "Play");


        if (model.willPlay()) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#CEC20F"));
        }
        else {
            graphics.setForegroundColor(TextColor.Factory.fromString("#009999"));
        }

        graphics.putString(new TerminalPosition(width / 3, 2 * height / 3), "Exit");
    }

}
