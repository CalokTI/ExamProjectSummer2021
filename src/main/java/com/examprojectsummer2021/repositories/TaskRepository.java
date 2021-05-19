package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Julius
 */

public class TaskRepository {

    private Connection conn;

    // Constructor
    public TaskRepository() {
        DatabaseConnectionUtility dbConnect = new DatabaseConnectionUtility();
        conn = dbConnect.getConn();
    }


    // ------ SETTERS ------ //

    public void createNewTask(String taskTitle, String taskDescription, String owner, int projectID) {
        String sql = "INSERT INTO task (title, description, owner, project) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, taskTitle);
            preparedStatement.setString(2, taskDescription);
            preparedStatement.setString(3, owner);
            preparedStatement.setInt(4, projectID);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changeTaskFinished(boolean state, int taskID){
        String sql = "UPDATE task SET is_finished = ? WHERE id = ?";
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setBoolean(1, state);
                    preparedStatement.setInt(2, taskID);
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
    }

    public void linkUserAndTask(String taskUsername, int taskID){
        String sql = "INSERT INTO user_task (username, taskID) VALUES (?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,taskUsername);
            statement.setInt(2,taskID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskRepository - linkUserAndTask");
        }

    }

    // ------ GETTERS ------ //

    public ResultSet getTask(int taskID){
        String sql = "SELECT * FROM task WHERE id = ?";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,taskID);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getTasksFromProject(int projectID) {
        try {
            String sql = "SELECT * FROM task where project = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public int getTaskID(String taskTitle){
        try {
            String sql = "Select id FROM task where title = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,taskTitle);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("TaskRepository - getTaskID");
        }
        return -1;
    }

    //todo wat is dis ;__;
    private int getSpecificTaskFromDatabase(int taskID) {
        try {
            String sql = "SELECT task FROM task WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                return resultSet.getInt(taskID);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
