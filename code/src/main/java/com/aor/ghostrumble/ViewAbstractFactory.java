package com.aor.ghostrumble;

import com.aor.ghostrumble.gameOver.view.ViewGameOver;
import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.play.view.ViewGame;

public interface ViewAbstractFactory {

    int getScreenWidth();
    int getScreenHeight();
    ViewMenu createMenuView();
    ViewGame createGameView();
    ViewGameOver createGameOverView();

}
