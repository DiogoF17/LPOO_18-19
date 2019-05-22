package com.aor.ghostrumble.view;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.event.EventQueue;

import javax.swing.*;
import java.io.IOException;

public class GameSwing extends Game {

    protected final static int TILE_SIZE = 24;
    private final static int BORDER_OFFSET = 16;

    JFrame frame;
    JPanel panel;

    public GameSwing() {
        this(60, 40);
    }

    public GameSwing(int width, int height) {
        frame = new JFrame("Ghost Rumble (GR)");
        frame.setLocation(50,50);
        frame.setSize(width * TILE_SIZE + BORDER_OFFSET, height * TILE_SIZE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        // frame.pack();

        init(width, height);
    }

    @Override
    protected DrawingMethod createDrawingMethod()
    {
        return new DrawSwing(panel);
    }

    @Override
    protected boolean handleInput(EventQueue eventQueue) throws IOException {
        return true;
    }
}
