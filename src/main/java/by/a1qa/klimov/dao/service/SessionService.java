package by.a1qa.klimov.dao.service;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Session;
import by.a1qa.klimov.dao.impl.SessionDaoImpl;
import by.a1qa.klimov.dao.interfaces.SessionDao;

import java.util.List;

public class SessionService {
    private SessionDao sessionDao = new SessionDaoImpl();

    public long addSession(Session session) {
        if (session != null) {

            Long id = session.getId();
            if (id != null && sessionDao.read(session.getId()) != null) {
                Logger.getInstance().error("Session with id \"" + id + "\" already exists.");
                return 0;
            }

            List<Session> sessionList = sessionDao.find(session);
            if (sessionList.size() == 0) return sessionDao.create(session);
            return sessionList.get(0).getId();
        } else return 0;
    }
}
