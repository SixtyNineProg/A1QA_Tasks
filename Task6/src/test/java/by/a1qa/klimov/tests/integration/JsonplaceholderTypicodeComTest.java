package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.APIUtils;
import by.a1qa.klimov.utils.NetworkUtils;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Log4j
public class JsonplaceholderTypicodeComTest extends BaseTest {

    @Test
    public void testForms() {
        URL formsUrl;
        try {
            formsUrl = NetworkUtils.createAllPostsUrl();
        } catch (MalformedURLException e) {
            log.error("Url doesn't create.", e);
            throw new NullPointerException("Url doesn't create.");
        }

        String jsonForms = APIUtils.doRequest(
                formsUrl,
                "GET",
                "application/x-www-form-urlencoded",
                "application/json");


    }
}
