package by.a1qa.klimov.tests.integration.dao;

import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.klimov.dao.entity.*;
import by.a1qa.klimov.dao.service.*;
import org.testng.ITestResult;

import java.sql.Timestamp;

public class ServiceTestDao {
    private final JsonSettingsFile jsonSettingsFile = new JsonSettingsFile("settings.json");

    private final StatusService statusService = new StatusService();
    private final SessionService sessionService = new SessionService();
    private final ProjectService projectService = new ProjectService();
    private final AuthorService authorService = new AuthorService();
    private final TestService testService = new TestService();

    public long createStatus(String name) {
        long statusId = statusService.addStatus(new Status(null, name));
        if (statusId == 0) throw new NullPointerException("Status " + name + "not created.");
        return statusId;
    }

    public long createSession() {
        Session session = new Session(null,
                jsonSettingsFile.getValue("/sessionKey").toString(),
                new Timestamp(System.currentTimeMillis()),
                Long.valueOf(jsonSettingsFile.getValue("/buildNumber").toString()));
        long sessionId = sessionService.addSession(session);
        if (sessionId == 0) throw new NullPointerException(session + " not created.");
        return sessionId;
    }

    public long createProject() {
        Project project = new Project(null, jsonSettingsFile.getValue("/projectName").toString());
        long projectId = projectService.addProject(project);
        if (projectId == 0) throw new NullPointerException(project + " not created.");
        return projectId;
    }

    public long createAuthor() {
        Author author = new Author(
                null,
                jsonSettingsFile.getValue("/authorName").toString(),
                jsonSettingsFile.getValue("/authorLogin").toString(),
                jsonSettingsFile.getValue("/authorEmail").toString());
        long authorId = authorService.addAuthor(author);
        if (authorId == 0) throw new NullPointerException(author + " not created.");
        return authorId;
    }

    public void createRecordTest(long statusId, long sessionId, long projectId, long authorId, ITestResult tr) {
        long testId = 0;
        if (sessionId != 0 && projectId != 0 && authorId != 0) {
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
            testId = testService.addTest(test);
        }
        if (testId == 0) throw new NullPointerException("Test record not created.");
    }
}
