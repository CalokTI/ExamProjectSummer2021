package com.examprojectsummer2021.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Anton
 */

public class Task {

    private int ID; // unique ID for SQL
    private String title;
    private String description;

    private Date inceptionDate;
    private Date deadline;

    //private ArrayList


    public Task(String title, String description, int taskID) {
        this.title = title;
        this.description = description;
        this.ID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return ID;
    }
}
