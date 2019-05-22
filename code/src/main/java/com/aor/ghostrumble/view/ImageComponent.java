package com.aor.ghostrumble.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        this.setBounds(
            this.x + getRootPane().getInsets().left,
            this.y + getRootPane().getInsets().top,
            TILE_SIZE,
            TILE_SIZE
        );
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public ImageComponent clone() {
        return new ImageComponent(image);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
