package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.ViewAbstractFactory;
import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.play.GameState;

public class GameOverState extends MenuState {

    private final static String TITLE = "Your score was ";
    private final static String FIRST = "PLAY AGAIN";
    private final static String SECOND = "MAIN MENU";

    public GameOverState(ViewAbstractFactory factory, int score) {
        super(factory, new MenuModel(TITLE + score, FIRST, SECOND));
    }

    public void update() {

        MenuEvent event = view.getEvent();
        view.setEvent(new NullEvent());

        if (event.process(model)) {

            view.prepareStateChange();

            if (model.willPlay()) {
                observer.changeState(new GameState(factory));
            }
            else {
                observer.changeState(new MainMenuState(factory));
            }

        }

    }

}
