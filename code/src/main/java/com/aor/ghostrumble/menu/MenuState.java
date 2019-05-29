package com.aor.ghostrumble.menu;

import com.aor.ghostrumble.State;
import com.aor.ghostrumble.ViewAbstractFactory;
import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;
import com.aor.ghostrumble.play.GameState;

public class MenuState extends State {

    private boolean end;
    private MenuModel model;
    private ViewMenu view;

    public MenuState(ViewAbstractFactory factory) {
        super(factory);
        this.model = new MenuModel();
        this.view = factory.createMenuView();
        this.end = false;
    }

    @Override
    public boolean keepGoing() {
        return !end;
    }

    public void handleInput() { view.handleInput(); }

    public void draw() {
        view.drawAll(model);
    }

    public void update() {

        MenuEvent event = view.getEvent();
        view.setEvent(new NullEvent());

        if (event.process(model)) {
            if (model.willPlay()) {
                observer.changeState(new GameState(factory));
            }
            else {
                end = true;
            }
        }

    }

}
