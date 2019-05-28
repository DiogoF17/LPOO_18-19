package com.aor.ghostrumble.view.swing;

import com.aor.ghostrumble.controller.event.*;
import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.view.ViewGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class ViewGameSwing extends ViewGame implements KeyListener {

    private final static int TILE_SIZE = 24;
    private final static int BORDER_OFFSET = 16;

    private JFrame frame;
    private DrawSwingGame drawer;

    public ViewGameSwing() {
        this(60, 40);
    }

    public ViewGameSwing(int width, int height) {

        frame = new JFrame("Ghost Rumble (GR)");
        frame.setLayout(new GridLayout());
        frame.setLocation(50,50);
        frame.setSize(width * TILE_SIZE + BORDER_OFFSET, height * TILE_SIZE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameComponent panel = new GameComponent(width * TILE_SIZE + BORDER_OFFSET, height * TILE_SIZE, TILE_SIZE);
        panel.setBackground(Color.DARK_GRAY);

        // init(width, height);
        // panel.setHouse(house);

        frame.add(panel);
        frame.setVisible(true);
        frame.addKeyListener(this);

        drawer = new DrawSwingGame(panel);
    }

    public final static int getTileSize() { return TILE_SIZE; }


    @Override
    public void drawAll(HauntedHouse house) {
        drawer.drawAll(house);
    }


    @Override
    public boolean handleInput() {
        if (queue.exit())
            return false;

        if (queue.close()) {
            frame.setVisible(false);
            frame.dispose();
            queue.setExit(true);
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
                queue.raiseEvent(new EventPlayerUp());
                break;

            case VK_A:
                queue.raiseEvent(new EventPlayerLeft());
                break;

            case VK_S:
                queue.raiseEvent(new EventPlayerDown());
                break;

            case VK_D:
                queue.raiseEvent(new EventPlayerRight());
                break;

            case VK_UP:
                queue.raiseEvent(new EventBulletUp());
                break;

            case VK_DOWN:
                queue.raiseEvent(new EventBulletDown());
                break;

            case VK_LEFT:
                queue.raiseEvent(new EventBulletLeft());
                break;

            case VK_RIGHT:
                queue.raiseEvent(new EventBulletRight());
                break;

            case VK_ESCAPE:
                queue.setClose(true);
                break;

            default:
                break;

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
