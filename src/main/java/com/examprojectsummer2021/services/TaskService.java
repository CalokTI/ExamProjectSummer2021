package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.User;
import com.examprojectsummer2021.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {

    UserRepository userRepository = new UserRepository();

    public ArrayList<User> usersFromProject(int projectID) {

        ResultSet test = userRepository.getUsersFromProject(projectID);

        ArrayList<User> listofthemguys;

        try {
            while (test.next()){

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
