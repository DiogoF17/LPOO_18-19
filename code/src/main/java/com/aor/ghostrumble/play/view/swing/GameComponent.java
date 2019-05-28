package com.aor.ghostrumble.play.view.swing;

import com.aor.ghostrumble.play.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class GameComponent extends JPanel {

    private HauntedHouse house;
    private int width;
    private int height;
    private int tileSize;
    private BufferedImage image;

    private BufferedImage backgroundSprite;
    private BufferedImage wallSprite;
    private BufferedImage playerSprite;
    private BufferedImage bulletRightSprite;
    private BufferedImage bulletUpSprite;
    private BufferedImage bulletDownSprite;
    private BufferedImage bulletLeftSprite;
    private BufferedImage zombieSprite;
    private BufferedImage ghostSprite;
    private BufferedImage poltergeistSprite;
    private JProgressBar hpBar;

    public GameComponent(int width, int height, int tileSize) {

        this.width = width;
        this.height = height;
        this.tileSize = tileSize;

        try {
            this.backgroundSprite = loadImage("floor");
            this.wallSprite = loadImage("wall");
            this.playerSprite = loadImage("guy");
            this.bulletUpSprite = loadImage("bulletUp");
            this.bulletLeftSprite = loadImage("bulletLeft");
            this.bulletDownSprite = loadImage("bulletDown");
            this.bulletRightSprite = loadImage("bulletRight");
            this.zombieSprite = loadImage("zombie");
            this.ghostSprite = loadImage("ghost");
            this.poltergeistSprite = loadImage("poltergeist");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.hpBar = new JProgressBar();
        hpBar.setVisible(true);
        hpBar.setStringPainted(true);
        this.add(hpBar);

    }

    protected void setHouse(HauntedHouse house) {
        this.house = house;
    }

    private BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(getClass().getResource("/" + imageName + ".png"));
    }

    private void drawHouse(Graphics g, HauntedHouse house) {

        image = backgroundSprite;

        for (int i = 6; i < house.getHeight() - 1; i++) {

            for (int j = 1; j < house.getWidth() - 1; j++) {

                g.drawImage(image, j * tileSize, i * tileSize, null);

            }

        }

    }

    private void drawElement(Graphics g, Element element) {
        g.drawImage(image, element.getPosition().getX() * tileSize,
                element.getPosition().getY() * tileSize, null);
    }

    private void drawWalls(Graphics g, List<Element> walls) {

        image = wallSprite;

        for (Element wall : walls) {

            drawElement(g, wall);

        }

    }

    private void drawEnemies(Graphics g, List<Enemy> enemies) {

        g.setColor(Color.ORANGE);
        g.setFont(new Font("Consolas", Font.BOLD, 20));

        g.drawString("Enemies left: " + enemies.size(), 4 * tileSize,
                (height - 3 * tileSize));

        for (Enemy enemy : enemies) {

            if (enemy instanceof Zombie) {
                image = zombieSprite;
            }
            else if (enemy instanceof Ghost) {
                image = ghostSprite;
            }
            else if (enemy instanceof Poltergeist) {
                image = poltergeistSprite;
            }

            drawElement(g, enemy);

        }

    }

    private void drawBullets(Graphics g, List<Bullet> bullets) {

        for (Bullet bullet : bullets) {

            if (bullet instanceof VerticalBullet) {

                if (bullet.getDelta() < 0) {
                    image = bulletUpSprite;
                }
                else {
                    image = bulletDownSprite;
                }

            }
            else if (bullet instanceof  HorizontalBullet){

                if (bullet.getDelta() < 0) {
                    image = bulletLeftSprite;
                }
                else {
                    image = bulletRightSprite;
                }

            }

            drawElement(g, bullet);

        }

    }

    private void drawHPBar(Graphics g, Player player) {

        g.setFont(new Font("Consolas", Font.BOLD, 26));
        g.drawString("HP: ", 2 * tileSize, 3 * tileSize - 4);

        hpBar.setBounds(
                hpBar.getRootPane().getInsets().left + 4 * tileSize,
                hpBar.getRootPane().getInsets().top + 2 * tileSize,
                hpBar.getWidth(),
                hpBar.getHeight()
        );
        hpBar.setString(player.getCurrentHealth() + "/" + player.getMaxHealth());
        hpBar.setValue(player.getCurrentHealth());
        hpBar.setMaximum(player.getMaxHealth());
        hpBar.setBackground(Color.BLACK);
        if (player.getCurrentHealth() > 5) {
            hpBar.setForeground(Color.GREEN);
        }
        else {
            hpBar.setForeground(Color.RED);
        }

    }

    private void drawPlayer(Graphics g, Player player) {
        image = playerSprite;
        drawElement(g, player);
        drawHPBar(g, player);
    }

    private void drawScore(Graphics g, int score) {

        g.setColor(Color.ORANGE);

        g.setFont(new Font("Consolas", Font.BOLD, 25));

        g.drawString("Score: " + score,  (width - 10 * tileSize),
                3 * tileSize);
    }

    private void drawAll(Graphics g) {

        drawHouse(g, house);
        drawWalls(g, house.getWalls());
        drawEnemies(g, house.getEnemies());
        drawBullets(g, house.getBullets());
        drawPlayer(g, house.getPlayer());
        drawScore(g, house.getScore());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(house != null)
            drawAll(g);
    }

}
