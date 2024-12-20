package com.solvd.carina.demo.gui.pages.common;

import com.solvd.carina.demo.gui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.FindBy;

public abstract class PageBase extends CaptchaPageBase {

    @FindBy(css = "header")
    public HeaderComponent headerComponent;

    public PageBase(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return headerComponent;
    }

}
