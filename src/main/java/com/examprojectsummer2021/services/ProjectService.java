package com.examprojectsummer2021.services;

import com.examprojectsummer2021.models.Project;
import com.examprojectsummer2021.repositories.ProjectRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julius
 * Time: 14.05
 * Date: 10/05/2021
 */
public class ProjectService {

    private ProjectRepository projectRepository = new ProjectRepository();


    public void createNewProject(String projectTitle, String projectDescription, String owner){
        projectRepository.createNewProject(projectTitle,projectDescription,owner);
    }


    public Project getSpecificProject(int projectID){
        ResultSet resultSet = projectRepository.getSpecificProjectFromDatabase(projectID);
        Project project = null;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                project = new Project(title, description, id);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return project;
    }



    public List getAllProjects() {

        List allProjects = new ArrayList();
        ResultSet resultSet = projectRepository.getAllProjects();

        try {
            while (resultSet.next()) {
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                int projectID = resultSet.getInt(1);
                Project project = new Project(title, description, projectID);
                allProjects.add(project);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("ProjectService - getAllProjects");
        }
        return allProjects;
    }


}
