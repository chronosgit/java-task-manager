package com.chronosgit.app;

import org.jline.utils.*;

import com.chronosgit.terminal.*;

class InputHandler {
    String promptUserInput() {
        AttributedString prompt = new AttributedString(
                "prompt > ",
                AttributedStyle.DEFAULT.foreground(StylePreset.COLOR_MAIN));

        String userInput = JLine.reader.readLine(prompt.toAnsi());

        return userInput;
    }

    void handleInput(String userInput) {
        String[] tokens = userInput.trim().split(" ");

        if (tokens.length == 0) {
            return;
        }

        CommandsHandler.handleCommand(tokens);
    }
}
