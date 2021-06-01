package by.a1qa.klimov.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.models.PostData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UserPage extends Form {

    public UserPage() {
        super(By.id("profile_edit_act"), "Edit profile button");
    }

    public PostData getPostData(Integer postId, Integer ownerId) {
        String xpath = "//div[@id='page_wall_posts']//div[@id='post"+ ownerId + "_" + postId + "']";

        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post"+ ownerId + "_" + postId + "']"),
                        "Post with id = " + postId);

        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is created");

        PostData postData = new PostData();

        ILabel labelAuthor = labelPost.findChildElement(By.xpath("//*[@class='author']"), ILabel.class);
        postData.setUserHref(labelAuthor.getAttribute("href"));

        ILabel labelText = getElementFactory()
                .getLabel(By.xpath("//div[contains(@class,'wall_post_text')]"), "Author last post");
        postData.setText(labelText.getText());
        return postData;
    }

    public Boolean isExistPictureOnPost(Integer postId, Integer ownerId, String picturePath) {
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post"+ ownerId + "_" + postId + "']"),
                        "Post with id = " + postId);

        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is created");

        return labelPost.findChildElements(By.xpath("//*[@href='"+ picturePath + "']"), ILabel.class).size() != 0;
    }
}
