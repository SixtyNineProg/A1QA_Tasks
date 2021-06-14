package by.a1qa.klimov.jdbc.dao.impl;

import by.a1qa.klimov.jdbc.entity.Author;
import org.testng.annotations.Test;

import java.util.Optional;

public class AuthorDaoTest {
    private final AuthorDao authorDao = new AuthorDao();

    @Test
    public void testRead() {
        Author author = authorDao.read(1);
        System.out.println(author.toString());
    }
}