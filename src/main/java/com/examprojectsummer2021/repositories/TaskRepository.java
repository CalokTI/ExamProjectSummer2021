package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Julius
 * Time: 10.24
 * Date: 10/05/2021
 */
public class TaskRepository {

    private Connection conn;

    public TaskRepository(){
        DatabaseConnectionUtility dbConnect = new DatabaseConnectionUtility();
        conn = dbConnect.getConn();
    }

    private int getSpecificTaskFromDatabase(int taskID) {
        try {
            String sql = "SELECT task FROM task WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null){
                return resultSet.getInt(taskID);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }
}
