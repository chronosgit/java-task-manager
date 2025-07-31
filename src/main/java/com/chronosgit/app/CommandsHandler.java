package com.chronosgit.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jline.utils.AttributedStringBuilder;

import com.chronosgit.music.Playback;
import com.chronosgit.music.TracksManager;
import com.chronosgit.settings.SettingsHandler;
import com.chronosgit.terminal.*;

class CommandsHandler {
    @FunctionalInterface
    interface Command {
        void run(String[] args);
    }

    static final Map<String, Command> commands = new HashMap<>();
    static final Map<String, Integer> numOfArgs = new HashMap<>();

    static {
        commands.put("help", args -> HelpMessagesRenderer.renderHelpMessage());
        numOfArgs.put("help", 0);

        commands.put("exit", args -> CommandsHandler.gracefulExit());
        numOfArgs.put("exit", 0);

        commands.put("settings help", args -> HelpMessagesRenderer.renderSettingsHelpMessage());
        numOfArgs.put("settings help", 0);

        commands.put("settings show color", args -> SettingsHandler.renderColor());
        numOfArgs.put("settings show color", 0);

        commands.put("settings show colorlist", args -> SettingsHandler.renderColorList());
        numOfArgs.put("settings show colorlist", 0);

        commands.put("settings show username", args -> SettingsHandler.renderUsername());
        numOfArgs.put("settings show username", 0);

        commands.put("settings set color", args -> SettingsHandler.setColor(args[0].toUpperCase()));
        numOfArgs.put("settings set color", 1);

        commands.put("settings set username", args -> SettingsHandler.setUsername(args[0]));
        numOfArgs.put("settings set username", 1);

        commands.put("music help", args -> HelpMessagesRenderer.renderMusicHelpMessage());
        numOfArgs.put("music help", 0);

        commands.put("music show current", args -> Playback.renderCurTrackInfo());
        numOfArgs.put("music show current", 0);

        commands.put("music show tracks", args -> TracksManager.renderTrackList());
        numOfArgs.put("music show tracks", 0);

        commands.put("music start -s", args -> Playback.startPlaybackFromStart());
        numOfArgs.put("music start -s", 0);

        commands.put("music start", args -> Playback.startPlaybackFromTrack(args[0]));
        numOfArgs.put("music start", 1);

        commands.put("music loop", args -> Playback.loopCurTrack());
        numOfArgs.put("music loop", 0);

        commands.put("music unloop", args -> Playback.unloopCurTrack());
        numOfArgs.put("music unloop", 0);

        commands.put("music restart", args -> Playback.restartCurTrack());
        numOfArgs.put("music restart", 0);

        commands.put("music pause", args -> Playback.pauseCurTrack());
        numOfArgs.put("music pause", 0);

        commands.put("music continue", args -> Playback.continueCurTrack());
        numOfArgs.put("music continue", 0);

        commands.put("music stop", args -> Playback.stopPlayback());
        numOfArgs.put("music stop", 0);
    }

    static void handleCommand(String[] tokens) {
        for (int i = tokens.length; i > 0; i--) {
            String commandKey = String.join(" ", Arrays.copyOfRange(tokens, 0, i));

            Command command = commands.get(commandKey);

            if (command != null) {
                String[] remainingArgs = Arrays.copyOfRange(tokens, i, tokens.length);

                if (numOfArgs.get(commandKey) != remainingArgs.length) {
                    CommandsHandler.renderInvalidArgsWarning(numOfArgs.get(commandKey));

                    return;
                }

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

    static void renderInvalidArgsWarning(int requiredArgs) {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nIncorrect number of values. Expected: " + requiredArgs + ".\n");
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
