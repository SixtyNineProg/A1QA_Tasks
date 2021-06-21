package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.interfaces.StatusDao;
import by.a1qa.klimov.dao.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class StatusDaoImpl implements StatusDao {
    @Override
    public long create(Status status) {
        Logger.getInstance().info("Create " + status);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(status);
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public Status read(long id) {
        Logger.getInstance().info("Read Status with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Status.class, id);
        }
    }

    @Override
    public List<Status> find(Status status) {
        Logger.getInstance().info("Find " + status);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
            Root<Status> myObjectRoot = criteria.from(Status.class);

            Predicate predicate = builder.equal(myObjectRoot.get("name"), status.getName());

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Status> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public List<Status> findByName(String name) {
        Logger.getInstance().info("Read Status with name: " + name);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
            Root<Status> myObjectRoot = criteria.from(Status.class);

            Predicate predicate = builder.equal(myObjectRoot.get("name"), name);
            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Status> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public void update(Status status) {
        Logger.getInstance().info("Update " + status);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(status);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        Logger.getInstance().info("Delete Status with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.load(Status.class, id));
            session.getTransaction().commit();
        }
    }
}
