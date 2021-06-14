package by.a1qa.klimov.dao;

import by.a1qa.klimov.dao.entity.Test;
import by.a1qa.klimov.dao.impl.TestDao;

public class DataBaseProcessor {
    private TestDao testDao = new TestDao();

    public void initSessionInDb() {

    }

    public long createTest(Test test) {
        return testDao.create(test);
    }
}
