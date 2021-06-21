package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Author;
import by.a1qa.klimov.dao.interfaces.AuthorDao;
import by.a1qa.klimov.dao.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public long create(Author author) {
        Logger.getInstance().info("Create " + author);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(author);
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public Author read(long id) {
        Logger.getInstance().info("Read Author with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        }
    }

    @Override
    public List<Author> find(Author author) {
        Logger.getInstance().info("Find " + author);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> criteria = builder.createQuery(Author.class);
            Root<Author> myObjectRoot = criteria.from(Author.class);

            Predicate predicate = builder.and(
                    builder.equal(myObjectRoot.get("name"), author.getName()),
                    builder.equal(myObjectRoot.get("login"), author.getLogin()),
                    builder.equal(myObjectRoot.get("email"), author.getEmail())
            );

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Author> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public List<Author> findByLogin(String login) {
        Logger.getInstance().info("Find Author with login: " + login);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> criteria = builder.createQuery(Author.class);
            Root<Author> myObjectRoot = criteria.from(Author.class);

            Predicate predicate = builder.equal(myObjectRoot.get("login"), login);

            criteria.select(myObjectRoot).where(predicate);

            TypedQuery<Author> query = session.createQuery(criteria);
            return query.getResultList();
        }
    }

    @Override
    public void update(Author author) {
        Logger.getInstance().info("Update " + author);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(author);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        Logger.getInstance().info("Delete Author with id: " + id);
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.load(Author.class, id));
            session.getTransaction().commit();
        }
    }
}
