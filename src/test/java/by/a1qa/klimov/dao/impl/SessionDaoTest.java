package by.a1qa.klimov.dao.impl;

import by.a1qa.klimov.dao.entity.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class SessionDaoTest {
    private final SessionDao sessionDao = new SessionDao();

    @Test
    public void crudTest() {
        final Session session = new Session(
                null,
                "SessionKey",
                new Timestamp(System.currentTimeMillis()),
                1111L);
        long id = sessionDao.create(session);
        Assert.assertNotEquals(id, 0, "Record Session not created.");

        Session session1 = sessionDao.read(id);
        Assert.assertNotNull(session1, "Record Session not read.");

        String newSessionKey = "NewSessionKey";
        session1.setSessionKey(newSessionKey);
        sessionDao.update(session1);
        session1 = sessionDao.read(id);
        Assert.assertEquals(session1.getSessionKey(), newSessionKey, "Data doesn't match after update.");

        sessionDao.delete(id);
        session1 = sessionDao.read(id);
        Assert.assertNull(session1, "Record Session not deleted.");
    }
}