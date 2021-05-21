package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.TaskService;
import com.examprojectsummer2021.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Carsten & Anton
 */

@Controller
public class TaskController {

    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();

    // ------------ CREATE TASK ------------ //
    @GetMapping("/createtask")
    public String renderNewTask(Model model, @RequestParam(name = "projectID") int projectID) {
        model.addAttribute("projectUsers", userService.getUsersFromProject(projectID));
        model.addAttribute("projectID", projectID);

        return "task/createtask.html";
    }

    @PostMapping("/createtask")
    public String createNewTask(@RequestParam(name = "title") String taskTitle,
                                @RequestParam(name = "description") String taskDescription,
                                @RequestParam(name = "time") int taskTime,
                                @RequestParam(name = "username") String[] taskUsers,
                                @RequestParam(name = "price", defaultValue = "0") int taskPrice,
                                @RequestParam(name = "projectID") String projectIDString) {

        int projectID = Integer.parseInt(projectIDString);
        //todo add owner
        String taskOwner = "jowa69";

        taskService.createTask(taskTitle, taskDescription, taskUsers, taskOwner, taskPrice, taskTime, projectID);

        return "redirect:/updateproject/" + projectID;
    }


    // ------------ EDIT TASK ------------ //
    @GetMapping("/updateproject/updatetask/{id}")
    public String renderUpdateTask(@PathVariable("id") int taskID, Model model){

        model.addAttribute("task",taskService.getTask(taskID));
        model.addAttribute("users",userService.getUsersFromTask(taskID));

        return "task/updatetask.html";
    }

    @PostMapping("/change_finished_status")
    public String renderChangeStatus(@RequestParam(name = "projectID") int projectID,
                                     @RequestParam(name = "taskID") int taskID) {

        taskService.changeTaskFinished(taskID);

        return "redirect:/updateproject/" + projectID;
    }
}

