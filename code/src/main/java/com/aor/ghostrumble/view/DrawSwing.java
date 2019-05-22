package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.Element;
import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class DrawSwing implements DrawingMethod {

    private JPanel gamePanel;

    private ImageComponent backgroundSprite;
    private ImageComponent playerSprite;
    private ImageComponent bulletRightSprite;
    private ImageComponent bulletUpSprite;
    private ImageComponent bulletDownSprite;
    private ImageComponent bulletLeftSprite;
    private ImageComponent zombieSprite;
    private ImageComponent ghostSprite;
    private ImageComponent poltergeistSprite;
    private ImageComponent wallSprite;


    public DrawSwing(JPanel gamePanel) {
        this.gamePanel = gamePanel;


        try {
            this.playerSprite = loadImage("guy");
            this.bulletUpSprite = loadImage("bulletUp");
            this.bulletLeftSprite = loadImage("bulletLeft");
            this.bulletDownSprite = loadImage("bulletDown");
            this.bulletRightSprite = loadImage("bulletRight");
            this.zombieSprite = loadImage("zombie");
            this.ghostSprite = loadImage("ghost");
            this.backgroundSprite = loadImage("floor");
            this.wallSprite = loadImage("wall");
/*
            this.poltergeistSprite = loadImage();
*/
        } catch(IOException e) {
            e.printStackTrace();
        }

    }


    private ImageComponent loadImage(String imageName) throws IOException {
        return new ImageComponent(ImageIO.read(getClass().getResource("/" + imageName + ".png")));
    }


    private void drawImage(ImageComponent image, int x, int y) {
        // gamePanel.getGraphics().drawImage(image, x * TILE_SIZE, y * TILE_SIZE, null);
        image.setPosition(x, y);
        gamePanel.add(image);
    }


    @Override
    public void drawAll(HauntedHouse house) throws IOException {
        drawHouse(house.getWidth(), house.getHeight());
        drawWalls(house.getWalls());
        drawPlayer(house.getPlayer());
        gamePanel.revalidate();
    }


    private void drawHouse(int width, int height) {
        for (int i = 5; i < height; i++) {
            for (int j = 0; j < width; j++) {
                drawImage(backgroundSprite, j, i);
            }
        }
    }

    private void drawWalls(List<Element> walls) {
        for (Element wall : walls) {
            drawImage(wallSprite, wall.getPosition().getX(), wall.getPosition().getY());
        }
    }

    private void drawPlayer(Player player) {
        drawImage(playerSprite, player.getPosition().getX(), player.getPosition().getY());
    }
}