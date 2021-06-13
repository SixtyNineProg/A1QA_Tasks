package by.a1qa.klimov.tests;

import by.a1qa.klimov.jdbc.connection.ConnectionDb;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseTest {
    @BeforeSuite
    protected void beforeSuite() {
        BasicConfigurator.configure();
    }

    @AfterSuite
    protected void afterSuite() throws SQLException {
        Connection connection = ConnectionDb.getConnection();
        if (connection != null) connection.close();
    }
}
