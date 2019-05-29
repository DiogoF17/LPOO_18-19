package com.aor.ghostrumble;

import com.aor.ghostrumble.gameOver.view.ViewGameOver;
import com.aor.ghostrumble.gameOver.view.lanterna.ViewGameOverLanterna;
import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.menu.view.lanterna.ViewMenuLanterna;
import com.aor.ghostrumble.play.view.ViewGame;
import com.aor.ghostrumble.play.view.lanterna.ViewGameLanterna;

public class ViewLanternaFactory implements ViewAbstractFactory {

    public final static int SCREEN_WIDTH = 100;
    public final static int SCREEN_HEIGHT = 35;

    public int getScreenWidth() { return SCREEN_WIDTH; }
    public int getScreenHeight() { return SCREEN_HEIGHT; }

    public ViewMenu createMenuView() {
        return new ViewMenuLanterna();
    }

    public ViewGame createGameView() {
        return new ViewGameLanterna();
    }

    public ViewGameOver createGameOverView() {
        return new ViewGameOverLanterna();
    }

}
