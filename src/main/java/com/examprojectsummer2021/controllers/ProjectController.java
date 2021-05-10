package com.examprojectsummer2021.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author Carsten
 * Time: 11.43
 * Date: 10/05/2021
 */

@Controller
public class ProjectController {

    @GetMapping("/createproject")
    public String renderNewProject(){

        return "createproject.html";
    }

    @GetMapping("/updateproject/list.getID")
    public String renderUpdateProject(@PathVariable("list.getID") int projectID){

        return "updateproject.html";
    }

    @GetMapping("/dashboard")
    public String renderDashboard(){

        return "dashboard.html";
    }


}
