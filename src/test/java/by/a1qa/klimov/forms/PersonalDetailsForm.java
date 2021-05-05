package by.a1qa.klimov.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsForm extends Form {
    public PersonalDetailsForm() {
        super(By.cssSelector(".personal-details__form"), "Personal details form.");
    }
}
