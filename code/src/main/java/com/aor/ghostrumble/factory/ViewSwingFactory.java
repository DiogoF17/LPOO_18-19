package com.aor.ghostrumble.factory;

import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.menu.view.swing.ViewMenuSwing;
import com.aor.ghostrumble.play.view.ViewGame;
import com.aor.ghostrumble.play.view.swing.ViewGameSwing;

import javax.swing.*;
import java.awt.*;

public class ViewSwingFactory implements ViewAbstractFactory {

    private JFrame frame;
    private final static int SCREEN_WIDTH = 60;
    private final static int SCREEN_HEIGHT = 40;
    private final static int TILE_SIZE = 24;
    private final static int BORDER_OFFSET = 16;

    public ViewSwingFactory() {

        frame = new JFrame("Ghost Rumble (GR)");

        frame.setLayout(new GridLayout());
        frame.setLocation(50,50);
        frame.setSize(SCREEN_WIDTH * TILE_SIZE + BORDER_OFFSET, SCREEN_HEIGHT * TILE_SIZE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public int getScreenWidth() { return SCREEN_WIDTH; }
    public int getScreenHeight() { return SCREEN_HEIGHT; }

    public ViewMenu createMenuView() {
        return new ViewMenuSwing(frame, SCREEN_WIDTH, SCREEN_HEIGHT, TILE_SIZE, BORDER_OFFSET);
    }

    public ViewGame createGameView() {
        return new ViewGameSwing(frame, SCREEN_WIDTH, SCREEN_HEIGHT, TILE_SIZE, BORDER_OFFSET);
    }

    public void close() {

        frame.removeAll();
        frame.dispose();

    }

}
