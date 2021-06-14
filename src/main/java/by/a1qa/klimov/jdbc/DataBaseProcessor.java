package by.a1qa.klimov.jdbc;

import by.a1qa.klimov.jdbc.dao.impl.TestDao;
import by.a1qa.klimov.jdbc.entity.Test;

public class DataBaseProcessor {
    private TestDao testDao = new TestDao();

    public void initSessionInDb() {

    }

    public long createTest(Test test) {
        return testDao.create(test);
    }
}
