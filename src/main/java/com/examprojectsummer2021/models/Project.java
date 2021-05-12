package com.examprojectsummer2021.models;

import java.util.ArrayList;
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

    //<editor-fold desc="html test">
    private ArrayList<User> users = fillArrayList();

    public ArrayList<User> fillArrayList(){
        ArrayList<User> users = new ArrayList<>();
        User user = new User("111", "Wayne", "jowa69", "Manager");
        User user2 = new User("222", "Wayne", "jowa69", "Manager");
        User user3 = new User("333", "Wayne", "jowa69", "Manager");
        users.add(user);
        users.add(user2);
        users.add(user3);
        return users;
    }

    public ArrayList<User> getList(){
        return this.users;
    }
    //</editor-fold>

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
