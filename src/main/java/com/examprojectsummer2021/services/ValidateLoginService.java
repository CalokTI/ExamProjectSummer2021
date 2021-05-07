package com.examprojectsummer2021.services;

import com.examprojectsummer2021.repositories.UserRepository;

/**
 * @author Carsten
 * Time: 09.30
 * Date: 07/05/2021
 */
public class ValidateLoginService {

    UserRepository userRepository = new UserRepository();

    public boolean validateLogin(String username, String password){
        String storedPassword = userRepository.getPasswordForUsername(username);
        return password.equals(storedPassword);
    }

}
