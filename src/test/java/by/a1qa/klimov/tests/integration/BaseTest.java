package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.tests.integration.dao.BaseTestDao;
import org.apache.log4j.BasicConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    public static final String STATUS_PASSED_NAME = "PASSED";
    public static final String STATUS_FAILED_NAME = "FAILED";
    public static final String STATUS_SKIPPED_NAME = "SKIPPED";

    private long statusPassedId = 0;
    private long statusFailedId = 0;
    private long statusSkippedId = 0;
    private long sessionId = 0;
    private long projectId = 0;
    private long authorId = 0;

    private final BaseTestDao baseTestDao = new BaseTestDao();

    @BeforeSuite
    protected void beforeSuite() {
        BasicConfigurator.configure();

        statusPassedId = baseTestDao.createStatus(STATUS_PASSED_NAME);
        statusFailedId = baseTestDao.createStatus(STATUS_FAILED_NAME);
        statusSkippedId = baseTestDao.createStatus(STATUS_SKIPPED_NAME);

        sessionId = baseTestDao.createSession();
        projectId = baseTestDao.createProject();
        authorId = baseTestDao.createAuthor();
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        long statusId;
        switch (result.getStatus()) {
            case 1:
                statusId = statusPassedId;
                break;
            case 3:
                statusId = statusSkippedId;
                break;
            default:
                statusId = statusFailedId;
                break;
        }
        baseTestDao.createRecordTest(statusId, sessionId, projectId, authorId, result);
    }
}
