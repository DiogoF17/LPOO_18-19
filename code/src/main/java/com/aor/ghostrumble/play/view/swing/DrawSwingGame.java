package com.aor.ghostrumble.play.view.swing;

import com.aor.ghostrumble.play.model.*;

public class DrawSwingGame {

    private GameComponent gamePanel;

    public DrawSwingGame(GameComponent gamePanel) {

        this.gamePanel = gamePanel;

    }


    public void drawAll(HauntedHouse house) {

        gamePanel.setHouse(house);
        gamePanel.repaint();

    }

}