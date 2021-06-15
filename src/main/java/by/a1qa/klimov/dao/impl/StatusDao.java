package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.interfaces.Dao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

public class StatusDao implements Dao<Status> {
    @Override
    public long create(Status status) {
        Logger.getInstance().info("Create Status: " + status.toString());
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
    public void update(Status status) {
        Logger.getInstance().info("Update Status: " + status.toString());
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
