package com.chronosgit.tasks;

import java.util.ArrayList;
import java.util.List;

import org.jline.utils.*;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;
import com.chronosgit.utils.StringMethods;

public class TasksDeletor {
    public static void deleteTasks(String[] ids, boolean deleteAll) {
        if (!deleteAll && ids.length == 0) {
            new AttributedString("\nAt least one ID must be provided.\n\n", StylePreset.ERROR)
                    .print(JLine.terminal);
            JLine.terminal.flush();

            return;
        }

        TasksStorage.runIfTasksAreLoaded(() -> {
            List<Task> persisting = new ArrayList<>();

            if (!deleteAll) {
                for (int i = 0; i < TasksStorage.virtualTasks.size(); i++) {
                    Task task = TasksStorage.virtualTasks.get(i);

                    if (!StringMethods.contains(ids, task.getId())) {
                        persisting.add(task);
                    }
                }
            }

            try {
                TasksStorage.writeTasksToFile(persisting);

                JLine.terminal.writer().write("\nSome or no tasks were deleted.\n\n");
                JLine.terminal.flush();
            } catch (RuntimeException e) {
                new AttributedString("\nTask deletion aborted.\nCouldn't write to file.\n\n", StylePreset.ERROR)
                        .print(JLine.terminal);
                JLine.terminal.flush();
            }
        });
    }
}
