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

    @GetMapping("/newproject")
    public String renderNewProject(){

        return "newProject.html";
    }

    @GetMapping("/updateproject/list.getID")
    public String renderUpdateProject(@PathVariable("list.getID") int projectID){

        return "updateProject.html";
    }


}
