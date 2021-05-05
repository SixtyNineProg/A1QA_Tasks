package by.a1qa.klimov.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.utils.Randomizer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginForm extends Form {

    private final ITextBox textBoxPassword = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Choose Password']"), "Password field");

    private final ITextBox textBoxEmail = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Your email']"), "Email field");

    private final ITextBox textBoxDomain = getElementFactory()
            .getTextBox(By.cssSelector("[placeholder='Domain']"), "Domain field");

    private final ICheckBox checkBoxAcceptTerms = getElementFactory()
            .getCheckBox(By.cssSelector(".checkbox__box .icon-check"), "Check Box Accept Terms");

    private final IButton buttonDropMenuWithDomains = getElementFactory()
            .getButton(By.xpath("//span[contains(@class,'icon-chevron-down')]"), "Button dropdown opener");

    private final IButton buttonDropDownListLastItem = getElementFactory()
            .getButton(By.cssSelector(".dropdown__list :last-child"), "Last list item");

    private final IButton buttonNext = getElementFactory()
            .getButton(By.xpath("//a[@class='button--secondary' and text()='Next']"), "Button next");

    private final IButton buttonSendToBottom = getElementFactory()
            .getButton(By.cssSelector(".help-form__send-to-bottom-button"), "Button send to bottom");

    private final IButton buttonTransparent = getElementFactory()
            .getButton(By.cssSelector(".button--transparent"), "Transparent button");

    private final ILabel labelTimer = getElementFactory()
            .getLabel(By.cssSelector(".timer--center"), "Timer label");

    public LoginForm() {
        super(By.cssSelector(".login-form__container .login-form__fields"), "Login container");
    }

    public void fillPassword() {
        textBoxPassword.clearAndType(generatePassword());
    }

    private String generatePassword() {
        return
                //ASCII A(65)-Z(90)
                Randomizer.generateRandomText(
                        Randomizer.generateRandomNumFromRange(3, 5),
                        65, 90) +
                        //ASCII a(97)-z(122)
                        Randomizer.generateRandomText(
                                Randomizer.generateRandomNumFromRange(3, 5),
                                97, 122) +
                        //ASCII 0(48)-9(57)
                        Randomizer.generateRandomText(
                                Randomizer.generateRandomNumFromRange(3, 5),
                                48, 57) +
                        "@";
    }

    public void fillEmail() {
        textBoxEmail.clearAndType(
                Randomizer.generateRandomText(
                        Randomizer.generateRandomNumFromRange(5,15)
                )
        );
    }

    public void fillDomain() {
        textBoxDomain.clearAndType(
                Randomizer.generateRandomText(
                        Randomizer.generateRandomNumFromRange(2, 8)
                )
        );
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

    public boolean buttonSendToBottomIsPresent() {
        return buttonSendToBottom.state().isDisplayed();
    }

    public void buttonSendToBottomClick() {
        if (buttonSendToBottomIsPresent())
            buttonSendToBottom.click();
    }

    public void waitHideButtonSendToBottom() {
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.invisibilityOf(buttonSendToBottom.getElement()),
                "Send down button is hidden");
    }

    public void waitDisplayButtonTransparent() {
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(buttonTransparent.getElement()),
                "Send down button is hidden");
    }

    public boolean buttonTransparentIsPresent() {
        return buttonTransparent.state().isDisplayed();
    }

    public void buttonTransparentClick() {
        buttonTransparent.click();
    }

    public String labelTimerGetText() {
        return labelTimer.getText();
    }
}
