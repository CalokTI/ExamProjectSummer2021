package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Project;
import com.examprojectsummer2021.repositories.ProjectRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julius & Anton
 */

public class ProjectService {

    private ProjectRepository projectRepository = new ProjectRepository();

    // ------ SETTERS ------ //

    public void createNewProject(String projectTitle, String projectDescription, String projectOwner, String startDate, String deadline, String[] projectUsers) {
        projectRepository.createNewProject(projectTitle, projectDescription, projectOwner, startDate, deadline);
        linkUserAndProject(projectUsers, projectTitle, projectOwner);
    }

    private void linkUserAndProject(String[] projectUsers, String projectTitle, String projectOwner){
        int projectID = getProjectID(projectTitle, projectOwner);

        for (String s : projectUsers) {
            projectRepository.linkUserAndProject(s, projectID);
        }
    }

    public void changeProjectStatus(int projectID) {

        Project project = getSpecificProject(projectID);

        boolean state;

        if (project.isFinished()){
            state = false;
        } else {
            state = true;
        }

        projectRepository.changeProjectFinished(state, projectID);

    }

    // ------ GETTERS ------ //

    public int getProjectID(String projectTitle, String projectOwner) {
        int projectID = projectRepository.getProjectID(projectTitle, projectOwner);
        return projectID;
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

    public List getAllProjects() {

        List allProjects = new ArrayList();
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


}
