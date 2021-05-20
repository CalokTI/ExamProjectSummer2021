package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.User;
import com.examprojectsummer2021.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Julius & Anton
 */

public class UserService {

    private UserRepository userRepository = new UserRepository();

    // ------ SETTERS ------ //


    // ------ GETTERS ------ //

    public ArrayList<User> getUsersFromProject(int projectID) {

        ResultSet resultSet = userRepository.getUsersFromProject(projectID);

        ArrayList<User> userList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                String username = resultSet.getString(1);    // username
                String firstname = resultSet.getString(2);   // first_name
                String lastname = resultSet.getString(3);    // last_name
                String role = resultSet.getString(4);        // role
                int salary = resultSet.getInt(6);           //salary

                User tmpUser = new User(username, firstname, lastname, role,salary);
                userList.add(tmpUser);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    public ArrayList<User> getUsersFromTask(int taskID) {

        ResultSet resultSet = userRepository.getUsersFromTask(taskID);

        ArrayList<User> userList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                String username = resultSet.getString(1);    // username
                String firstname = resultSet.getString(2);   // first_name
                String lastname = resultSet.getString(3);    // last_name
                String role = resultSet.getString(4);        // role
                int salary = resultSet.getInt(6);           //salary

                User tmpUser = new User(username, firstname, lastname, role,salary);
                userList.add(tmpUser);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;

    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();

        try {
            while (resultSet.next()) {
                User user;
                String tempFirstname = resultSet.getString(1);
                String tempLastname = resultSet.getString(2);
                String tempUsername = resultSet.getString(3);
                String tempRole = resultSet.getString(4);
                int tempSalary = resultSet.getInt(5);
                user = new User(tempFirstname, tempLastname, tempUsername, tempRole,tempSalary);
                allUsers.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("UserService - getAllUsers");
        }
        return allUsers;
    }

    public User getUserFromDatabase(String username) {
        ResultSet resultSet = userRepository.getUserFromDatabase(username);
        User user = null;
        try {
            while (resultSet.next()) {
                String tempUsername = resultSet.getString(1);
                String tempFirstname = resultSet.getString(2);
                String tempLastname = resultSet.getString(3);
                String tempRole = resultSet.getString(4);
                int tempSalary = resultSet.getInt(5);


                user = new User(tempUsername, tempFirstname, tempLastname, tempRole,tempSalary);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }


    public Map<String, Integer> getRoleAndSalaryMap() {
        Map<String, Integer> rolesAndSalaryMap = new HashMap<>();
        ResultSet resultSet = userRepository.getUserHourPrice();

        try {
            while (resultSet.next()) {
                String tempSalaryRole = resultSet.getString(1);
                int value = resultSet.getInt(2);
                rolesAndSalaryMap.put(tempSalaryRole, value);
            }
            return rolesAndSalaryMap;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public int getUserHourPriceFromDatabase() {
        Map<String, Integer> tempMap = getRoleAndSalaryMap();
        ArrayList<User> allUsersList = getAllUsers();
        int salary;
        for (int i = 0; i < allUsersList.size(); i++) {
            if (tempMap.containsKey(allUsersList.get(i).getRole())) {
                salary = tempMap.get(allUsersList.get(i).getRole());
                return salary;
            }

            System.out.println("Wrong");
        }
        return -1;
    }


    //Der skal kobles en salary på userrole
    //


}
