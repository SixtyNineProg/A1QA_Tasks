package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.properties.DataProperties;
import org.openqa.selenium.By;

public class LoginPage extends Form {

    private final ITextBox textBoxEmail = getElementFactory()
            .getTextBox(By.id("index_email"), "Email field");

    private final ITextBox textBoxPassword = getElementFactory()
            .getTextBox(By.id("index_pass"), "Password field");

    private final IButton buttonLogin = getElementFactory()
            .getButton(By.id("index_login_button"), "Login button");

    public LoginPage() {
        super(By.xpath("//div[@id='index_login']//form[@id='index_login_form']"), "Login container");
    }

    public void fillPassword() {
        textBoxPassword.clearAndType(DataProperties.getDataPropertyByKey("userPassword"));
    }

    public void fillEmail() {
        textBoxEmail.clearAndType(DataProperties.getDataPropertyByKey("userLogin"));
    }

    public void clickLoginButton() {
        buttonLogin.click();
    }
}
