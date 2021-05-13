package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ProjectService;
import com.examprojectsummer2021.services.TaskService;
import com.examprojectsummer2021.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author Carsten
 * Time: 11.43
 * Date: 10/05/2021
 */

@Controller
public class ProjectController {

    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();

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
    public String createNewProject(@RequestParam(name = "title") String projectTitle,
                                   @RequestParam(name = "description") String projectDescription,
                                   @RequestParam(name = "deadline") String projectDeadline){

        String projectOwner = "jowa69";

        projectService.createNewProject(projectTitle, projectDescription, projectOwner, projectDeadline);
        //fix link id
        int projectID = projectService.getProjectID(projectTitle, projectOwner);
        return "redirect:/updateproject/" + projectID;
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
        model.addAttribute("users", userService.getUsersFromProject(projectID));
        model.addAttribute("tasks", taskService.getTasksFromProject(projectID));
        return "project/updateproject.html";
    }
}
