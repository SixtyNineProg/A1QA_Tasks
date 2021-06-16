package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Session;
import by.a1qa.klimov.dao.interfaces.SessionDao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SessionDaoImpl implements SessionDao {
    @Override
    public long create(Session session) {
        Logger.getInstance().info("Create " + session);
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
    public List<Session> find(Session session) {
        Logger.getInstance().info("Find " + session);
        try (org.hibernate.Session hibernateSession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
            CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
            Root<Session> myObjectRoot = criteria.from(Session.class);

            Predicate predicate = builder.and(
                    builder.equal(myObjectRoot.get("sessionKey"), session.getSessionKey()),
                    builder.equal(myObjectRoot.get("createdTime"), session.getCreatedTime()),
                    builder.equal(myObjectRoot.get("buildNumber"), session.getBuildNumber())
            );

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Session> query = hibernateSession.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public void update(Session session) {
        Logger.getInstance().info("Update " + session);
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
