package by.a1qa.klimov.tests;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import by.a1qa.klimov.theinternet.TheInternetPage;
import by.a1qa.klimov.utils.FileUploader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected final IElementFactory elementFactory;

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
    }

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getBrowser().maximize();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected void navigate(TheInternetPage page) {
        getBrowser().goTo(page.getAddress());
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
