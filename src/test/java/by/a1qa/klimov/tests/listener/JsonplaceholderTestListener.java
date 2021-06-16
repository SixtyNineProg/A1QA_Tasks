package by.a1qa.klimov.tests.listener;

import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.klimov.dao.entity.*;
import by.a1qa.klimov.dao.service.*;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.sql.Timestamp;

public class JsonplaceholderTestListener extends TestListenerAdapter {
    private final JsonSettingsFile jsonSettingsFile = new JsonSettingsFile("settings.json");

    private final StatusService statusService = new StatusService();
    private final SessionService sessionService = new SessionService();
    private final ProjectService projectService = new ProjectService();
    private final AuthorService authorService = new AuthorService();
    private final TestService testService = new TestService();

    public static final String STATUS_PASSED_NAME = "PASSED";
    public static final String STATUS_FAILED_NAME = "FAILED";
    public static final String STATUS_SKIPPED_NAME = "SKIPPED";

    private long statusPassedId = 0;
    private long statusFailedId = 0;
    private long statusSkippedId = 0;
    private long sessionId = 0;
    private long projectId = 0;
    private long authorId = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        createRecordTest(statusFailedId, tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        createRecordTest(statusSkippedId, tr);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        createRecordTest(statusPassedId, tr);
    }

    @Override
    public void beforeConfiguration(ITestResult tr, ITestNGMethod tm) {
        super.beforeConfiguration(tr, tm);
        statusPassedId = createStatus(STATUS_PASSED_NAME);
        statusFailedId = createStatus(STATUS_FAILED_NAME);
        statusSkippedId = createStatus(STATUS_SKIPPED_NAME);

        sessionId = createSession();
        projectId = createProject();
        authorId = createAuthor();
    }

    private long createStatus(String name) {
        long statusId = statusService.addStatus(new Status(null, name));
        if (statusId == 0) throw new NullPointerException("Status " + name + "not created.");
        return statusId;
    }

    private long createSession() {
        Session session = new Session(null,
                jsonSettingsFile.getValue("/sessionKey").toString(),
                new Timestamp(System.currentTimeMillis()),
                Long.valueOf(jsonSettingsFile.getValue("/buildNumber").toString()));
        long sessionId = sessionService.addSession(session);
        if (sessionId == 0) throw new NullPointerException(session + " not created.");
        return sessionId;
    }

    private long createProject() {
        Project project = new Project(null, jsonSettingsFile.getValue("/projectName").toString());
        long projectId = projectService.addProject(project);
        if (projectId == 0) throw new NullPointerException(project + " not created.");
        return projectId;
    }

    private long createAuthor() {
        Author author = new Author(
                null,
                jsonSettingsFile.getValue("/authorName").toString(),
                jsonSettingsFile.getValue("/authorLogin").toString(),
                jsonSettingsFile.getValue("/authorEmail").toString());
        long authorId = authorService.addAuthor(author);
        if (authorId == 0) throw new NullPointerException(author + " not created.");
        return authorId;
    }

    private void createRecordTest(long statusId, ITestResult tr) {
        Test test = new Test(
                null,
                tr.getName(),
                statusId,
                tr.getMethod().getMethodName(),
                projectId,
                sessionId,
                new Timestamp(tr.getStartMillis()),
                new Timestamp(tr.getEndMillis()),
                System.getenv().get("OS"),
                jsonSettingsFile.getValue("/browserName").toString(),
                authorId
        );
        long testId = testService.addTest(test);
        if (testId == 0) throw new NullPointerException(test + " not created.");
    }
}
