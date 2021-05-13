package com.examprojectsummer2021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Carsten
 * Time: 11.44
 * Date: 10/05/2021
 */

@Controller
public class TaskController {

    @GetMapping("/createtask")
    public String renderNewTask(Model model, @RequestParam(name = "projectID") int projectID){

        model.addAttribute("projectID", projectID);

        return "task/createtask.html";
    }

    @PostMapping("/createtask")
    public String createNewTask(){


        //efter task er oprettet skal man tilbage til project siden,
        return "redirect:/";
    }


    @GetMapping("/updatetask")
    public String renderUpdateTask(){

        return "task/updateproject.html";
    }
}
