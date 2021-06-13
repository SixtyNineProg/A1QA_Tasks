package by.a1qa.klimov.tests;

import by.a1qa.klimov.jdbc.connection.ConnectionDb;
import by.a1qa.klimov.jdbc.dao.impl.TestDao;
import by.a1qa.klimov.jdbc.model.Test;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class BaseTest {
    @BeforeSuite
    protected void beforeSuite() {
        BasicConfigurator.configure();
    }

    @AfterMethod
    protected void writeResultTest() {
        Test test = new Test(
                2L,
                "aaa",
                1,
                "com",
                4,
                12,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "DESKTOP",
                "chrome",
                1
        );
        TestDao testDao = new TestDao();
        Long id = testDao.update(test);
        //Test test = testDao.read(300);
        System.out.println(id);
    }

    @AfterSuite
    protected void afterSuite() throws SQLException {
        Connection connection = ConnectionDb.getConnection();
        if (connection != null) connection.close();
    }
}
