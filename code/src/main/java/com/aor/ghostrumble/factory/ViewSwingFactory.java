package com.aor.ghostrumble.factory;

import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.menu.view.swing.ViewMenuSwing;
import com.aor.ghostrumble.play.view.ViewGame;
import com.aor.ghostrumble.play.view.swing.ViewGameSwing;

import javax.swing.*;
import java.awt.*;

public class ViewSwingFactory implements ViewAbstractFactory {

    private JFrame frame;
    private int screenWidth;
    private int screenHeight;
    private int tileSize;
    private int borderOffset;

    public ViewSwingFactory() {
        this(60, 40, 24, 16);
    }

    public ViewSwingFactory(int screenWidth, int screenHeight, int tileSize, int borderOffset) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.tileSize = tileSize;
        this.borderOffset = borderOffset;

        frame = new JFrame("Ghost Rumble (GR)");

        frame.setLocation(50,50);
        frame.setSize(screenWidth * tileSize + borderOffset, screenHeight * tileSize);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public int getScreenWidth() { return screenWidth; }
    public int getScreenHeight() { return screenHeight; }
    public int getTileSize() { return tileSize; }
    public int getBorderOffset() { return borderOffset; }
    public JFrame getFrame() { return frame; }

    public void setFrame(JFrame frame) { this.frame = frame; }

    public ViewMenu createMenuView() {
        return new ViewMenuSwing(frame, screenWidth, screenHeight, tileSize, borderOffset);
    }

    public ViewGame createGameView() {
        return new ViewGameSwing(frame, screenWidth, screenHeight, tileSize, borderOffset);
    }

    public void close() {

        frame.removeAll();
        frame.dispose();

    }

}
