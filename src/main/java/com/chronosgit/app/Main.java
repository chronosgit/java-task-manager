package com.chronosgit.app;

import com.chronosgit.terminal.JLine;

public class Main {
    public static void main(String[] args) {
        try {
            // Init
            AppState appState = new AppState();
            InputManager ih = new InputManager();

            // Managing redrawing
            AppState.State renderedState = null;
            boolean needsRedraw = false;

            JLine.clearScreen();

            while (true) {
                if (renderedState != appState.getState()) {
                    renderedState = appState.getState();

                    needsRedraw = true;
                }

                switch (renderedState) {
                    case STARTUP:
                        if (needsRedraw) {
                            StartupMessageRenderer.renderStartupMessage();

                            needsRedraw = false;
                        }

                        String input = ih.promptUserInput("prompt> ");

                        if (input != null && input.trim().length() > 0) {
                            ih.manageInput(input);
                        }

                        break;
                    default:
                        break;
                }
            }
        } catch (RuntimeException e) {
            System.err.println("\nSomething went wrong.");
            System.err.println("Closing the app.\n");

            System.exit(1);
        }
    }
}