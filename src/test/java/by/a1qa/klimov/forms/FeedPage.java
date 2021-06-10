package by.a1qa.klimov.forms;

import aquality.selenium.forms.Form;
import by.a1qa.klimov.forms.navigation.VkNavigationBar;
import org.openqa.selenium.By;

public class FeedPage extends Form {

    private VkNavigationBar vkNavigationBar = new VkNavigationBar();

    public FeedPage() {
        super(By.id("feed_rmenu"), "Feed menu");
    }

    public VkNavigationBar getVkNavigationBar() {
        return vkNavigationBar;
    }
}
