package by.a1qa.klimov.jdbc.dao.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class TestDaoTest {
    @Test
    public void CrudTest() {
        final by.a1qa.klimov.jdbc.entity.Test test = new by.a1qa.klimov.jdbc.entity.Test(
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
        Assert.assertNotEquals(id, 0, "Record Test not created.");

//        by.a1qa.klimov.jdbc.entity.Test test1 = testDao.read(id);
//        Assert.assertEquals(test1.getName(), test.getName(), "Record Test not read.");
//
//        String newName = "Razer";
//        test1.setName(newName);
//        Assert.assertEquals(testDao.update(test1), 1, "Record test not updated.");
//        test1 = testDao.read(id);
//        Assert.assertEquals(test1.getName(), newName, "Name doesn't match after update.");

//        Assert.assertEquals(testDao.delete(id), 1, "Record Test not deleted.");
    }
}