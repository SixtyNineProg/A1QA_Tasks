package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.utils.Randomizer;
import org.openqa.selenium.By;

public class UserPage extends Form {

    private final ITextBox textBoxPost = getElementFactory()
            .getTextBox(By.id("post_field"), "Post field");

    private final IButton buttonSendPost = getElementFactory()
            .getButton(By.id("send_post"), "Button send post");

    public UserPage() {
        super(By.id("profile_edit_act"), "Edit profile button");
    }

    public void createPost(String text) {
        textBoxPost.click();
        textBoxPost.clearAndType(text);
        buttonSendPost.click();
    }
}
