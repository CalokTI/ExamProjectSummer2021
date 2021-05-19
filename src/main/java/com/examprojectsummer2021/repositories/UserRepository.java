package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Carsten & Julius
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
        String sql = "SELECT * FROM user WHERE username = ?";

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

    // Returnér alle users fra specefikt projekt
    public ResultSet getUsersFromProject(int projectID) {
        try {
            String sql = "SELECT * FROM user INNER JOIN  user_project ON user.username = user_project.username WHERE user_project.projectID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
