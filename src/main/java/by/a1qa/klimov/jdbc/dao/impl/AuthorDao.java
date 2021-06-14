package by.a1qa.klimov.jdbc.dao.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.jdbc.dao.interfaces.Dao;
import by.a1qa.klimov.jdbc.dao.util.HibernateSessionFactoryUtil;
import by.a1qa.klimov.jdbc.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.Optional;

public class AuthorDao implements Dao<Author> {
    private static final String TABLE_NAME = "AUTHOR";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long create(Author author) {
        long id = 0;
        return id;
    }

    @Override
    public Author read(long id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Author.class, id);
    }

    @Override
    public int update(Author object) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
