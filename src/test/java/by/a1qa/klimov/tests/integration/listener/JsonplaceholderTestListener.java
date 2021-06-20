package by.a1qa.klimov.tests.integration.listener;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class JsonplaceholderTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
    }

    @Override
    public void beforeConfiguration(ITestResult tr, ITestNGMethod tm) {
        super.beforeConfiguration(tr, tm);
    }


}
