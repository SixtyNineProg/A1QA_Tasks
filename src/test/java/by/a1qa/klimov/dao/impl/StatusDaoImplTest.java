package by.a1qa.klimov.dao.impl;

import by.a1qa.klimov.dao.entity.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusDaoImplTest {
    private final StatusDaoImpl statusDaoImpl = new StatusDaoImpl();

    @Test
    public void crudTest() {
        final Status status = new Status(
                null,
                "PASSED");
        long id = statusDaoImpl.create(status);
        Assert.assertNotEquals(id, 0, "Record Status not created.");

        Status status1 = statusDaoImpl.read(id);
        Assert.assertNotNull(status1, "Record Status not read.");

        status1 = statusDaoImpl.find(status1);
        Assert.assertNotNull(status1, "Record Status not found.");

        String newName = "FAILED";
        status1.setName(newName);
        statusDaoImpl.update(status1);
        status1 = statusDaoImpl.read(id);
        Assert.assertEquals(status1.getName(), newName, "Data doesn't match after update.");

        statusDaoImpl.delete(id);
        status1 = statusDaoImpl.read(id);
        Assert.assertNull(status1, "Record Status not deleted.");
    }
}