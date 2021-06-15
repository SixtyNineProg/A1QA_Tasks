package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Session;
import by.a1qa.klimov.dao.interfaces.Dao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;

public class SessionDao implements Dao<Session> {
    @Override
    public long create(Session session) {
        Logger.getInstance().info("Create Session: " + session.toString());
        try (org.hibernate.Session hibernateSession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            hibernateSession.beginTransaction();
            long id = (long) hibernateSession.save(session);
            hibernateSession.getTransaction().commit();
            return id;
        }
    }

    @Override
    public Session read(long id) {
        Logger.getInstance().info("Read Session with id: " + id);
        try (org.hibernate.Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Session.class, id);
        }
    }

    @Override
    public void update(Session session) {
        Logger.getInstance().info("Update Session: " + session.toString());
        try (org.hibernate.Session hibernateSession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.update(session);
            hibernateSession.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        Logger.getInstance().info("Delete Session with id: " + id);
        try (org.hibernate.Session hibernateSession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.delete(hibernateSession.load(Session.class, id));
            hibernateSession.getTransaction().commit();
        }
    }
}
