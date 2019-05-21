package com.aor.ghostrumble.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private int x;
    private int y;

    public ImagePanel(String imageName) throws IOException {
        this.image = ImageIO.read(getClass().getResource("/" + imageName));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}
