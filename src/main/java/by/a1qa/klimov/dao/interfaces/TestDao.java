package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Test;

public interface TestDao {
    long create(Test test);

    Test read(long id);

    void update(Test test);

    void delete(long id);
}
