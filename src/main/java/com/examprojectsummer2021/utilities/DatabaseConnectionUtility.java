package com.examprojectsummer2021.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Carsten
 * Time: 12.36
 * Date: 06/05/2021
 */
public class DatabaseConnectionUtility {

    private static String url;
    private static String user;
    private static String password;
    private static Connection conn = null;

    //Constructor
    public DatabaseConnectionUtility() {
        setLogin();
    }

    //loads login info from file
    private void setLogin() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            url = prop.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        if (conn != null){
            return conn;
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }
}
