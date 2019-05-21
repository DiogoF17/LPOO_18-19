package com.aor.ghostrumble;

import com.aor.ghostrumble.view.GameLanterna;
import com.aor.ghostrumble.view.GameSwing;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            // Game game = new GameLanterna();
            Game game = new GameSwing();
            game.run();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
