package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Project;
import com.examprojectsummer2021.models.Task;
import com.examprojectsummer2021.repositories.ProjectRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Julius & Anton
 */

public class ProjectService {

    private ProjectRepository projectRepository = new ProjectRepository();
    private TaskService taskService = new TaskService();

    // ------ SETTERS ------ //

    public void createNewProject(String projectTitle, String projectDescription, String projectOwner, String startDate, String deadline, String[] projectUsers) {
        projectRepository.createNewProject(projectTitle, projectDescription, projectOwner, startDate, deadline);
        linkUserAndProject(projectUsers, projectTitle);
    }

    private void linkUserAndProject(String[] projectUsers, String projectTitle) {
        int projectID = getProjectID(projectTitle);

        for (String s : projectUsers) {
            projectRepository.linkUserAndProject(s, projectID);
        }
    }

    public void changeProjectStatus(String projectTitle) {

        int projectID = getProjectID(projectTitle);
        Project project = getSpecificProject(projectID);

        boolean state;

        state = !project.isFinished();

        projectRepository.changeProjectFinished(state, projectID);

    }

    public void deleteProject(String projectTitle) {
        projectRepository.deleteProject(projectTitle);
    }

    // ------ GETTERS ------ //

    public int getProjectID(String projectTitle) {
        return projectRepository.getProjectID(projectTitle);
    }

    public Project getSpecificProject(int projectID) {
        ResultSet resultSet = projectRepository.getSpecificProjectFromDatabase(projectID);
        Project project = null;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                String owner = resultSet.getString(4);
                String inceptionDate = resultSet.getString(5);
                String deadline = resultSet.getString(6);
                boolean isFinished = resultSet.getBoolean(7);
                project = new Project(id, title, description, owner, inceptionDate, deadline, isFinished);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return project;
    }

    public ArrayList getAllProjects() {

        ArrayList allProjects = new ArrayList();
        ResultSet resultSet = projectRepository.getAllProjects();
        Project project;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                String owner = resultSet.getString(4);
                String inceptionDate = resultSet.getString(5);
                String deadline = resultSet.getString(6);
                boolean isFinished = resultSet.getBoolean(7);
                project = new Project(id, title, description, owner, inceptionDate, deadline, isFinished);
                allProjects.add(project);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectService - getAllProjects");
        }
        return allProjects;
    }

    public ArrayList<String> getAllProjectTitles() {
        return projectRepository.getAllProjectTitles();
    }

    public int getTotalPrice(int projectID) {
        ArrayList<Task> tasks = taskService.getTasksFromProject(projectID);
        int totalprice = 0;

        for (int i = 0; i < tasks.size(); i++) {
            totalprice += tasks.get(i).getPrice();
        }
        return totalprice;
    }

    public int getTotalHours(int projectID){
        ArrayList<Task> tasks = taskService.getTasksFromProject(projectID);
        int totalHours = 0;

        for (int i = 0; i < tasks.size(); i++) {
            totalHours += tasks.get(i).getTime();
        }
        return totalHours;
    }


}
