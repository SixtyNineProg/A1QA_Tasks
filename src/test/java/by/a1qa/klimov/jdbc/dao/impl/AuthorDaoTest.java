package by.a1qa.klimov.jdbc.dao.impl;

import by.a1qa.klimov.jdbc.entity.Author;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorDaoTest {
    private final AuthorDao authorDao = new AuthorDao();

    @Test
    public void crudTest() {
        final Author author = new Author(
                null,
                "Dmitry",
                "Sixty",
                "d@email");
        long id = authorDao.create(author);
        Assert.assertNotEquals(id, 0, "Record Author not created.");

        Author author1 = authorDao.read(id);
        Assert.assertNotNull(author1, "Record Author not read.");

        String newName = "Sasha";
        author1.setName(newName);
        authorDao.update(author1);
        author1 = authorDao.read(id);
        Assert.assertEquals(author1.getName(), newName, "Data doesn't match after update.");

        authorDao.delete(id);
        author1 = authorDao.read(id);
        Assert.assertNull(author1, "Record Author not deleted.");
    }
}