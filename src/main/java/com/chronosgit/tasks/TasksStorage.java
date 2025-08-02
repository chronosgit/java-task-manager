package com.chronosgit.tasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jline.utils.*;

import com.chronosgit.terminal.JLine;
import com.chronosgit.terminal.StylePreset;
import com.chronosgit.utils.CSVEscaper;

public class TasksStorage {
    private static String filePath = "src/main/.data/.tasks.csv";
    private static boolean areTasksLoaded = true;

    static List<Task> virtualTasks = new ArrayList<>();

    static {
        try {
            loadTasks();
        } catch (Exception e) {
            areTasksLoaded = false;
        }
    }

    static boolean areTasksLoaded() {
        return areTasksLoaded;
    }

    static void runIfTasksAreLoaded(Runnable action) {
        if (!areTasksLoaded()) {
            new AttributedString(
                    "\nTasks weren't loaded correctly.\nOperation cancelled.\n\n",
                    StylePreset.ERROR).print(JLine.terminal);

            JLine.terminal.flush();
            return;
        }

        action.run();
    }

    static void addTask(Task t) throws RuntimeException {
        String csvLine = String.format("%s,%s,%s,%b,%s,%s%n",
                t.getId(),
                CSVEscaper.escapeCsv(t.getTitle()),
                CSVEscaper.escapeCsv(t.getBody()),
                t.getIsCompleted(),
                t.getStartDate(),
                t.getEndDate());

        try (FileWriter fw = new FileWriter(filePath, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(csvLine);

            virtualTasks.add(t);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write task to file.", e);
        }
    }

    static void writeTasksToFile(List<Task> tasks) throws RuntimeException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            bw.write("id,title,body,isCompleted,start,end");
            bw.newLine();

            for (Task task : tasks) {
                String line = String.join(",",
                        task.getId(),
                        task.getTitle(),
                        task.getBody(),
                        Boolean.toString(task.getIsCompleted()),
                        task.getStartDate(),
                        task.getEndDate());

                System.out.println(line);

                bw.write(line);
                bw.newLine();
            }

            virtualTasks = tasks;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write tasks to file.", e);
        }
    }

    private static void loadTasks() throws RuntimeException {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",", -1);

                String id = fields[0].trim();
                String title = fields[1].trim();
                String body = fields[2].trim();
                boolean isCompleted = Boolean.parseBoolean(fields[3].trim());
                String startDate = fields[4].trim();
                String endDate = fields[5].trim();

                Task task = new Task(id, title, body, isCompleted, startDate, endDate);

                tasks.add(task);

                virtualTasks = tasks;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File couldn't be found: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file.", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid ID format in file.", e);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}