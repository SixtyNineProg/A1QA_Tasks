package by.a1qa.klimov.dao.service;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Project;
import by.a1qa.klimov.dao.impl.ProjectDaoImpl;
import by.a1qa.klimov.dao.interfaces.ProjectDao;

import java.util.List;

public class ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();

    public long addProject(Project project) {
        if (project != null) {
            Long id = project.getId();
            if (id != null && projectDao.read(project.getId()) != null) {
                Logger.getInstance().error("Project with id \"" + id + "\" already exists.");
                return 0;
            }

            List<Project> projectList = projectDao.find(project);
            if (projectList.size() == 0) {
                projectList = projectDao.findByName(project.getName());
                if (projectList.size() != 0) {
                    Logger.getInstance().error("Project with name \"" + project.getName() + "\" exists.");
                    return 0;
                }
                return projectDao.create(project);
            }
            return projectList.get(0).getId();
        } else return 0;
    }
}
