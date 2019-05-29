package com.aor.ghostrumble.gameOver.model;

public class GameOverModel {

    private boolean play;
    private int score;

    public GameOverModel(int score) {
        this.play = true;
        this.score = score;
    }

    public void changeOption() {
        this.play = !this.play;
    }

    public int getScore() { return this.score; }

}
