package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.ViewAbstractFactory;
import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.play.GameState;

public class MainMenuState extends MenuState {

    private final static String TITLE = "Ghost Rumble (GR)";
    private final static String FIRST = "PLAY";
    private final static String SECOND = "EXIT";

    private boolean end;

    public MainMenuState(ViewAbstractFactory factory) {
        super(factory, new MenuModel(TITLE, FIRST, SECOND));
        this.end = false;
    }

    @Override
    public boolean keepGoing() {
        return !end;
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
                factory.close();
                end = true;
            }

        }

    }
}
