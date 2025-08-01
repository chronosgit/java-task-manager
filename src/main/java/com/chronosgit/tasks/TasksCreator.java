package com.chronosgit.tasks;

import org.jline.utils.AttributedString;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;
import com.chronosgit.utils.ISODateVerifier;

public class TasksCreator {
    public static void createTask(String[] args) {
        if (args.length != 2 && args.length != 3) {
            new AttributedString("\nInvalid number of argumens.\n\n", StylePreset.ERROR)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        String title = args[0];
        String body = args[1];
        String endDate = args.length == 3 ? args[2] : "";

        if (title == null || title.length() <= 0 || title.length() > 50) {
            new AttributedString("\nTask creation aborted.\nTitle must be between 1 and 50 characters.\n\n",
                    StylePreset.WARNING)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        if (body == null || body.length() == 0 || body.length() > 128) {
            new AttributedString("\nTask creation aborted.\nBody must be between 1 and 128 characters.\n\n",
                    StylePreset.WARNING)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        if (endDate.length() > 0 && !ISODateVerifier.verifyDate(endDate)) {
            new AttributedString("\nTask creation aborted.\nEnd date must be of format yyyy-MM-dd.\n\n",
                    StylePreset.WARNING)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        Task t = new Task(title, body, endDate);

        TasksStorage.addTask(t);
    }
}