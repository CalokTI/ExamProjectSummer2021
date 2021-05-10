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
        conn = DatabaseConnectionUtility.getConn();
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
