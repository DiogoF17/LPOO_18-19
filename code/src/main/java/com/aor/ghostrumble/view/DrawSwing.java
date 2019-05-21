package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.HauntedHouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.aor.ghostrumble.view.GameSwing.TILE_SIZE;

public class DrawSwing implements DrawingMethod {

    private JPanel gamePanel;

    private BufferedImage backgroundSprite;
    private BufferedImage playerSprite;
    private BufferedImage bulletRightSprite;
    private BufferedImage bulletUpSprite;
    private BufferedImage bulletDownSprite;
    private BufferedImage bulletLeftSprite;
    private BufferedImage zombieSprite;
    private BufferedImage ghostSprite;
    private BufferedImage poltergeistSprite;
    private BufferedImage wallSprite;


    public DrawSwing(JPanel gamePanel) {
        this.gamePanel = gamePanel;


        try {
            this.backgroundSprite = loadImage("guy.png");
/*
            this.playerSprite = loadImage();
            this.bulletRightSprite = loadImage();
            this.bulletDownSprite = loadImage();
            this.bulletLeftSprite = loadImage();
            this.bulletUpSprite = loadImage();
            this.zombieSprite = loadImage();
            this.ghostSprite = loadImage();
            this.poltergeistSprite = loadImage();
            this.wallSprite = loadImage();
*/
        } catch(IOException e) {
            e.printStackTrace();
        }

    }


    private BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(getClass().getResource("/" + imageName));
    }


    private void drawImage(BufferedImage image, int x, int y) {
        gamePanel.getGraphics().drawImage(image, x * TILE_SIZE, y * TILE_SIZE, null);
    }


    @Override
    public void drawAll(HauntedHouse house) throws IOException {
        drawHouse(29, 19);
    }


    private void drawHouse(int width, int height) {
        drawImage(backgroundSprite, width, height);
    }
}