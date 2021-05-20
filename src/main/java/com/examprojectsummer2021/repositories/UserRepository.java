package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Carsten, Julius & Anton
 */

public class UserRepository {

    private Connection conn;

    // Constructor
    public UserRepository() {
        DatabaseConnectionUtility dbConnect = new DatabaseConnectionUtility();
        conn = dbConnect.getConn();
    }

    //NOTE. WE HAVE PREDEFINED USERS -> WE DONT CREATE NEW USERS ATM, MAYBE WITH A ADMIN SITE IN THE FUTURE..

    // ------ SETTERS ------ //

    // none

    // ------ GETTERS ------ //

    //---- Returns a specific user from database, based on username ---- \\
    //todo test om det fungere optimalt
    public ResultSet getUserFromDatabase(String username) {
        String sql = "SELECT user.username, user.first_name, user.last_name, user.role, salary FROM user INNER JOIN role_salary ON user.role = role_salary.role WHERE username = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

           return resultSet;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public ResultSet getUsersFromTask(int taskID) {
        try {
            String sql = "SELECT user.username, user.first_name, user.last_name, user.role, user_task.taskID, role_salary.salary FROM user INNER JOIN  user_task ON user.username = user_task.username INNER JOIN role_salary ON user.role = role_salary.role WHERE user_task.taskID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    // Returnér alle users fra specefikt projekt
    public ResultSet getUsersFromProject(int projectID) {
        try {
            String sql = "SELECT user.username, user.first_name, user.last_name, user.role, user_project.projectID, role_salary.salary FROM user INNER JOIN  user_project ON user.username = user_project.username INNER JOIN role_salary ON user.role = role_salary.role WHERE user_project.projectID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public ResultSet getAllUsers(){
        String sql = "SELECT user.username, user.first_name, user.last_name, user.role, salary FROM user INNER JOIN role_salary ON user.role = role_salary.role";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserRepository - getAllUsers");
        }
        return null;
    }


    //----- Returns the password for the specific user -----\\
    public String getPasswordForUsername(String username) {

        try {
            String sql = "SELECT password FROM user WHERE username = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}

