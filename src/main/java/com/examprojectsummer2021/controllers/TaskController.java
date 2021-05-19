package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.TaskService;
import com.examprojectsummer2021.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Carsten
 */

@Controller
public class TaskController {

    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();

    @GetMapping("/createtask")
    public String renderNewTask(Model model, @RequestParam(name = "projectID") int projectID){
        model.addAttribute("projectUsers", userService.getUsersFromProject(projectID));
        model.addAttribute("projectID", projectID);

        return "task/createtask.html";
    }

    @PostMapping("/createtask")
    public String createNewTask(@RequestParam(name = "title") String taskTitle,
                                @RequestParam(name = "description") String taskDescription,
                                @RequestParam(name = "deadline") String taskDeadline,
                                @RequestParam(name = "username") String[] taskUsernames,
                                @RequestParam(name = "projectID")String projectIDString){

        int projectID = Integer.parseInt(projectIDString);
        //todo add owner
        String taskOwner = "jowa69";

        taskService.createTask(taskTitle, taskDescription, taskDeadline, taskUsernames, taskOwner, projectID);

        return "redirect:/updateproject/" + projectID;
    }


    @GetMapping("/updatetask")
    public String renderUpdateTask(){

        return "task/updateproject.html";
    }
}
