package com.chronosgit.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jline.utils.AttributedStringBuilder;

import com.chronosgit.music.*;
import com.chronosgit.settings.*;
import com.chronosgit.tasks.TasksCreator;
import com.chronosgit.tasks.TasksDeletor;
import com.chronosgit.terminal.*;

class CommandsManager {
    @FunctionalInterface
    interface Command {
        void run(String[] args);
    }

    static final Map<String, Command> commands = new HashMap<>();
    static final Map<String, Integer[]> numOfArgs = new HashMap<>();

    static {
        commands.put("help", args -> HelpMessagesRenderer.renderHelpMessage());
        numOfArgs.put("help", new Integer[] { 0 });

        commands.put("exit", args -> CommandsManager.gracefulExit());
        numOfArgs.put("exit", new Integer[] { 0 });

        commands.put("clear", args -> JLine.clearScreen());
        numOfArgs.put("clear", new Integer[] { 0 });

        commands.put("settings help", args -> HelpMessagesRenderer.renderSettingsHelpMessage());
        numOfArgs.put("settings help", new Integer[] { 0 });

        commands.put("settings show color", args -> SettingsHandler.renderColor());
        numOfArgs.put("settings show color", new Integer[] { 0 });

        commands.put("settings show colorlist", args -> SettingsHandler.renderColorList());
        numOfArgs.put("settings show colorlist", new Integer[] { 0 });

        commands.put("settings show username", args -> SettingsHandler.renderUsername());
        numOfArgs.put("settings show username", new Integer[] { 0 });

        commands.put("settings set color", args -> SettingsHandler.setColor(args[0].toUpperCase()));
        numOfArgs.put("settings set color", new Integer[] { 1 });

        commands.put("settings set username", args -> SettingsHandler.setUsername(args[0]));
        numOfArgs.put("settings set username", new Integer[] { 1 });

        commands.put("music help", args -> HelpMessagesRenderer.renderMusicHelpMessage());
        numOfArgs.put("music help", new Integer[] { 0 });

        commands.put("music show current", args -> Playback.renderCurTrackInfo());
        numOfArgs.put("music show current", new Integer[] { 0 });

        commands.put("music show tracks", args -> TracksManager.renderTrackList());
        numOfArgs.put("music show tracks", new Integer[] { 0 });

        commands.put("music start -s", args -> Playback.startPlaybackFromStart());
        numOfArgs.put("music start -s", new Integer[] { 0 });

        commands.put("music start", args -> Playback.startPlaybackFromTrack(args[0]));
        numOfArgs.put("music start", new Integer[] { 1 });

        commands.put("music loop", args -> Playback.loopCurTrack());
        numOfArgs.put("music loop", new Integer[] { 0 });

        commands.put("music unloop", args -> Playback.unloopCurTrack());
        numOfArgs.put("music unloop", new Integer[] { 0 });

        commands.put("music restart", args -> Playback.restartCurTrack());
        numOfArgs.put("music restart", new Integer[] { 0 });

        commands.put("music pause", args -> Playback.pauseCurTrack());
        numOfArgs.put("music pause", new Integer[] { 0 });

        commands.put("music continue", args -> Playback.continueCurTrack());
        numOfArgs.put("music continue", new Integer[] { 0 });

        commands.put("music stop", args -> Playback.stopPlayback());
        numOfArgs.put("music stop", new Integer[] { 0 });

        commands.put("tasks help", args -> HelpMessagesRenderer.renderTasksHelpMessage());
        numOfArgs.put("tasks help", new Integer[] { 0 });

        commands.put("tasks create", args -> TasksCreator.createTask(args));
        numOfArgs.put("tasks create", new Integer[] { 3, 2 });

        commands.put("tasks delete", args -> TasksDeletor.deleteTasks(args));
        numOfArgs.put("tasks delete", new Integer[] { Integer.MAX_VALUE });
    }

    static void manageCommand(String[] tokens) {
        for (int i = tokens.length; i > 0; i--) {
            String commandKey = String.join(" ", Arrays.copyOfRange(tokens, 0, i));

            Command command = commands.get(commandKey);

            if (command != null) {
                String[] remainingArgs = Arrays.copyOfRange(tokens, i, tokens.length);

                Integer[] opts = numOfArgs.get(commandKey);

                if (opts[0] == Integer.MAX_VALUE && remainingArgs.length > 0) {
                    command.run(remainingArgs);

                    return;
                }

                boolean flag = false;

                for (int j = 0; j < opts.length; j++) {
                    if (opts[j] == remainingArgs.length) {
                        flag = true;

                        break;
                    }
                }

                if (flag) {
                    command.run(remainingArgs);

                    return;
                }
            }
        }

        CommandsManager.renderWrongCommandWarning();
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
