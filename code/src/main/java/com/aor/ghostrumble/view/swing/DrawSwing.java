package com.aor.ghostrumble.view.swing;

import com.aor.ghostrumble.model.*;
import com.aor.ghostrumble.view.DrawingMethod;

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