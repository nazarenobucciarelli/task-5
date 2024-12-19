package com.solvd.carina.demo.gui.pages.common;

import com.solvd.carina.demo.gui.components.HeaderComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public abstract class PageBase extends AbstractPage {

    @FindBy(css = "header")
    public HeaderComponent headerComponent;

    @FindBy(xpath = "//iframe[@data-hcaptcha-response]")
    public static ExtendedWebElement captcha;

    public PageBase(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return headerComponent;
    }

    public ExtendedWebElement getCaptcha() {
        waitUntil(webDriver -> captcha.isVisible(),5);
        return captcha;
    }
}
