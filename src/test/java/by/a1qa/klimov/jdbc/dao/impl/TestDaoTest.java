package by.a1qa.klimov.jdbc.dao.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class TestDaoTest {
    @Test
    public void CRUDTest() {
        final by.a1qa.klimov.jdbc.model.Test test = new by.a1qa.klimov.jdbc.model.Test(
                2L,
                "aaa",
                1,
                "com",
                4,
                12,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "DESKTOP",
                "chrome",
                1
        );
        TestDao testDao = new TestDao();
        long id = testDao.create(test);
        Assert.assertNotEquals(id, 0);

        by.a1qa.klimov.jdbc.model.Test test1 = testDao.read(id);
        Assert.assertEquals(test1.getName(), test.getName());

        String newName = "Razer";
        test1.setName(newName);
        Assert.assertEquals(testDao.update(test1), 1);
        test1 = testDao.read(id);
        Assert.assertEquals(test1.getName(), newName);

        Assert.assertEquals(testDao.delete(id), 1);
    }
}