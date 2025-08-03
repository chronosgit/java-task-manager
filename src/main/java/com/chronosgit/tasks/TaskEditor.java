package com.chronosgit.tasks;

import java.util.ArrayList;
import java.util.List;

import org.jline.utils.*;

import com.chronosgit.app.InputManager;
import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;

public class TaskEditor {
    public static void editTask(String taskId) {
        TasksStorage.runIfTasksAreLoaded(() -> {
            List<Task> copy = new ArrayList<>(TasksStorage.virtualTasks);

            boolean wasEdited = false;

            for (Task t : copy) {
                if (t.getId().equals(taskId)) {
                    InputManager im = new InputManager();

                    String title = im.promptUserInput("\nTitle: ").trim();
                    String body = im.promptUserInput("Body: ").trim();
                    String endDate = im.promptUserInput("End date: ").trim();

                    boolean isTitleEdit = false;
                    boolean isBodyEdit = false;
                    boolean isEndDateEdit = false;

                    try {
                        if (!title.isEmpty()) {
                            t.setTitle(title);

                            isTitleEdit = true;
                        }
                    } catch (IllegalArgumentException e) {
                        isTitleEdit = false;
                    }

                    try {
                        if (!body.isEmpty()) {
                            t.setBody(body);

                            isBodyEdit = true;
                        }
                    } catch (IllegalArgumentException e) {
                        isBodyEdit = false;
                    }

                    try {
                        if (!endDate.isEmpty()) {
                            t.setEndDate(endDate);

                            isEndDateEdit = true;
                        }
                    } catch (IllegalArgumentException e) {
                        JLine.terminal.writer()
                                .write("\nThe end date must be of format \"yyyy-MM-dd\".\nThe value is omitted.\n");
                        JLine.terminal.flush();

                        isEndDateEdit = false;
                    }

                    if (!isTitleEdit && !isBodyEdit && !isEndDateEdit) {
                        JLine.terminal.writer().write("\nThe editing is skipped.\n\n");
                        JLine.terminal.flush();

                        return;
                    }

                    wasEdited = true;
                }
            }

            if (!wasEdited) {
                JLine.terminal.writer().write("\nTask with such ID doesn't exist.\n\n");
                JLine.terminal.flush();

                return;
            }

            try {
                TasksStorage.writeTasksToFile(copy);

                JLine.terminal.writer().write("\nThe task was edited.\n\n");
                JLine.terminal.flush();
            } catch (RuntimeException e) {
                new AttributedString("\nTask editing aborted.\nCouldn't write to file.\n\n", StylePreset.ERROR)
                        .print(JLine.terminal);
                JLine.terminal.flush();
            }
        });
    }
}
