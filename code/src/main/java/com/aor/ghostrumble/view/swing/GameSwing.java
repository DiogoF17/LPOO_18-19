package com.aor.ghostrumble.view.swing;

import com.aor.ghostrumble.Game;
import com.aor.ghostrumble.controller.event.*;
import com.aor.ghostrumble.controller.event.EventQueue;
import com.aor.ghostrumble.view.DrawingMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class GameSwing extends Game implements KeyListener {

    private final static int TILE_SIZE = 24;
    private final static int BORDER_OFFSET = 16;

    private JFrame frame;
    private GameComponent panel;

    public GameSwing() {
        this(60, 40);
    }

    public GameSwing(int width, int height) {

        frame = new JFrame("Ghost Rumble (GR)");
        frame.setLayout(new GridLayout());
        frame.setLocation(50,50);
        frame.setSize(width * TILE_SIZE + BORDER_OFFSET, height * TILE_SIZE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new GameComponent(width * TILE_SIZE + BORDER_OFFSET, height * TILE_SIZE);
        panel.setBackground(Color.DARK_GRAY);

        init(width, height);
        panel.setHouse(house);

        frame.add(panel);
        frame.setVisible(true);

        frame.addKeyListener(this);
    }


    public final static int getTileSize() { return TILE_SIZE; }


    public void setFrame(JFrame frame) { this.frame = frame; }

    @Override
    protected DrawingMethod createDrawingMethod()
    {
        return new DrawSwing(panel);
    }

    @Override
    protected boolean handleInput(EventQueue eventQueue) throws IOException {

        if (eventQueue.exit())
            return false;

        if (eventQueue.close()) {
            frame.setVisible(false);
            frame.dispose();
            eventQueue.setExit(true);
        }


        try {
            Thread.sleep(1);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            case VK_W:
                this.getEventQueue().raiseEvent(new EventPlayerUp());
                break;

            case VK_A:
                this.getEventQueue().raiseEvent(new EventPlayerLeft());
                break;

            case VK_S:
                this.getEventQueue().raiseEvent(new EventPlayerDown());
                break;

            case VK_D:
                this.getEventQueue().raiseEvent(new EventPlayerRight());
                break;

            case VK_UP:
                this.getEventQueue().raiseEvent(new EventBulletUp());
                break;

            case VK_DOWN:
                this.getEventQueue().raiseEvent(new EventBulletDown());
                break;

            case VK_LEFT:
                this.getEventQueue().raiseEvent(new EventBulletLeft());
                break;

            case VK_RIGHT:
                this.getEventQueue().raiseEvent(new EventBulletRight());
                break;

            case VK_ESCAPE:
                this.getEventQueue().setClose(true);
                break;

            default:
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
