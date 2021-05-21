package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.repositories.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Anton
 */

public class TaskService {

    TaskRepository taskRepository = new TaskRepository();
    // ------ SETTERS ------ //

    public void createTask(String taskTitle, String taskDescription, String[] taskUsers, String taskOwner, int taskPrice, int taskTime, int projectID) {


        taskRepository.createNewTask(taskTitle, taskDescription, taskPrice, taskTime, taskOwner, projectID);

        linkUserAndTask(taskUsers, taskTitle);
    }

    public void changeTaskFinished(int taskID) {

        Task task = getTask(taskID);

        boolean state;

        if (task.isFinished()){
            state = false;
        } else {
            state = true;
        }

        taskRepository.changeTaskFinished(state, taskID);

    }

    public void linkUserAndTask(String[] taskUsers, String taskTitle) {
        int taskID = getTaskID(taskTitle);

        for (String s : taskUsers) {
            taskRepository.linkUserAndTask(s, taskID);
        }
    }

    // ------ GETTERS ------ //

    public Task getTask(int taskID) {

        ResultSet resultSet = taskRepository.getTask(taskID);
        Task task = null;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);                   // id
                String title = resultSet.getString(2);          // title
                String description = resultSet.getString(3);    // description
                int price = resultSet.getInt(4);                // price
                boolean isFinished = resultSet.getBoolean(5);   // isFinished
                int time = resultSet.getInt(6);                 // time

                task = new Task(id, title, description, price, isFinished, time);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return task;
    }

    public int getTaskID(String taskTitle) {
        return taskRepository.getTaskID(taskTitle);
    }

    public ArrayList<Task> getTasksFromProject(int projectID) {

        ResultSet resultSet = taskRepository.getTasksFromProject(projectID);

        ArrayList<Task> taskList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);                   // id
                String title = resultSet.getString(2);          // title
                String description = resultSet.getString(3);    // description
                int price = resultSet.getInt(4);                // price
                boolean isFinished = resultSet.getBoolean(5);   // isFinished
                int time = resultSet.getInt(6);                 // time

                Task tmpTask = new Task(id, title, description, price, isFinished, time);

                taskList.add(tmpTask);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return taskList;
    }


}
