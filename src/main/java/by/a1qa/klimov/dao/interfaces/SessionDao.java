package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Session;

import java.util.List;

public interface SessionDao {
    long create(Session session);

    Session read(long id);

    List<Session> find(Session session);

    void update(Session session);

    void delete(long id);
}
