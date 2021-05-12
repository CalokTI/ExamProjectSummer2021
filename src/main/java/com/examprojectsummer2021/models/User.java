package com.examprojectsummer2021.models;

/**
 * @author Julius Panduro
 * Time: 09.25
 * Date: 07/05/2021
 */

public class User {

    private String username; // unique ID for SQL
    private String firstname;
    private String lastname;
    private String role;

    public User(String firstname, String lastname, String username, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    //Made to get the fully user information, to reduce the numbers of method calls.
    public String getFullUserInfo() {
        return firstname + lastname + username + role;
    }

}
