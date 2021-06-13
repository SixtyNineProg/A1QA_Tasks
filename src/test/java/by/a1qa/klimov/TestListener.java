package by.a1qa.klimov;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.jdbc.model.Test;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

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
        Logger.getInstance().info(String.valueOf(Test.TEST_STATUS_PASSED));
        Logger.getInstance().info(tr.getMethod().getMethodName());
        log(".Success");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

}
