package by.a1qa.klimov.forms;

import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.utils.Randomizer;
import org.openqa.selenium.By;

import java.util.List;

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

    private final List<Label> interests = getElementFactory()
            .findElements(By.cssSelector("label[for*='interest']"), Label.class);


    public UserinyerfaceGame2Form() {
        super(By.cssSelector(".avatar-and-interests-page"), "This is me container");
    }

    public void buttonUploadAvatarClick() {
        buttonUploadAvatar.click();
    }

    public void chooseInterests(int numInterests) {
        labelUnselectAll.click();
        if (interests.size() >= numInterests + 2) {
            for (int i = 0; i < 3;) {
                int randomNum = Randomizer.generateRandomNumFromRange(0, interests.size() - 1);
                Label interest = interests.get(randomNum);
                String attribute = interest.getAttribute("htmlFor");
                if (!attribute.equals("interest_unselectall") && !attribute.equals("interest_selectall")) {
                    interests.get(randomNum).click();
                    interests.remove(randomNum);
                    i++;
                }
            }
        } else {
            throw new IllegalArgumentException("Many interests selected");
        }
    }

    public void buttonNextClick() {
        buttonNext.click();
    }
}
