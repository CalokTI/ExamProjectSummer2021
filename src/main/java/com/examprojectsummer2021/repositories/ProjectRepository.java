package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.*;

/**
 * @author Julius & Anton
 */
public class ProjectRepository {

    private Connection conn;

    // Constructor
    public ProjectRepository() {
        DatabaseConnectionUtility dbConnect = new DatabaseConnectionUtility();
        conn = dbConnect.getConn();
    }

    // ------ SETTERS ------ //

    public void createNewProject(String projectTitle, String projectDescription, String owner,
                                 String inceptionDate, String projectDeadline, boolean isFinished) {
        String sql = "INSERT INTO project (title, description, owner, inception_date, deadline, is_finished) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, projectTitle);
            preparedStatement.setString(2, projectDescription);
            preparedStatement.setString(3, owner);
            preparedStatement.setString(4, inceptionDate);
            preparedStatement.setString(5, projectDeadline);
            preparedStatement.setBoolean(6, isFinished);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void linkUserAndProject(String projectUsername, int projectID){
        String sql = "INSERT INTO user_project (username, projectID) VALUES (?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,projectUsername);
            statement.setInt(2,projectID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("projectRepository - linkUserAndProject");
        }
    }

    public void changeProjectFinished(boolean state, int taskID){
        String sql = "UPDATE project SET is_finished = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBoolean(1, state);
            preparedStatement.setInt(2, taskID);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // ------ GETTERS ------ //

    public int getProjectID(String projectTitle, String projectOwner){
        int projectID = -1;

        String sql = "SELECT id FROM project WHERE title = ? AND owner = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,projectTitle);
            statement.setString(2,projectOwner);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                projectID = resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectRepository - getProjectID");
        }

        return projectID;
    }

    public ResultSet getAllProjects() {
        String sql = "SELECT * FROM project";
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectRepository - getAllProjects");
        }
        return resultSet;
    }

    public ResultSet getSpecificProjectFromDatabase(int projectID) {
        String sql = "SELECT * FROM project WHERE id = ?";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,projectID);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return resultSet;
    }
}
