package by.a1qa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LambdaTest {

    @Test
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "/All/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://accounts.lambdatest.com/register");

        String title = driver.getTitle();
        System.out.println(title);
        //Relative xpath for organization field
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Dmitry");
        //Relative xpath for full name field
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("dima.klimov.98@inbox.ru");
        driver.close();
        Assert.assertEquals("Sign up for free | Cross Browser Testing Tool | LambdaTest - LambdaTest", title);
    }
}
