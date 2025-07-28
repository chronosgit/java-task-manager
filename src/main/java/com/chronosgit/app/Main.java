package com.chronosgit.app;

import com.chronosgit.terminal.MainMenu;;

public class Main {
    public static void main(String[] args) {
        try {
            MainMenu mainMenu = new MainMenu();

            mainMenu.printMainMenu();
        } catch (RuntimeException e) {
            System.err.println("Something went wrong: " + e.getMessage());

            System.exit(1);
        }
    }
}