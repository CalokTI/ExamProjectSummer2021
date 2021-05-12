package com.examprojectsummer2021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Carsten
 * Time: 11.44
 * Date: 10/05/2021
 */

@Controller
public class TaskController {

    @GetMapping("/task")
    public String renderNewTask(){



        return "createtask.html";
    }

    @PostMapping("/createtask")
    public String createNewTask(){


        //efter task er oprettet skal man tilbage til project siden,
        return "redirect:/";
    }


    @GetMapping("/updatetask")
    public String renderUpdateTask(){

        return "updateproject.html";
    }
}
