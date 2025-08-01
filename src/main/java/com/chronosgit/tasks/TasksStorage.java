package com.chronosgit.tasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.chronosgit.utils.CSVEscaper;

public class TasksStorage {
    private static String filePath = "src/main/.data/.tasks.csv";

    static List<Task> virtualTasks;

    static {
        try {
            loadTasks();
        } catch (Exception e) {
            virtualTasks = new ArrayList<>();
        }
    }

    public static void addTask(Task t) {
        String csvLine = String.format("%s,%s,%s,%s,%s%n",
                t.getId(),
                CSVEscaper.escapeCsv(t.getTitle()),
                CSVEscaper.escapeCsv(t.getBody()),
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

    public static void loadTasks() throws RuntimeException {
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
                String startDate = fields[3].trim();
                String endDate = fields[4].trim();

                Task task = new Task(id, title, body, startDate, endDate);

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File couldn't be found: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file.", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid ID format in file.", e);
        }

        virtualTasks = tasks;
    }
}