package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class UserPage extends BaseForm {
    private static final String XPATH_LABEL_USER_NAME = "//*[contains(text(),'user1')]";

    private Label labelUserName;

    public UserPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public boolean isUserPage(String userName) {
        return getLabelUserName(userName).isDisplayed();
    }

    private Label getLabelUserName(String userName) {
        return labelUserName == null ?
                labelUserName = new Label(By.xpath(getXpathLabelUserName(userName)), "Label user href.") :
                labelUserName;
    }

    private String getXpathLabelUserName(String userName) {
        return "//*[contains(text(),'" + userName + "')]";
    }
}
