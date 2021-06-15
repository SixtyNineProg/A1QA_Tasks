package by.a1qa.klimov.tests.listener;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.entity.Author;
import by.a1qa.klimov.dao.entity.Project;
import by.a1qa.klimov.dao.entity.Session;
import by.a1qa.klimov.dao.entity.Status;
import by.a1qa.klimov.dao.service.AuthorService;
import by.a1qa.klimov.dao.service.ProjectService;
import by.a1qa.klimov.dao.service.SessionService;
import by.a1qa.klimov.dao.service.StatusService;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.sql.Timestamp;
import java.util.Map;

public class JsonplaceholderTestListener extends TestListenerAdapter {
    private StatusService statusService = new StatusService();
    private SessionService sessionService = new SessionService();
    private ProjectService projectService = new ProjectService();
    private AuthorService authorService = new AuthorService();

    private long statusPassedId;
    private long statusFailedId;
    private long statusSkippedId;
    private long sessionId;
    private long projectId;
    private long authorId;

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
        Map<String, String> map = System.getenv();
        log(".Success");
    }

    private void log(String string) {
        System.out.print(string);
    }

    @Override
    public void beforeConfiguration(ITestResult tr, ITestNGMethod tm) {
        super.beforeConfiguration(tr, tm);
        statusPassedId = statusService.addStatus(new Status(null, "PASSED"));
        statusFailedId = statusService.addStatus(new Status(null, "FAILED"));
        statusSkippedId = statusService.addStatus(new Status(null, "SKIPPED"));
        sessionId = sessionService.addSession(new Session(null,
                "1476293769538",
                java.sql.Timestamp.valueOf("2016-10-12 20:36:26"),
                1L));
        projectId = projectService.addProject(new Project(null, "MyProject"));
        authorId = authorService.addAuthor(new Author(null, "Dmitry", "SixtyN", "@mail"));
    }
}
