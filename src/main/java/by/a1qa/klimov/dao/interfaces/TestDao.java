package by.a1qa.klimov.dao.interfaces;

import by.a1qa.klimov.dao.entity.Test;

import java.util.List;

public interface TestDao {
    long create(Test test);

    Test read(long id);

    List<Test> find(Test test);

    void update(Test test);

    void delete(long id);

    List<Test> readByIdPattern(String pattern);
}
