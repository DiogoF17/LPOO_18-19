package com.aor.ghostrumble;

import com.aor.ghostrumble.view.lanterna.GameLanterna;
import com.aor.ghostrumble.view.swing.GameSwing;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    private static int getGameType() {

        Scanner scan = new Scanner(System.in);
        int i;

        do {

            System.out.println("Choose your version: ");
            System.out.println();
            System.out.println("0: Retro");
            System.out.println("1: Modern");
            System.out.print("Option: ");
            i = scan.nextInt();
            System.out.println();

            if (i < 0 || i > 1) {
                System.out.println("Invalid option! Choose again.");
                System.out.println();
            }

        } while (i < 0 || i > 1);

        return i;
    }

    public static void main(String[] args) {

        try {
            Game game;

            if (getGameType() == 0)
                game = new GameLanterna();
            else {
                game = new GameSwing();
            }

            game.run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
