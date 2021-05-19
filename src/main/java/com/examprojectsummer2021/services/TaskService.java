package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.repositories.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {

    TaskRepository taskRepository = new TaskRepository();
    // ------ SETTERS ------ //

    public void createTask(String taskTitle, String taskDescription, String taskDeadline, String[] taskUsername, String taskOwner, int projectID) {

        boolean status = false; // every new task starts with a false 'isFinished'

        taskRepository.createNewTask(taskTitle, taskDescription, taskOwner, projectID, status);

        linkUserAndTask(taskUsername, taskTitle);
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

    public void linkUserAndTask(String[] taskUsername, String taskTitle) {
        int taskID = getTaskID(taskTitle);

        for (String s : taskUsername) {
            taskRepository.linkUserAndTask(s, taskID);
        }
    }

    // ------ GETTERS ------ //

    public Task getTask(int taskID) {

        ResultSet resultSet = taskRepository.getTask(taskID);
        Task task = null;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                boolean status = resultSet.getBoolean(4);
                task = new Task(id, title, description, status);
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
                boolean isFinished = resultSet.getBoolean(4);   // isFinished

                Task tmpTask = new Task(id, title, description, isFinished);

                taskList.add(tmpTask);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return taskList;
    }


}
