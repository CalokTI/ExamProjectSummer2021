package com.examprojectsummer2021.models;

import java.util.Date;

/**
 * @author Anton
 */
//todo add owner
public class Project {

    private int projectID; // unique ID for SQL
    private String title;
    private String description;

    private Date inceptionDate;
    private Date deadline;


    public Project(String title, String description, int projectID) {
        this.title = title;
        this.description = description;
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getProjectID() {
        return projectID;
    }
}
