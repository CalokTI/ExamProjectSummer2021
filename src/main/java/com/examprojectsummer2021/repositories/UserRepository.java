package com.examprojectsummer2021.repositories;

import com.examprojectsummer2021.utilities.DatabaseConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Carsten
 * Time: 12.37
 * Date: 06/05/2021
 */
public class UserRepository {

    private Connection conn;

    public UserRepository(){
        conn = DatabaseConnectionUtility.getConn(); //static call, ingen instance af class
    }

    public String getPasswordForUsername(String username){

        try{
            String sql = "SELECT password FROM user WHERE username = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null){
                return resultSet.getString(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
