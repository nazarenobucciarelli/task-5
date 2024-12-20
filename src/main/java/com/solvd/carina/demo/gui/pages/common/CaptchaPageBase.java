package com.solvd.carina.demo.gui.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class CaptchaPageBase extends AbstractPage {

    @FindBy(xpath = "//iframe[@data-hcaptcha-response]")
    public ExtendedWebElement captcha;

    public CaptchaPageBase(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getCaptcha() {
        return captcha;
    }
}
