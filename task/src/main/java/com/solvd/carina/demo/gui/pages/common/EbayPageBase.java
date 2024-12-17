package com.solvd.carina.demo.gui.pages.common;

import com.solvd.carina.demo.gui.components.Header;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public abstract class EbayPageBase extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(css = "header")
    protected Header header;


    public EbayPageBase(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }
}
