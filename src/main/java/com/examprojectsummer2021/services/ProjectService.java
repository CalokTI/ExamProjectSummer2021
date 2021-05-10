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

    public List getAllProjects() {

        List allProjects = new ArrayList();
        ResultSet resultSet = projectRepository.getAllProjects();

        try {
            while (resultSet.next()) {
                String title = resultSet.getString(1);
                String description = resultSet.getString(2);
                int projectID = resultSet.getInt(3);
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
