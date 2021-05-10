package com.examprojectsummer2021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Anton
 */

@Controller
public class AdderController {


    @GetMapping("/adder")
    public String renderAdder(Model model){


        return "adder";
    }

}
