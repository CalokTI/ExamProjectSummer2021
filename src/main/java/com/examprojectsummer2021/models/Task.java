package com.examprojectsummer2021.models;

/**
 * @author Anton
 */

public class Task {

    private String title;
    private String description;
    private int taskID; // for SQL

    public Task(String title, String description, int taskID) {
        this.title = title;
        this.description = description;
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskID() {
        return taskID;
    }
}
