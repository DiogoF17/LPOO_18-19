package com.aor.ghostrumble.play;

import com.aor.ghostrumble.State;
import com.aor.ghostrumble.ViewAbstractFactory;
import com.aor.ghostrumble.menu.GameOverState;
import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.view.ViewGame;

public class GameState extends State {

    private HauntedHouse house;
    private Updater updater;
    private ViewGame view;

    public GameState(ViewAbstractFactory factory) {
        super(factory);
        this.house = new HauntedHouse(factory.getScreenWidth(), factory.getScreenHeight() - 5);
        this.view = factory.createGameView();
        this.updater = new Updater();
    }

    public void handleInput() {
        this.view.handleInput();
    }

    public void draw() {
        this.view.drawAll(house);
    }

    public void update() {
        if (!this.updater.update(view.getQueue(), house)) {
            view.prepareStateChange();
            observer.changeState(new GameOverState(factory, house.getScore()));
        }
    }

}
