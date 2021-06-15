package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Author;

import java.util.List;

public interface AuthorDao {
    long create(Author author);

    Author read(long id);

    List<Author> find(Author author);

    List<Author> findByLogin(String login);

    void update(Author author);

    void delete(long id);
}
