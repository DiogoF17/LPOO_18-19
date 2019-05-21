package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.model.Player;

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
            this.playerSprite = loadImage("guy.png");
            this.bulletUpSprite = loadImage("bulletUp.png");
            this.bulletLeftSprite = loadImage("bulletLeft.png");
            this.bulletDownSprite = loadImage("bulletDown.png");
            this.bulletRightSprite = loadImage("bulletRight.png");
            this.zombieSprite = loadImage("zombie.png");
            this.ghostSprite = loadImage("ghost.png");
/*
            this.backgroundSprite = loadImage("bulletUp.png");
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
        // drawHouse(house.getWidth(), house.getHeight());
        drawPlayer(house.getPlayer());
    }


    private void drawHouse(int width, int height) {
        for (int i = 5; i < height; i++) {
            for (int j = 0; j < width; j++) {
                drawImage(backgroundSprite, j, i);
            }
        }
    }

    private void drawPlayer(Player player) {
        drawImage(playerSprite, player.getPosition().getX(), player.getPosition().getY());
    }
}