package com.solvd.carina.demo.gui.pages.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SignInEbayPage extends AbstractPage {

    @FindBy(id = "userid")
    private ExtendedWebElement userId;

    @FindBy(id = "pass")
    private ExtendedWebElement passwordInput;

    @FindBy(css = "button[data-testid='signin-continue-btn']")
    private ExtendedWebElement signInContinueBtn;

    @FindBy(id = "sgnBt")
    private ExtendedWebElement signInBtn;

    @FindBy(id = "signin-error-msg")
    private ExtendedWebElement signInErrorMsg;

    protected SignInEbayPage(WebDriver driver) {
        super(driver);
    }

    public void typeUserId(String id) {
        userId.type(id);
    }

    public void clickSignInContinueBtn() {
        signInContinueBtn.click();
    }

    public void typePassword(String password) {
        passwordInput.type(password);
    }

    public void clickSignInBtn() {
        signInBtn.click();
    }

    public boolean isSignInErrorMsgDisplayed() {
        return signInErrorMsg.isVisible();
    }
}