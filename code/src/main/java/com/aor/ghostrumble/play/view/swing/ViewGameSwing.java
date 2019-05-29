package com.aor.ghostrumble.play.view.swing;

import com.aor.ghostrumble.play.controller.event.*;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.view.ViewGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class ViewGameSwing extends ViewGame implements KeyListener {

    private DrawSwingGame drawer;

    public ViewGameSwing(JFrame frame, int width, int height, int tileSize, int borderOffset) {

        int panWidth = width * tileSize + borderOffset;
        int panHeight = height * tileSize;
        GameComponent panel = new GameComponent(panWidth, panHeight, tileSize);
        panel.setPreferredSize(new Dimension(panWidth, panHeight));
        panel.setBackground(Color.DARK_GRAY);

        frame.add(panel);
        frame.addKeyListener(this);

        frame.revalidate();

        drawer = new DrawSwingGame(panel);
    }

    @Override
    public void drawAll(HauntedHouse house) {
        drawer.drawAll(house);
    }


    @Override
    public void handleInput() {

/*
        if (queue.exit())
            return false;

        if (queue.close()) {
            queue.setExit(true);
        }
*/

/*
        try {
            Thread.sleep(1);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
*/

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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
