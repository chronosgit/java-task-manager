package com.chronosgit.app;

import org.jline.utils.*;

import com.chronosgit.terminal.*;

public class InputManager {
    public String promptUserInput(String prompt) {
        AttributedString input = new AttributedString(
                prompt,
                AttributedStyle.DEFAULT.foreground(StylePreset.COLOR_MAIN));

        String userInput = JLine.reader.readLine(input.toAnsi());

        return userInput;
    }

    void manageInput(String userInput) {
        String[] tokens = userInput.trim().split("\\s+");

        if (tokens.length == 0) {
            return;
        }

        CommandsManager.manageCommand(tokens);
    }
}
