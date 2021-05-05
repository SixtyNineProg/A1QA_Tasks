package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.utils.Randomizer;
import org.openqa.selenium.By;

import java.util.List;

public class AvatarAndInterestsPage extends Form {
    private final int NUM_INTERESTS = 3;

    private final IButton buttonUploadAvatar = getElementFactory()
            .getButton(By.cssSelector(".avatar-and-interests__upload-button"), "Button upload avatar");

    private final ILabel labelUnselectAll = getElementFactory()
            .getLabel(By.xpath("//label[@for='interest_unselectall']"), "CheckBox unselect all");

    private final IButton buttonNext = getElementFactory()
            .getButton(By.xpath("//button[contains(text(),'Next')]"), "Button upload avatar");

    public AvatarAndInterestsPage() {
        super(By.cssSelector(".avatar-and-interests-page"), "This is me container");
    }

    public void buttonUploadAvatarClick() {
        buttonUploadAvatar.click();
    }

    public void chooseInterests() {
        labelUnselectAll.click();
        List<ILabel> interests = getInterestsList();
        if (interests.size() >= NUM_INTERESTS + 2) {
            for (int i = 0; i < NUM_INTERESTS;) {
                int randomNum = Randomizer.generateRandomNumFromRange(0, interests.size() - 1);
                ILabel interest = interests.get(randomNum);
                String attribute = interest.getAttribute("htmlFor");
                if (!attribute.equals("interest_unselectall") && !attribute.equals("interest_selectall")) {
                    interests.get(randomNum).click();
                    interests.remove(randomNum);
                    i++;
                }
            }
        } else {
            throw new IllegalArgumentException("There are not enough interests on the page");
        }
    }

    public void buttonNextClick() {
        buttonNext.click();
    }

    private List<ILabel> getInterestsList() {
        return getElementFactory().findElements(By.cssSelector("label[for*='interest']"), ILabel.class);
    }
}
