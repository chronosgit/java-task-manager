package com.chronosgit.app;

import org.jline.utils.*;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;

class HelpMessagesRenderer {
    static void renderHelpMessage() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nBasic commands:\n");

        sb.append("settings help", StylePreset.COMMAND);
        sb.append(" - shows available settings commands\n");

        sb.append("music help", StylePreset.COMMAND);
        sb.append(" - shows available music commands\n");

        sb.append("tasks help", StylePreset.COMMAND);
        sb.append(" - shows available task commands\n");

        sb.append("exit", StylePreset.COMMAND);
        sb.append(" - exits the application\n\n");

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }

    static void renderSettingsHelpMessage() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nSettings commands:\n");

        sb.append("settings show color", StylePreset.COMMAND);
        sb.append(" - shows the current theme color\n");

        sb.append("settings show colorlist", StylePreset.COMMAND);
        sb.append(" - lists all allowed theme colors\n");

        sb.append("settings show username", StylePreset.COMMAND);
        sb.append(" - shows the current username\n");

        sb.append("settings set color <value>", StylePreset.COMMAND);
        sb.append(" - sets the theme color to a valid value\n");

        sb.append("settings set username <value>", StylePreset.COMMAND);
        sb.append(" - sets the username\n\n");

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }

    static void renderMusicHelpMessage() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nMusic commands:\n");

        sb.append("music show current", StylePreset.COMMAND);
        sb.append(" - shows the current track name and time\n");

        sb.append("music show tracks", StylePreset.COMMAND);
        sb.append(" - lists all available tracks\n");

        sb.append("music start -s", StylePreset.COMMAND);
        sb.append(" - starts the playback from the first track\n");

        sb.append("music start <track_id>", StylePreset.COMMAND);
        sb.append(" - starts the playback from the first or selected track\n");

        sb.append("music loop", StylePreset.COMMAND);
        sb.append(" - enables looping for the current track\n");

        sb.append("music unloop", StylePreset.COMMAND);
        sb.append(" - disables looping for the current track\n");

        sb.append("music restart", StylePreset.COMMAND);
        sb.append(" - restarts the current track from the beginning\n");

        sb.append("music pause", StylePreset.COMMAND);
        sb.append(" - pause the current track\n");

        sb.append("music continue", StylePreset.COMMAND);
        sb.append(" - resumes playback from where it was paused\n");

        sb.append("music stop", StylePreset.COMMAND);
        sb.append(" - stops playback and resets the track\n\n");

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }

    static void renderTasksHelpMessage() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nTask commands:\n");

        sb.append("tasks view", StylePreset.COMMAND);
        sb.append(" - shows all tasks\n");

        sb.append("tasks view -e", StylePreset.COMMAND);
        sb.append(" - shows expired tasks\n");

        sb.append("tasks view -c", StylePreset.COMMAND);
        sb.append(" - shows crossed-out tasks\n");

        sb.append("tasks view -u", StylePreset.COMMAND);
        sb.append(" - shows uncrossed (active) tasks\n");

        sb.append("tasks create", StylePreset.COMMAND);
        sb.append(" - creates a new task\n");

        sb.append("tasks update <task-id>", StylePreset.COMMAND);
        sb.append(" - updates the task with the given ID\n");

        sb.append("tasks cross <task-id> [<task-id>...]", StylePreset.COMMAND);
        sb.append(" - marks one or more tasks as crossed-out\n");

        sb.append("tasks uncross <task-id> [<task-id>...]", StylePreset.COMMAND);
        sb.append(" - marks one or more tasks as uncrossed\n");

        sb.append("tasks delete <task-id> [<task-id>...]", StylePreset.COMMAND);
        sb.append(" - deletes one or more tasks\n\n");

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }
}
