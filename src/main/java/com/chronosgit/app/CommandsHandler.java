package com.chronosgit.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jline.utils.AttributedStringBuilder;

import com.chronosgit.terminal.*;

class CommandsHandler {
    @FunctionalInterface
    interface Command {
        void run(String[] args);
    }

    static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("help", args -> HelpMessagesRenderer.renderHelpMessage());
        commands.put("exit", args -> CommandsHandler.gracefulExit());

        commands.put("settings help", args -> HelpMessagesRenderer.renderSettingsHelpMessage());

        commands.put("music help", args -> HelpMessagesRenderer.renderMusicHelpMessage());

        commands.put("tasks help", args -> HelpMessagesRenderer.renderTasksHelpMessage());
    }

    static void handleCommand(String[] tokens) {
        for (int i = tokens.length; i > 0; i--) {
            String commandKey = String.join(" ", Arrays.copyOfRange(tokens, 0, i));

            Command command = commands.get(commandKey);

            if (command != null) {
                String[] remainingArgs = Arrays.copyOfRange(tokens, i, tokens.length);

                command.run(remainingArgs);

                return;
            }
        }

        CommandsHandler.renderWrongCommandWarning();
    }

    static void renderWrongCommandWarning() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nSuch a command doesn't exist.\n");
        sb.append("Type ");
        sb.append("help", StylePreset.COMMAND);
        sb.append(" to see available commands.\n\n");

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }

    static void gracefulExit() {
        System.out.println("\nHave a great day!\n");

        System.exit(0);
    }
}
