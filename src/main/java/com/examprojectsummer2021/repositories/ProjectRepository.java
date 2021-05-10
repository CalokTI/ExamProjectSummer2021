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

    public ProjectRepository(){
        DatabaseConnectionUtility dbConnect = new DatabaseConnectionUtility();
        conn = dbConnect.getConn();
    }


    public void createNewProject(int projectID, String projectTitle, String projectDescription,String owner) {
        String sql = "INSERT INTO project (projectID, projectTitle, projectDescription, owner) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);
            preparedStatement.setString(2, projectTitle);
            preparedStatement.setString(3, projectDescription);
            preparedStatement.setString(4, owner);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int getSpecificProjectFromDatabase(int projectID) {
        try {
            String sql = "SELECT project FROM project WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null){
                return resultSet.getInt(projectID);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
