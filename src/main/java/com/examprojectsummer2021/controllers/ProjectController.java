package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "createproject.html";
    }

    @PostMapping("/createproject")
    public String createNewProject(){

        projectService.createNewProject("","","");

        return "redirect/updateproject/list.getID";
    }

    @GetMapping("/updateproject/{id}")
    public String renderUpdateProject(@PathVariable("id") int projectID, Model model){

        model.addAttribute("project", projectService.getSpecificProject(projectID));

        return "project/updateproject.html";
    }
}
