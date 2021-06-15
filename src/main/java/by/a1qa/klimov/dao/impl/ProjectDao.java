package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Project;
import by.a1qa.klimov.dao.interfaces.Dao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

public class ProjectDao implements Dao<Project> {
    @Override
    public long create(Project project) {
        Logger.getInstance().info("Create Project: " + project.toString());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(project);
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public Project read(long id) {
        Logger.getInstance().info("Read Project with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Project.class, id);
        }
    }

    @Override
    public void update(Project project) {
        Logger.getInstance().info("Update Project: " + project.toString());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(project);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        Logger.getInstance().info("Delete Project with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.load(Project.class, id));
            session.getTransaction().commit();
        }
    }
}
