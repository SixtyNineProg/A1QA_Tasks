package by.a1qa.klimov.dao.impl;

import by.a1qa.klimov.dao.entity.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusDaoTest {
    private final StatusDao statusDao = new StatusDao();

    @Test
    public void crudTest() {
        final Status status = new Status(
                null,
                "PASSED");
        long id = statusDao.create(status);
        Assert.assertNotEquals(id, 0, "Record Status not created.");

        Status status1 = statusDao.read(id);
        Assert.assertNotNull(status1, "Record Status not read.");

        String newName = "FAILED";
        status1.setName(newName);
        statusDao.update(status1);
        status1 = statusDao.read(id);
        Assert.assertEquals(status1.getName(), newName, "Data doesn't match after update.");

        statusDao.delete(id);
        status1 = statusDao.read(id);
        Assert.assertNull(status1, "Record Status not deleted.");
    }
}