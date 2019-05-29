package com.aor.ghostrumble;

import com.aor.ghostrumble.play.view.lanterna.ViewGameLanterna;
import com.aor.ghostrumble.play.view.swing.ViewGameSwing;

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

            Game game;

            if (getGameType() == 0) {
                System.out.println("You chose - RETRO");
                game = new Game(new ViewLanternaFactory());
            }
            else {
                System.out.println("You chose - MODERN");
                game = new Game(new ViewSwingFactory());
            }

            game.run();


    }
}
