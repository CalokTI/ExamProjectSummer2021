package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.User;
import com.examprojectsummer2021.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {

    UserRepository userRepository = new UserRepository();

    // ------ SETTERS ------ //
        //return userList;
    // ------ GETTERS ------ //

    public ArrayList<User> getUsersFromProject(int projectID) {

        ResultSet resultSet = userRepository.getUsersFromProject(projectID);

        ArrayList<User> userList = null;

        try {
            while (resultSet.next()){
                String username = resultSet.getString(1);    // username
                String firstname = resultSet.getString(2);   // first_name
                String lastname = resultSet.getString(3);    // last_name
                String role = resultSet.getString(5);        // role

                User tmpUser = new User(username,firstname,lastname,role);
                userList.add(tmpUser);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}
