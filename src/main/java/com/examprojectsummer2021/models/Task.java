package com.examprojectsummer2021.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Anton
 */

public class Task {

    private int taskID; // unique ID for SQL
    private String title;
    private String description;

    private Date inceptionDate;
    private Date deadline;

    //private ArrayList


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
