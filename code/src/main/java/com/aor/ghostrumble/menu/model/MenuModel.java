package com.aor.ghostrumble.menu.model;

public class MenuModel {

    private boolean play;

    private String title;
    private String first;
    private String second;

    public MenuModel(String title, String first, String second) {
        this.play = true;
        this.title = title;
        this.first = first;
        this.second = second;
    }

    public void changeOption() {
        this.play = !this.play;
    }

    public boolean willPlay() {
        return play;
    }

    public String getTitle() { return title; }

    public String getFirst() { return first; }

    public String getSecond() { return second; }

}
