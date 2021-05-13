package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Julius
 * Time: 10.41
 * Date: 10/05/2021
 */
public class ProjectRepository {

    private Connection conn;

    // Constructor
    public ProjectRepository() {
        DatabaseConnectionUtility dbConnect = new DatabaseConnectionUtility();
        conn = dbConnect.getConn();
    }

    // ------ SETTERS ------ //

    public void createNewProject(String projectTitle, String projectDescription, String owner) {
        String sql = "INSERT INTO project (title, description, owner) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, projectTitle);
            preparedStatement.setString(2, projectDescription);
            preparedStatement.setString(3, owner);
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
