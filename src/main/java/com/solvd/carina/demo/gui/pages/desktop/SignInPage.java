package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.pages.common.CaptchaPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends CaptchaPageBase {

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

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void typeUserId(String id) {
        userId.type(id);
    }

    public void clickSignInContinueBtn() {
        signInContinueBtn.click();
        if (getCaptcha().isVisible()) {
            System.out.println("captcha is visible");
            waitUntil(webDriver -> !getCaptcha().isVisible(), 20);
        }
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