package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Project;

import java.util.List;

public interface ProjectDao {
    long create(Project project);

    Project read(long id);

    List<Project> find(Project project);

    List<Project> findByName(String name);

    void update(Project project);

    void delete(long id);
}
