package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

/**
 * @author Anton
 */

@Controller
public class AdderController {


    @GetMapping("/adder")
    public String renderAdder(Model model){

        //ArrayList<User> allUsers =


        //model.addAttribute(list,)

        return "adder";
    }

}
