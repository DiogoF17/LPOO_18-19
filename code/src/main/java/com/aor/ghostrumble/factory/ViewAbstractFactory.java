package com.aor.ghostrumble.factory;

import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.play.view.ViewGame;

public interface ViewAbstractFactory {

    int getScreenWidth();
    int getScreenHeight();
    ViewMenu createMenuView();
    ViewGame createGameView();

    void close();

}
