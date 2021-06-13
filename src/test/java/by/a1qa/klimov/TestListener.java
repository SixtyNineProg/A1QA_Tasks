package by.a1qa.klimov;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.jdbc.model.Test;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.sql.Timestamp;

public class TestListener extends TestListenerAdapter {
    private int m_count = 0;
    @Override
    public void onTestFailure(ITestResult tr) {
        log("F");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("S");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Logger.getInstance().info(tr.getTestName());
        Logger.getInstance().info(tr.getName());
        Logger.getInstance().info(tr.getMethod().getMethodName());
        Timestamp start = new Timestamp(tr.getStartMillis());
        Timestamp end = new Timestamp(tr.getEndMillis());
        log(".Success");
    }

    private void log(String string) {
        System.out.print(string);
    }

}
