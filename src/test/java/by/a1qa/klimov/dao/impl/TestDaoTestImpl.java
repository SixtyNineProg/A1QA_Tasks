package by.a1qa.klimov.dao.impl;

import by.a1qa.klimov.dao.interfaces.TestDao;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class TestDaoTestImpl {
    @Test
    public void crudTest() {
        final by.a1qa.klimov.dao.entity.Test test = new by.a1qa.klimov.dao.entity.Test(
                2L,
                "aaa",
                1L,
                "com",
                4L,
                12L,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "DESKTOP",
                "chrome",
                1L
        );
        TestDao testDao = new TestDaoImpl();
        long id = testDao.create(test);
        Assert.assertNotEquals(id, 0, "Record Test not created.");

//        by.a1qa.klimov.dao.entity.Test test1 = testDao.read(id);
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