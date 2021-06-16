package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Test;
import by.a1qa.klimov.dao.interfaces.TestDao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TestDaoImpl implements TestDao {

    @Override
    public long create(Test test) {
        Logger.getInstance().info("Create " + test);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(test);
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public Test read(long id) {
        Logger.getInstance().info("Read Test with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Test.class, id);
        }
    }

    @Override
    public List<Test> find(Test test) {
        Logger.getInstance().info("Find " + test);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Test> criteria = builder.createQuery(Test.class);
            Root<Test> myObjectRoot = criteria.from(Test.class);

            Predicate predicate = builder.and(
                    builder.equal(myObjectRoot.get("name"), test.getName()),
                    builder.equal(myObjectRoot.get("statusId"), test.getStatusId()),
                    builder.equal(myObjectRoot.get("methodName"), test.getMethodName()),
                    builder.equal(myObjectRoot.get("projectId"), test.getProjectId()),
                    builder.equal(myObjectRoot.get("sessionId"), test.getSessionId()),
                    builder.equal(myObjectRoot.get("startTime"), test.getStartTime()),
                    builder.equal(myObjectRoot.get("endTime"), test.getEndTime()),
                    builder.equal(myObjectRoot.get("env"), test.getEnv()),
                    builder.equal(myObjectRoot.get("browser"), test.getBrowser()),
                    builder.equal(myObjectRoot.get("authorId"), test.getAuthorId())
            );

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Test> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public void update(Test test) {
        Logger.getInstance().info("Update " + test);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(test);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        Logger.getInstance().info("Delete Test with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.load(Test.class, id));
            session.getTransaction().commit();
        }
    }
}

