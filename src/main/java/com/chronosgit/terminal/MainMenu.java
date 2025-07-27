package com.chronosgit.terminal;

import com.chronosgit.terminal.enums.Color;
import com.chronosgit.terminal.enums.TextStyle;

public class MainMenu {
    private void printLogo() {
        TerminalAnsiManager.printText("+-------------------------------+\n" + //
                "| T A S K   ✿ ✿   M A N A G E R |\n" + //
                "+-------------------------------+\n" + //
                "", Color.BRIGHT_CYAN, TextStyle.BOLD);
    }

    private void printAppInfo() {
        System.out.print("Welcome to ");

        TerminalAnsiManager.printText("Task Manager", Color.BRIGHT_CYAN);

        System.out.println("!");

        System.out.print("A simple CLI to help you ");

        TerminalAnsiManager.printText("organize tasks efficiently.\n", TextStyle.ITALIC);

        System.out.println("Made by © chronosgit");
    }

    public void printMainMenu() {
        System.out.println();

        printLogo();

        printAppInfo();

        System.out.println();
    }
}
