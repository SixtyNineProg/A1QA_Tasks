package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Status;

import java.util.List;

public interface StatusDao {
    long create(Status object);

    Status read(long id);

    List<Status> find(Status status);

    List<Status> findByName(String name);

    void update(Status status);

    void delete(long id);
}
