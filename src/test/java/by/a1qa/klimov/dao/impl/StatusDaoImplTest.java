package by.a1qa.klimov.dao.impl;

import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.interfaces.StatusDao;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class StatusDaoImplTest {
    private final StatusDao statusDao = new StatusDaoImpl();

    @Test
    public void crudTest() {
        final Status status = new Status(
                null,
                "PASSED");
        long id = statusDao.create(status);
        Assert.assertNotEquals(id, 0, "Record Status not created.");

        Status status1 = statusDao.read(id);
        Assert.assertNotNull(status1, "Record Status not read.");

        Assert.assertNotEquals(statusDao.find(status1), 0, "Record Status not found.");

        String newName = "FAILED";
        status1.setName(newName);
        statusDao.update(status1);
        List<Status> statusList = statusDao.findByName(newName);
        Assert.assertNotEquals(statusList.size(), 0);
        status1 = statusList.get(0);
        Assert.assertEquals(status1.getName(), newName, "Data doesn't match after update.");

        statusDao.delete(id);
        status1 = statusDao.read(id);
        Assert.assertNull(status1, "Record Status not deleted.");
    }
}