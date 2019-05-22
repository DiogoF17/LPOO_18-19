package com.aor.ghostrumble.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.aor.ghostrumble.view.GameSwing.TILE_SIZE;

public class ImageComponent extends JComponent {

    private BufferedImage image;
    private int x;
    private int y;

    public ImageComponent(BufferedImage image) {
        this.image = image;
    }

    public void setPosition(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}
