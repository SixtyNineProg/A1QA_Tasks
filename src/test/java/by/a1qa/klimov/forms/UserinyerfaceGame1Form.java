package by.a1qa.klimov.forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.elements.impl.SelectOptions;
import by.a1qa.klimov.elements.interfaces.ISelectOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ISelect;

public class UserinyerfaceGame1Form extends Form {

    private final ITextBox textFieldPassword = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Choose Password']"), "Password field");

    private final ITextBox textFieldEmail = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Your email']"), "Email field");

    private final ITextBox textFieldDomain = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Domain']"), "Domain field");

    private final ICheckBox checkBoxAcceptTerms = getElementFactory()
            .getCheckBox(By.cssSelector("#accept-terms-conditions"), "Check Box Accept Terms");

    private final IButton buttonDropdownOpener = getElementFactory()
            .getButton(By.xpath("//div[@class='dropdown__opener']"), "Button dropdown opener");

//    private final ISelectOptions select =
//            new SelectOptions(By.xpath("//div[@class='dropdown__opener']"), "Button dropdown opener", ElementState.DISPLAYED);

    private final IButton buttonDropDownListLastItem = getElementFactory()
            .getButton(By.xpath("//div[@class='login-form__container']//div[@class='align__cell'][4]"), "Last list item");
    // .getButton(By.cssSelector(".dropdown__list :last-child"), "Last list item");

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
        buttonDropDownListLastItem.click();
    }

    public void acceptTerms() {
        checkBoxAcceptTerms.uncheck();
    }

    public void clickNext() {
        buttonNext.click();
    }
}
