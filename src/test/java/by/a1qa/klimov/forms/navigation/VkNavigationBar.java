package by.a1qa.klimov.forms.navigation;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VkNavigationBar extends Form {

    private final IButton buttonMyPage = getElementFactory()
            .getButton(By.id("l_pr"), "My page button");

    public VkNavigationBar() {
        super(By.xpath("//div[@id = 'side_bar_inner']/nav"), "Navigation bar");
    }

    public void clickMyPageButton() {
        buttonMyPage.click();
    }
}
