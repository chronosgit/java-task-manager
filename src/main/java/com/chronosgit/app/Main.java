package com.chronosgit.app;

import com.chronosgit.terminal.JLine;

public class Main {
    public static void main(String[] args) {
        try {
            // Init
            AppState appState = new AppState();
            InputHandler ih = new InputHandler();

            // Managing redrawing
            AppState.State renderedState = null;
            boolean needsRedraw = false;

            // Clear screen once
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

                        ih.handleInput(ih.promptUserInput());

                        break;
                    default:
                }
            }
        } catch (RuntimeException e) {
            System.err.println("\nSomething went wrong.");
            System.err.println("Closing the app.\n");

            System.exit(1);
        }
    }
}