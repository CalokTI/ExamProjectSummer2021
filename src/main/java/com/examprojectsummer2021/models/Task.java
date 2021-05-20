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
    private boolean isFinished;


    public Task(int taskID, String title, String description, boolean isFinished) {
        this.ID = taskID;
        this.title = title;
        this.description = description;
        this.isFinished = isFinished;
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

    public boolean isFinished() {
        return isFinished;
    }

    public String printIsFinished() {
        if (isFinished) {
            return "finished ✔️";
        }
        else {
            return "N҉̟̬O͈͘T͖͕̩ ❌";
        }
    }

}
