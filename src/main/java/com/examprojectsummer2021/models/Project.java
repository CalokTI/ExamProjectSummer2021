package com.examprojectsummer2021.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Anton
 */
public class Project {

    private int ID; // unique ID for SQL
    private String title;
    private String description;
    private Date deadline;

    // pseudo-attributes in SQL //
    // tasks
    // users

    public Project(int projectID, String title, String description, String deadline) {
        this.ID = projectID;
        this.title = title;
        this.description = description;

        try {
            this.deadline = new SimpleDateFormat("yy-MM-dd").parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    // -- GETTERS -- //
    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDeadlineAsString() {

       String deadlineString;
       String pattern = "dd-MM-yy";
       DateFormat df = new SimpleDateFormat(pattern);

       deadlineString = df.format(deadline.getTime());

        return deadlineString;
    }

    // -- UTILITY -- //

    // Days till the deadline from current date
    public long countdown(){

        Date today = new Date();

        long diff = deadline.getTime() - today.getTime();
        return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);

    }
}
