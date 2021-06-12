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

        Test test = new Test(
                null,
                "KS-5437 PSS0202",
                1,
                "com.nexage.tests",
                1,
                3,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                "GRIGORYEVS",
                "chrome",
                1
        );
        TestDao testDao = new TestDao();
        Long id = testDao.create(test);
        System.out.println(id);
    }

    @AfterMethod
    protected void writeResultTest() {

    }

    @AfterSuite
    protected void afterSuite() throws SQLException {
        Connection connection = ConnectionDb.getConnection();
        if (connection != null) connection.close();
    }
}
