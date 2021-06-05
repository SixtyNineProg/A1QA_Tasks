package by.a1qa.klimov.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.models.CommentData;
import by.a1qa.klimov.models.PostData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UserPage extends Form {

    public UserPage() {
        super(By.id("profile_edit_act"), "Edit profile button");
    }

    public PostData getPostData(Integer postId, Integer ownerId) {
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post" + ownerId + "_" + postId + "']"),
                        "Post with id = " + postId);

        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is exist");

        PostData postData = new PostData();

        ILabel labelAuthor = labelPost.findChildElement(
                By.xpath("//*[@class='author']"),
                "Post author href with id: " + postId,
                ILabel.class);
        postData.setUserHref(labelAuthor.getAttribute("href"));

        ILabel labelText = labelPost.findChildElement(
                By.xpath("//div[contains(@class,'wall_post_text')]"),
                "Post text with id: " + postId,
                ILabel.class);
        postData.setText(labelText.getText());
        return postData;
    }

    public Boolean isExistPictureOnPost(Integer postId, Integer ownerId, String picturePath) {
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post" + ownerId + "_" + postId + "']"),
                        "Post with id = " + postId);

        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is exist");

        return labelPost.findChildElements(By.xpath("//*[@href='" + picturePath + "']"), ILabel.class).size() != 0;
    }

    public String getPicturePath(Integer postId, Integer ownerId, Integer pictureId) {
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post" + ownerId + "_" + postId + "']"),
                        "Post with id = " + postId);

        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is exist");

        return labelPost
                .findChildElement(By.xpath("//*[@data-photo-id='" + ownerId + "_" + pictureId + "']"), ILabel.class)
                .getAttribute("href");
    }

    public CommentData getCommentData(Integer ownerId, Integer postId, Integer commentId) {
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post" + ownerId + "_" + postId + "']"),
                        "Post with id: " + postId);
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is exist");

        ILabel labelShowAllReplies = labelPost.findChildElement(
                By.xpath("//*[contains(@onclick,'wall.showNextReplies')]"),
                "Show all replies label",
                ILabel.class);
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelShowAllReplies.getElement()));
        labelShowAllReplies.click();

        ILabel labelComment = labelPost.findChildElement(
                By.xpath("//div[@class='replies']//div[@id='post" + ownerId + "_" + commentId + "']"),
                "Comment with id: " + commentId,
                ILabel.class
        );
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelComment.getElement()),
                "Comment is exist");

        CommentData commentData = new CommentData();

        ILabel labelAuthor = labelComment.findChildElement(
                By.xpath("//*[@class='author']"),
                "Comment author href with id: " + commentId,
                ILabel.class);
        commentData.setUserHref(labelAuthor.getAttribute("href"));

        ILabel labelText = labelComment.findChildElement(
                By.xpath("//div[contains(@class,'wall_reply_text')]"),
                "Comment text with id: " + commentId,
                ILabel.class);
        commentData.setText(labelText.getText());
        return commentData;
    }

    public void likePost(Integer ownerId, Integer postId) {
        ILabel labelPost = getElementFactory()
                .getLabel(By.xpath(
                        "//div[@id='page_wall_posts']//div[@id='post" + ownerId + "_" + postId + "']"),
                        "Post with id: " + postId);
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(labelPost.getElement()),
                "Post is exist");

        ILabel labelLikes = labelPost.findChildElement(
                By.xpath("//a[contains(@onclick,'Likes.toggle')]"),
                "Post likes with id: " + postId,
                ILabel.class);
        labelLikes.click();
    }

    public Boolean isPostDeleted(Integer ownerId, Integer postId) {
        By byXpath = By.xpath("//div[@id='page_wall_posts']//div[@id='post" + ownerId + "_" + postId + "']");
        List<ILabel> posts = getElementFactory().findElements(byXpath, ILabel.class);
        if (posts.size() > 0) {
            AqualityServices.getConditionalWait().waitFor(
                    ExpectedConditions.invisibilityOf(
                            getElementFactory().getLabel(
                                    byXpath,
                                    "Post with id: " + postId)
                                    .getElement()));
        }
        return true;
    }
}
