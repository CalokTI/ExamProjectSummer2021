package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @author Carsten
 * Time: 11.43
 * Date: 10/05/2021
 */

@Controller
public class ProjectController {

    ProjectService projectService = new ProjectService();

    @GetMapping("/createproject")
    public String renderNewProject(){

        return "createproject.html";
    }

    @GetMapping("/updateproject/list.getID")
    public String renderUpdateProject(@PathVariable("list.getID") int projectID){

        return "updateproject.html";
    }

    @GetMapping("/dashboard")
    public String renderDashboard(Model model){
        List projectList = projectService.getAllProjects();

        model.addAttribute("list", projectList);
        return "dashboard.html";
    }


}
