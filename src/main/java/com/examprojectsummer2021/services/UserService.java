package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.User;
import com.examprojectsummer2021.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Julius Panduro
 */
public class UserService {

    private UserRepository userRepository = new UserRepository();

    // ------ SETTERS ------ //

    // ------ GETTERS ------ //

    public User getUserFromDatabase(String username) {
        ResultSet resultSet = userRepository.getUserFromDatabase(username);
        User user = null;
        try {
            String tempFirstname = resultSet.getString(1);
            String tempLastname = resultSet.getString(2);
            String tempUsername = resultSet.getString(3);
            String tempRole = resultSet.getString(4);
            user = new User(tempFirstname, tempLastname, tempUsername, tempRole);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }




}
