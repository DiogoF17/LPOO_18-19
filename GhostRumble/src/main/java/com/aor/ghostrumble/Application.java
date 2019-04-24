package com.aor.ghostrumble;

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
