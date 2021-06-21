package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.dao.service.TestService;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.integration.dao.ServiceTestDao;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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


        Assert.assertTrue(true);
    }
}
