package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.models.PostData;
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

    public PostData getLastPostData() {
        PostData postData = new PostData();
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath("//div[@id='page_wall_posts']/child::div[1]"), "Last post");

        ILabel labelAuthor = labelPost.findChildElement(By.xpath("//*[@class='author']"), ILabel.class);
        postData.setUserId(Integer.valueOf(labelAuthor.getAttribute("data-from-id")));

        ILabel labelText = getElementFactory()
                .getLabel(By.xpath("//div[contains(@class,'wall_post_text')]"), "Author last post");
        postData.setText(labelText.getText());
        return postData;
    }
}
