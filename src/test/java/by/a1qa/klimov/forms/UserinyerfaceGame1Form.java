package by.a1qa.klimov.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserinyerfaceGame1Form extends Form {

    private final ITextBox textFieldPassword = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Choose Password']"), "Password field");

    private final ITextBox textFieldEmail = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Your email']"), "Email field");

    private final ITextBox textFieldDomain = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Domain']"), "Domain field");

    private final ICheckBox checkBoxAcceptTerms = getElementFactory()
            .getCheckBox(By.cssSelector(".checkbox__box .icon-check"), "Check Box Accept Terms");

    private final IButton buttonDropMenuWithDomains = getElementFactory()
            .getButton(By.xpath("//span[@class='icon icon-chevron-down']"), "Button dropdown opener");

    private final IButton buttonDropDownListLastItem = getElementFactory()
            .getButton(By.cssSelector(".dropdown__list :last-child"), "Last list item");

    private final IButton buttonNext = getElementFactory()
            .getButton(By.xpath("//a[@class='button--secondary' and text()='Next']"), "Button next");

    public UserinyerfaceGame1Form() {
        super(By.cssSelector(".login-form__container .login-form__fields"), "Login container");
    }

    public void writePassword(String password) {
        textFieldPassword.clearAndType(password);
    }

    public void writeEmail(String mail) {
        textFieldEmail.clearAndType(mail);
    }

    public void writeDomain(String domain) {
        textFieldDomain.clearAndType(domain);
    }

    public void chooseDomain() {
        buttonDropMenuWithDomains.click();
        buttonDropDownListLastItem.click();
    }

    public void acceptTerms() {
        checkBoxAcceptTerms.check();
    }

    public void clickNext() {
        buttonNext.click();
    }
}
