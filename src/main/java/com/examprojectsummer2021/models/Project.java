package com.examprojectsummer2021.models;

import java.util.Date;

/**
 * @author Anton
 */
public class Project {

    private int ID; // unique ID for SQL
    private String title;
    private String description;
    private Date inceptionDate;
    private Date deadline;

    public Project(String title, String description, int projectID) {
        this.title = title;
        this.description = description;
        this.ID = projectID;
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
