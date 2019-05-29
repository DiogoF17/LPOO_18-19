package com.aor.ghostrumble.play;

import com.aor.ghostrumble.State;
import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.view.ViewGame;

public class GameState implements State {

    private HauntedHouse house;
    private Updater updater;
    private ViewGame view;
}
