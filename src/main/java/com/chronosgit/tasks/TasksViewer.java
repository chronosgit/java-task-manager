package com.chronosgit.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jline.utils.*;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;

public class TasksViewer {
    public static void renderAllTasks() {
        TasksStorage.runIfTasksAreLoaded(() -> {
            if (TasksStorage.virtualTasks.size() == 0) {
                JLine.terminal.writer().write("\nTasks list is empty.\n\n");
                JLine.terminal.flush();

                return;
            }

            JLine.terminal.writer().write("\nAll tasks:\n");

            for (Task t : TasksStorage.virtualTasks) {
                t.renderTaskInfo();
            }
        });
    }

    public static void renderExpiredTasks() {
        TasksStorage.runIfTasksAreLoaded(() -> {
            if (TasksStorage.virtualTasks.isEmpty()) {
                JLine.terminal.writer().write("\nTasks list is empty.\n\n");
                JLine.terminal.flush();

                return;
            }

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            boolean anyExpired = false;

            JLine.terminal.writer().write("\nExpired tasks:\n");

            for (Task t : TasksStorage.virtualTasks) {
                String end = t.getEndDate();

                if (end == null || end.isBlank()) {
                    continue;
                }

                try {
                    LocalDate endDate = LocalDate.parse(end.trim(), formatter);

                    if (endDate.isBefore(today)) {
                        t.renderTaskInfo();

                        anyExpired = true;
                    }
                } catch (Exception e) {
                    new AttributedString("Task with ID: " + t.getId() + " has invalid end date format",
                            StylePreset.ERROR).print(JLine.terminal);
                }
            }

            if (!anyExpired) {
                JLine.terminal.writer().write("\nNone.\n\n");
            }

            JLine.terminal.flush();
        });
    }

    public static void renderCompletedTasks() {
        TasksStorage.runIfTasksAreLoaded(() -> {
            if (TasksStorage.virtualTasks.isEmpty()) {
                JLine.terminal.writer().write("\nTasks list is empty.\n\n");
                JLine.terminal.flush();

                return;
            }

            boolean anyCompleted = false;

            JLine.terminal.writer().write("\nCompleted tasks:\n");

            for (Task t : TasksStorage.virtualTasks) {
                if (t.getIsCompleted()) {
                    t.renderTaskInfo();

                    anyCompleted = true;
                }
            }

            if (!anyCompleted) {
                JLine.terminal.writer().write("\nNone.\n\n");
            }

            JLine.terminal.flush();
        });
    }

    public static void renderUncompletedTasks() {
        TasksStorage.runIfTasksAreLoaded(() -> {
            if (TasksStorage.virtualTasks.isEmpty()) {
                JLine.terminal.writer().write("\nTasks list is empty.\n\n");
                JLine.terminal.flush();

                return;
            }

            boolean anyUncompleted = false;

            JLine.terminal.writer().write("\nUncompleted tasks:\n");

            for (Task t : TasksStorage.virtualTasks) {
                if (!t.getIsCompleted()) {
                    t.renderTaskInfo();

                    anyUncompleted = true;
                }
            }

            if (!anyUncompleted) {
                JLine.terminal.writer().write("\nNone.\n\n");
            }

            JLine.terminal.flush();
        });
    }

    public static void renderTask(String id) {
        TasksStorage.runIfTasksAreLoaded(() -> {
            if (TasksStorage.virtualTasks.isEmpty()) {
                JLine.terminal.writer().write("\nTasks list is empty.\n\n");
                JLine.terminal.flush();

                return;
            }

            for (Task t : TasksStorage.virtualTasks) {
                if (t.getId().equals(id)) {
                    t.renderTaskInfo();

                    return;
                }
            }

            JLine.terminal.writer().write("\nTask with such ID doesn't exist.\n\n");
            JLine.terminal.flush();
        });
    }
}
