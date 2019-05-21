package com.aor.ghostrumble.view;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.event.EventQueue;

import javax.swing.*;
import java.io.IOException;

public class GameSwing extends Game {

    JFrame frame;
    JPanel panel;

    public GameSwing() {
        this(100, 60);
    }

    public GameSwing(int width, int height) {
        frame = new JFrame("Ghost Rumble (GR)");
        frame.setVisible(true);
        frame.setLocation(50,50);
        frame.setSize(width * 16, height * 16);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);

        init(width, height);
    }

    @Override
    protected DrawingMethod createDrawingMethod()
    {
        return new DrawSwing(frame);
    }

    @Override
    protected boolean handleInput(EventQueue eventQueue) throws IOException {
        return true;
    }

}
