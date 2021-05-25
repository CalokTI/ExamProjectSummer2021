package com.examprojectsummer2021.utilities;

import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.services.ProjectService;
import com.examprojectsummer2021.services.TaskService;

import java.util.ArrayList;

/**
 * @author Carsten
 */
public class TimeCalculationUtility {

    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();

    public double workingHoursPerDay(int projectID){
        double combinedTaskTime = combinedTaskTime(projectID);
        int daysToProjectDeadline = daysToProjectDeadline(projectID); //todo nullpointer?
        if (daysToProjectDeadline == 0){
            return -1;
        }

        return combinedTaskTime / daysToProjectDeadline;
    }

    private int combinedTaskTime(int projectID){
        ArrayList<Task> projectTasks = taskService.getTasksFromProject(projectID);
        int combinedTaskTime = 0;

        for (int i = 0; i < projectTasks.size(); i++) {
            if (!projectTasks.get(i).isFinished())
            combinedTaskTime += projectTasks.get(i).getTime();
        }

        return combinedTaskTime;
    }

    private int daysToProjectDeadline(int projectID){
        return (int) projectService.getSpecificProject(projectID).countdown();
    }

}
