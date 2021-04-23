package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Image;
import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class HoverMainPage extends BaseForm {
    public static final String HREF_ATTRIBUTE = "href";

    public HoverMainPage(By locator, String name) {
        super(new Image(locator, name));
    }

    public void moveToUserImage(String userName) {
        getUserImage(userName).moveToElement();
    }

    public String getTextLabelUserName(String userName) {
        return getLabelUserName(userName).getText();
    }

    public String getUserHref(String userName) {
        return getLabelUserHref(userName).getAttribute(HREF_ATTRIBUTE);
    }

    public boolean atPage() {
        return isOpened();
    }

    public boolean isDisplayedUserHref(String userName) {
        return getLabelUserHref(userName).isDisplayed();
    }

    private Image getUserImage(String userName) {
        return new Image(By.xpath(
                "//*[contains(text(),'name:') and contains(text(),'" + userName + "')]/ancestor::div[@class='figure']"
        ), "User image.");
    }

    private Label getLabelUserName(String userName) {
        return new Label(By.xpath(
                "//div[@class='figcaption']//*[contains(text(),'name:') and contains(text(),'" + userName + "')]"
        ), "Label user name.");
    }

    private Label getLabelUserHref(String userName) {
        return new Label(By.xpath(
                "//*[contains(text(),'name:') and contains(text(),'" + userName + "')]/ancestor::div[@class='figcaption']//a[@href]"
        ), "Label user href.");
    }
}
