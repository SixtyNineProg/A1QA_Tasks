package by.a1qa.klimov.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Author;
import by.a1qa.klimov.dao.interfaces.Dao;
import by.a1qa.klimov.dao.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

public class AuthorDao implements Dao<Author> {

    @Override
    public long create(Author author) {
        Logger.getInstance().info("Create Author: " + author.toString());
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
    public void update(Author author) {
        Logger.getInstance().info("Update Author: " + author.toString());
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
