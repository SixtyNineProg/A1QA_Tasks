package by.a1qa.klimov.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserPage extends Form {
    public UserPage() {
        super(By.id("profile_edit_act"), "Edit profile button");
    }
}
