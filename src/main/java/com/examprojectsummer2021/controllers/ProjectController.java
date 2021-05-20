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

import java.util.Date;
import java.util.List;

/**
 * @author Carsten
 */

@Controller
public class ProjectController {

    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();

    // ------------ DASHBOARD ------------ //
    @GetMapping("/dashboard")
    public String renderDashboard(Model model){
        model.addAttribute("list", projectService.getAllProjects());


        return "project/dashboard.html";
    }


    // ------------ CREATE PROJECT ------------ //
    @GetMapping("/createproject")
    public String renderNewProject(Model model){

        model.addAttribute("allUsers", userService.getAllUsers());

        return "project/createproject.html";
    }


    @PostMapping("/createproject")
    public String createNewProject(@RequestParam(name = "title") String projectTitle,
                                   @RequestParam(name = "description") String projectDescription,
                                   @RequestParam(name = "startdate") String projectStartDate,
                                   @RequestParam(name = "deadline") String projectDeadline,
                                   @RequestParam(name ="username") String[] projectUsers){


        // owner
        String projectOwner = "jowa69"; //todo fix dette

        projectService.createNewProject(projectTitle, projectDescription, projectOwner, projectStartDate, projectDeadline, projectUsers);

        //todo fix link id til projectTitle
        int projectID = projectService.getProjectID(projectTitle, projectOwner);
        return "redirect:/updateproject/" + projectID;
    }

    // ------------ EDIT PROJECT ------------ //
    @GetMapping("/updateproject/{id}")
    public String renderUpdateProject(@PathVariable("id") int projectID, Model model){

        model.addAttribute("project", projectService.getSpecificProject(projectID));
        model.addAttribute("users", userService.getUsersFromProject(projectID));
        model.addAttribute("tasks", taskService.getTasksFromProject(projectID));
        return "project/updateproject.html";
    }

    @PostMapping("/change_project_status")
    public String renderChangeStatus(@RequestParam(name = "projectID") int projectID) {

        projectService.changeProjectStatus(projectID);

        return "redirect:/updateproject/" + projectID;
    }
}
