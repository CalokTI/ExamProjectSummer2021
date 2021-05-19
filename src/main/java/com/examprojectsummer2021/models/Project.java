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
    private Date startDate;
    private Date deadline;
    private boolean isFinished;

    // pseudo-attributes in SQL //
    // tasks
    // users

    public Project(int projectID, String title, String description, String startDate, String deadline, boolean isFinished) {
        this.ID = projectID;
        this.title = title;
        this.description = description;

        try {
            this.startDate = new SimpleDateFormat("yy-MM-dd").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.deadline = new SimpleDateFormat("yy-MM-dd").parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.isFinished = isFinished;


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

    public String getStartDateAsString(){

        String deadlineString;
        String pattern = "dd-MM-yy";
        DateFormat df = new SimpleDateFormat(pattern);

        deadlineString = df.format(startDate.getTime());

        return deadlineString;
    }

    // -- UTILITY -- //

    // Days till the deadline from current date
    public long countdown() {

        Date today = new Date();

        long diff = deadline.getTime() - today.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

    }

    // "Health-analyzer" of project
}
