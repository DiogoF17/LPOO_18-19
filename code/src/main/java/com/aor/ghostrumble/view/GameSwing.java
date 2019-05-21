package com.aor.ghostrumble.view;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.event.EventQueue;

import javax.swing.*;
import java.io.IOException;

public class GameSwing extends Game {

    private JPanel gamePanel;

    public GameSwing() {

    }

    @Override
    protected DrawingMethod createDrawingMethod() { return new DrawSwing(gamePanel); }


    @Override
    protected boolean handleInput(EventQueue eventQueue) throws IOException {
        return true;
    }


}
