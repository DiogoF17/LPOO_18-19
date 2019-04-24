package com.aor.ghostrumble;

import com.aor.ghostrumble.view.GameLanterna;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            Game game = new GameLanterna();
            game.run();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
