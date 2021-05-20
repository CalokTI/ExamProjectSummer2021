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


        // owner
        //String projectOwner = "jowa69"; //todo fix dette

        // isFinished
        boolean status = true; //todo fix dette

        /*// inception //todo fix dette (gør det pænere somehow)
        String inceptionDate;
        String pattern = "dd-MM-yy";
        DateFormat df = new SimpleDateFormat(pattern);

        inceptionDate = df.format(new Date().getTime());*/


        projectRepository.createNewProject(projectTitle, projectDescription, projectOwner, startDate, deadline, status);
        linkUserAndProject(projectUsers, projectTitle, projectOwner);
    }

    private void linkUserAndProject(String[] projectUsers, String projectTitle, String projectOwner){
        int projectID = getProjectID(projectTitle, projectOwner);

        for (String s : projectUsers) {
            projectRepository.linkUserAndProject(s, projectID);
        }
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
                String inceptionDate = resultSet.getString(5);
                String deadline = resultSet.getString(6);
                boolean isFinished = resultSet.getBoolean(7);
                project = new Project(id, title, description, inceptionDate, deadline, isFinished);
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
                String inceptionDate = resultSet.getString(5);
                String deadline = resultSet.getString(6);
                boolean isFinished = resultSet.getBoolean(7);
                project = new Project(id, title, description, inceptionDate, deadline, isFinished);
                allProjects.add(project);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectService - getAllProjects");
        }
        return allProjects;
    }


}
