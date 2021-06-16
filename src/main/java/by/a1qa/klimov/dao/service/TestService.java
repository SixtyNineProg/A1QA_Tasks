package by.a1qa.klimov.dao.service;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Test;
import by.a1qa.klimov.dao.impl.TestDaoImpl;
import by.a1qa.klimov.dao.interfaces.TestDao;

import java.util.List;

public class TestService {
    private TestDao testDao = new TestDaoImpl();

    public long addTest(Test test) {
        if (test != null) {

            Long id = test.getId();
            if (id != null && testDao.read(test.getId()) != null) {
                Logger.getInstance().error("Test with id \"" + id + "\" already exists.");
                return 0;
            }

            List<Test> testList = testDao.find(test);
            if (testList.size() == 0) return testDao.create(test);
            return testList.get(0).getId();
        } else return 0;
    }
}
