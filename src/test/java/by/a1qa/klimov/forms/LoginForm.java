package by.a1qa.klimov.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.klimov.utils.Generator;
import by.a1qa.klimov.utils.Randomizer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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

    private final IButton buttonLastItemInDomains = getElementFactory()
            .getButton(By.xpath("//div[@class='dropdown__list-item'][last()]"), "Button last item in domains");

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

    private final ILabel labelHelpFormTitle = getElementFactory()
            .getLabel(By.cssSelector(".help-form__title"), "Help form title label");

    public LoginForm() {
        super(By.cssSelector(".login-form__container .login-form__fields"), "Login container");
    }

    public void fillPassword(int numCapitalLetter,
                             int numLowerCaseLetter,
                             int numNumeral,
                             boolean atSymbol) {
        textBoxPassword.clearAndType(Generator.generatePassword(
                numCapitalLetter,
                numLowerCaseLetter,
                numNumeral,
                atSymbol));
    }

    public void fillEmail() {
        textBoxEmail.clearAndType(
                Randomizer.generateRandomText(
                        Randomizer.generateRandomNumFromRange(5, 15)
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
        getElementFactory()
                .getButton(By.xpath("//span[contains(@class,'icon-chevron-down')]"), "Button dropdown opener")
                .click();
        waitDisplayDomainList();
        List<ILabel> domainsList = getDomainsList();
        domainsList.get(Randomizer.generateRandomNumFromRange(0, domainsList.size() - 1)).click();
    }

    public void waitDisplayDomainList() {
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(buttonLastItemInDomains.getElement()),
                "Domain list is displayed.");
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

    public void waitHideLabelHelpFormTitle() {
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.invisibilityOf(labelHelpFormTitle.getElement()),
                "Help form title is hidden");
    }

    public void waitDisplayButtonTransparent() {
        AqualityServices.getConditionalWait().waitFor(
                ExpectedConditions.elementToBeClickable(buttonTransparent.getElement()),
                "Send down button is hidden");
    }

    public boolean labelHelpFormIsPresent() {
        return labelHelpFormTitle.state().isDisplayed();
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

    private List<ILabel> getDomainsList() {
        return getElementFactory().findElements(By.xpath("//div[@class='dropdown__list-item']"), ILabel.class);
    }
}
