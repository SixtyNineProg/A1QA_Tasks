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

public abstract class BaseTest {
    @BeforeSuite
    protected void beforeSuite() {
        BasicConfigurator.configure();
    }

    @AfterMethod
    protected void writeResultTest() {
        TestDao testDao = new TestDao();
        Test test = testDao.read(300);
        System.out.println("hello");
    }

    @AfterSuite
    protected void afterSuite() throws SQLException {
        Connection connection = ConnectionDb.getConnection();
        if (connection != null) connection.close();
    }
}
