package by.a1qa.klimov.tests;

import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    @BeforeSuite
    protected void beforeSuite() {
        BasicConfigurator.configure();
    }
}
