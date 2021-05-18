package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.repositories.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {

    TaskRepository taskRepository = new TaskRepository();

    public void createTask(String taskTitle, String taskDescription, String taskDeadline, String[] taskUsername, String taskOwner, int projectID){

        taskRepository.createNewTask(taskTitle, taskDescription, taskOwner, projectID);

        linkUserAndTask(taskUsername, taskTitle);
    }

    public void linkUserAndTask(String[] taskUsername, String taskTitle){
        int taskID = getTaskID(taskTitle);

        for (String s : taskUsername) {
            taskRepository.linkUserAndTask(s, taskID);
        }
    }

    // ------ SETTERS ------ //

    // ------ GETTERS ------ //

    public int getTaskID(String taskTitle){
        return taskRepository.getTaskID(taskTitle);
    }

    public ArrayList<Task> getTasksFromProject(int projectID) {

        ResultSet resultSet = taskRepository.getTasksFromProject(projectID);

        ArrayList<Task> taskList = new ArrayList<>();

        try {
            while (resultSet.next()){
                int id = resultSet.getInt(1);    // id
                String title = resultSet.getString(2);   // title
                String description = resultSet.getString(3);    // description
                //String role = resultSet.getString(4);        //todo owner - maybe?

                Task tmpTask = new Task(id,title,description);

                taskList.add(tmpTask);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return taskList;
    }



}
