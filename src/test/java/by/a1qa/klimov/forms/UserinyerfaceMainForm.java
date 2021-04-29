package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserinyerfaceMainForm extends Form {

    private final ILink startLink = getElementFactory().getLink(By.cssSelector(".start__link"),"Start link");

    public UserinyerfaceMainForm() {
        super(By.cssSelector("#app button[class=start__button]"), "Button");
    }

    public void startLinkClick() {
        startLink.click();
    }

}
