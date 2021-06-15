package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Status;

public interface StatusDao {
    long create(Status object);

    Status read(long id);

    Status find(Status status);

    void update(Status status);

    void delete(long id);
}
