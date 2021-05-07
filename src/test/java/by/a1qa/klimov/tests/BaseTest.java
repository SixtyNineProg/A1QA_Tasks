package by.a1qa.klimov.tests;

import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    @BeforeSuite
    protected void beforeSuite() {
        BasicConfigurator.configure();
    }

    @BeforeMethod
    protected void beforeMethod() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @AfterSuite
    protected void afterSuite() {
    }
}
