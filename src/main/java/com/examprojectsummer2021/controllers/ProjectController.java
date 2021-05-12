package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Carsten
 * Time: 11.43
 * Date: 10/05/2021
 */

@Controller
public class ProjectController {

    ProjectService projectService = new ProjectService();

    @GetMapping("/dashboard")
    public String renderDashboard(Model model){
        List projectList = projectService.getAllProjects();
        model.addAttribute("list", projectList);
        return "project/dashboard.html";
    }


    @GetMapping("/createproject")
    public String renderNewProject(){
        //model med alle users
        //model med users i session -> se holdUsersInSession
        return "project/createproject.html";
    }


    @PostMapping("/createproject")
    public String createNewProject(){

        projectService.createNewProject("","","");
        //fix link id
        return "redirect:/updateproject/{id}";
    }


    @PostMapping("/createprojectusers")
    public String holdUsersInSession(){
        //intet project at tilknytte udvalgte users til, så de gemmes i session??
        //kig på get og post mapping af /createproject
        return "redirect:/createproject";
    }

    @GetMapping("/updateproject/{id}")
    public String renderUpdateProject(@PathVariable("id") int projectID, Model model){

        model.addAttribute("project", projectService.getSpecificProject(projectID));

        return "project/updateproject.html";
    }
}
