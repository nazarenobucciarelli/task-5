package com.solvd.carina.demo.gui.pages.common;

import com.solvd.carina.demo.gui.components.Header;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.FindBy;


public abstract class PageBase extends AbstractPage {

    @FindBy(css = "header")
    public Header header;

    public PageBase(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }
}
