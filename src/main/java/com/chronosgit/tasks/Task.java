package com.chronosgit.tasks;

import java.time.LocalDate;

import org.jline.utils.AttributedStringBuilder;

import com.chronosgit.terminal.JLine;
import com.chronosgit.utils.ISODateVerifier;
import com.chronosgit.utils.IdGenerator;

public class Task {
    private String id;
    private String title;
    private String body;
    private boolean isCompleted;
    private String startDate;
    private String endDate;

    public Task(String id, String title, String body, boolean isCompleted, String startDate, String endDate) {
        if (!ISODateVerifier.verifyDate(endDate)) {
            endDate = "";
        }

        this.id = id;
        this.title = title;
        this.body = body;
        this.isCompleted = isCompleted;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Task(String title, String body, String endDate) {
        if (!ISODateVerifier.verifyDate(endDate)) {
            endDate = "";
        }

        this.id = IdGenerator.generateRandomHash();
        this.title = title;
        this.body = body;
        this.isCompleted = false;
        this.startDate = LocalDate.now().toString();
        this.endDate = endDate;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void cross() {
        this.isCompleted = true;
    }

    public void uncross() {
        this.isCompleted = false;
    }

    public void renderTaskInfo() {
        AttributedStringBuilder sb = new AttributedStringBuilder();

        sb.append("\nTask info:\n");
        sb.append("ID: " + this.id + "\n");
        sb.append("Title: " + this.title + "\n");
        sb.append("Body: " + this.body + "\n");
        sb.append(this.isCompleted ? "Completed.\n" : "Not completed.\n");
        sb.append("Start date: " + this.startDate + "\n");
        sb.append("End date: " + (endDate == null || endDate.isEmpty() ? "N/A" : endDate) + "\n\n");

        sb.toAttributedString().print(JLine.terminal);
        JLine.terminal.flush();
    }
}