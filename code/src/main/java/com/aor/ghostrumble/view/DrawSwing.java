package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.*;

public class DrawSwing implements DrawingMethod {

    private GameComponent gamePanel;

    public DrawSwing(GameComponent gamePanel) {

        this.gamePanel = gamePanel;

    }

    @Override
    public void drawAll(HauntedHouse house) {
        gamePanel.setHouse(house);
        gamePanel.repaint();
    }

}