package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserinyerfaceGame2Form extends Form {

    private final IButton buttonUploadAvatar = getElementFactory()
            .getButton(By.cssSelector(".avatar-and-interests__upload-button"), "Button upload avatar");

    private final ILabel labelUnselectAll = getElementFactory()
            .getLabel(By.xpath("//label[@for='interest_unselectall']"), "CheckBox unselect all");

    private final ILabel labelInterestSquares = getElementFactory()
            .getLabel(By.xpath("//label[@for='interest_squares']"), "CheckBox interest_squares");

    private final ILabel labelInterestPolo = getElementFactory()
            .getLabel(By.xpath("//label[@for='interest_polo']"), "CheckBox interest polo");

    private final ILabel labelInterestDough = getElementFactory()
            .getLabel(By.xpath("//label[@for='interest_dough']"), "CheckBox interest dough");

    private final IButton buttonNext = getElementFactory()
            .getButton(By.xpath("//button[contains(text(),'Next')]"), "Button upload avatar");


    public UserinyerfaceGame2Form() {
        super(By.cssSelector(".avatar-and-interests-page"), "This is me container");
    }

    public void buttonUploadAvatarClick() {
        buttonUploadAvatar.click();
    }

    public void chooseThreeInterest() {
        labelUnselectAll.click();
        labelInterestSquares.click();
        labelInterestPolo.click();
        labelInterestDough.click();
    }

    public void buttonNextClick() {
        buttonNext.click();
    }
}
