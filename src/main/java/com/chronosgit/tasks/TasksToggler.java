package com.chronosgit.tasks;

import java.util.ArrayList;
import java.util.List;

import org.jline.utils.*;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;
import com.chronosgit.utils.StringMethods;

public class TasksToggler {
    public static void completeTasks(String[] ids, boolean completeAll) {
        if (!completeAll && ids.length == 0) {
            new AttributedString("\nAt least one ID must be provided.\n\n", StylePreset.ERROR)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        TasksStorage.runIfTasksAreLoaded(() -> {
            List<Task> copy = new ArrayList<>(TasksStorage.virtualTasks);

            for (Task t : copy) {
                if (completeAll || StringMethods.contains(ids, t.getId())) {
                    t.cross();
                }
            }

            try {
                TasksStorage.writeTasksToFile(copy);

                JLine.terminal.writer().write("\nSome or no tasks were completed.\n\n");
                JLine.terminal.flush();
            } catch (RuntimeException e) {
                new AttributedString("\nTask completion aborted.\nCouldn't write to file.\n\n", StylePreset.ERROR)
                        .print(JLine.terminal);
                JLine.terminal.flush();
            }
        });
    }

    public static void uncompleteTasks(String[] ids, boolean uncompleteAll) {
        if (!uncompleteAll && ids.length == 0) {
            new AttributedString("\nAt least one ID must be provided.\n\n", StylePreset.ERROR)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        TasksStorage.runIfTasksAreLoaded(() -> {
            List<Task> copy = new ArrayList<>(TasksStorage.virtualTasks);

            for (Task t : copy) {
                if (uncompleteAll || StringMethods.contains(ids, t.getId())) {
                    t.uncross();
                }
            }

            try {
                TasksStorage.writeTasksToFile(copy);

                JLine.terminal.writer().write("\nSome or no tasks were uncompleted.\n\n");
                JLine.terminal.flush();
            } catch (RuntimeException e) {
                new AttributedString("\nTask uncompletion aborted.\nCouldn't write to file.\n\n", StylePreset.ERROR)
                        .print(JLine.terminal);
                JLine.terminal.flush();
            }
        });
    }
}
