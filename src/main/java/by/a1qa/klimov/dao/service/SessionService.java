package by.a1qa.klimov.dao.service;

import by.a1qa.klimov.dao.entity.Session;
import by.a1qa.klimov.dao.impl.SessionDaoImpl;
import by.a1qa.klimov.dao.interfaces.SessionDao;

import java.util.List;

public class SessionService {
    private SessionDao sessionDao = new SessionDaoImpl();

    public long addSession(Session session) {
        if (session != null) {
            List<Session> sessionList = sessionDao.find(session);
            if (sessionList.size() == 0) return sessionDao.create(session);
            return sessionList.get(0).getId();
        } else return 0;
    }
}
