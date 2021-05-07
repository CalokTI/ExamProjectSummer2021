package com.examprojectsummer2021.models;

/**
 * @author Anton
 */

public class Project {

    private String title;
    private String description;
    private int projectID;

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
