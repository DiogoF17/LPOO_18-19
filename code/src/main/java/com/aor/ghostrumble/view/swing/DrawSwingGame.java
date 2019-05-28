package com.aor.ghostrumble.view.swing;

import com.aor.ghostrumble.model.*;

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