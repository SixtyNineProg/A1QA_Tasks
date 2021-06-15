package by.a1qa.klimov.dao.impl;

import by.a1qa.klimov.dao.entity.Project;
import by.a1qa.klimov.dao.interfaces.ProjectDao;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectDaoImplTest {
    private final ProjectDao projectDao = new ProjectDaoImpl();

    @Test
    public void crudTest() {
        final Project project = new Project(
                null,
                "MyProject");
        long id = projectDao.create(project);
        Assert.assertNotEquals(id, 0, "Record Project not created.");

        Project project1 = projectDao.read(id);
        Assert.assertNotNull(project1, "Record Project not read.");

        String newName = "NewProject";
        project1.setName(newName);
        projectDao.update(project1);
        project1 = projectDao.read(id);
        Assert.assertEquals(project1.getName(), newName, "Data doesn't match after update.");

        projectDao.delete(id);
        project1 = projectDao.read(id);
        Assert.assertNull(project1, "Record Project not deleted.");
    }
}