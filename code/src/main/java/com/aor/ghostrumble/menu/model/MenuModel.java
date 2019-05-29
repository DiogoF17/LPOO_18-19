package com.aor.ghostrumble.menu.model;

public class MenuModel {

    private boolean play;

    public MenuModel() {
        this.play = true;
    }

    public void changeOption() {
        this.play = !this.play;
    }

    public boolean willPlay() {
        return play;
    }
}
