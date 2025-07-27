package com.chronosgit.terminal;

import com.chronosgit.terminal.enums.*;

public class TerminalAnsiManager {
    static void setTerminalColor(Color c) {
        System.out.print(c.getAnsiCode());
    }

    static void setTerminalTextStyle(TextStyle ts) {
        System.out.print(ts.getAnsiCode());
    }

    static void printText(String t, Color c, TextStyle ts) {
        TerminalAnsiManager.setTerminalColor(c);
        TerminalAnsiManager.setTerminalTextStyle(ts);

        System.out.print(t);

        TerminalAnsiManager.resetTerminalAnsi();
    }

    static void printText(String t, TextStyle ts) {
        TerminalAnsiManager.setTerminalTextStyle(ts);

        System.out.print(t);

        TerminalAnsiManager.resetTerminalAnsi();
    }

    static void printText(String t, Color c) {
        TerminalAnsiManager.setTerminalColor(c);

        System.out.print(t);

        TerminalAnsiManager.resetTerminalAnsi();
    }

    static void resetTerminalColor() {
        System.out.print("\033[39m");
    }

    static void resetTerminalTextStyle() {
        System.out.print("\033[22;23;24;27;29m");
    }

    // Use this resetter
    // for cleaner "pipeline"
    static void resetTerminalAnsi() {
        System.out.print("\033[0m");
    }
}
