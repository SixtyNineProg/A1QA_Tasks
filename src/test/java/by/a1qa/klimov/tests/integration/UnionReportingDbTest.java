package by.a1qa.klimov.tests.integration;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.dao.service.TestService;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.integration.dao.ServiceTestDao;
import by.a1qa.klimov.utils.TestsSimulations;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class UnionReportingDbTest {
    private final TestService testService = new TestService();

    @Test
    public void testWorkWithEntity() {
        List<by.a1qa.klimov.dao.entity.Test> tests = testService.readByIdWithPattern(
                DataProperties.getDataPropertyByKey("searchByIdPattern"));
        Assert.assertNotEquals(tests.size(), 0, "Tests not found.");

        ServiceTestDao serviceTestDao = new ServiceTestDao();
        long authorId = serviceTestDao.createAuthor();
        long projectId = serviceTestDao.createProject();

        by.a1qa.klimov.dao.entity.Test.setAllAuthor(tests, authorId);
        by.a1qa.klimov.dao.entity.Test.setAllProject(tests, projectId);

        List<Long> beforeCreateIds = by.a1qa.klimov.dao.entity.Test.getIds(tests);
        testService.save(tests);
        List<Long> afterCreateIds = by.a1qa.klimov.dao.entity.Test.getIds(tests);
        Assert.assertNotEquals(beforeCreateIds, afterCreateIds, "Tests not created.");

        TestsSimulations.simulateRunTests(tests);

        by.a1qa.klimov.dao.entity.Test maxDurationTest =
                by.a1qa.klimov.dao.entity.Test.searchTestWithMaxDuration(tests);
        Logger.getInstance().info("Test with the max duration: " + maxDurationTest);

        by.a1qa.klimov.dao.entity.Test minDurationTest =
                by.a1qa.klimov.dao.entity.Test.searchTestWithMinDuration(tests);
        Logger.getInstance().info("Test with the min duration: " + minDurationTest);

        List<Long> ids = by.a1qa.klimov.dao.entity.Test.getIds(tests);
        testService.delete(ids);
        List<by.a1qa.klimov.dao.entity.Test> testsAfterDelete = testService.read(ids);
        Assert.assertTrue(testsAfterDelete.stream().allMatch(Objects::isNull));
    }
}
