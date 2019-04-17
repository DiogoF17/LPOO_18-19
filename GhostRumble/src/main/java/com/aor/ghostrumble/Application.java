package com.aor.ghostrumble;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            new Game(100, 100).run();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
