package com.chronosgit.app;

public class Main {
    public static void main(String[] args) {
        try {
            // Init
            AppState appState = new AppState();
            InputHandler ih = new InputHandler();

            // Managing redrawing
            AppState.State renderedState = null;
            boolean needsRedraw = false;

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
            System.err.println();

            if (e.getMessage() != null) {
                System.err.println("Something went wrong.");
                System.err.println(e.getMessage());
            }

            System.err.println("Closing the app.");

            System.exit(1);
        }
    }
}