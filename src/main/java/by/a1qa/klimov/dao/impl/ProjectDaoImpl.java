package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Project;
import by.a1qa.klimov.dao.interfaces.ProjectDao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
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
    public List<Project> find(Project project) {
        Logger.getInstance().info("Find Project: " + project.toString());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
            Root<Project> myObjectRoot = criteria.from(Project.class);

            Predicate predicate = builder.equal(myObjectRoot.get("name"), project.getName());

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Project> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public List<Project> findByName(String name) {
        Logger.getInstance().info("Find Project by name: " + name);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
            Root<Project> myObjectRoot = criteria.from(Project.class);

            Predicate predicate = builder.equal(myObjectRoot.get("name"), name);

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Project> query = session.createQuery(criteria);
            return query.getResultList();
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
