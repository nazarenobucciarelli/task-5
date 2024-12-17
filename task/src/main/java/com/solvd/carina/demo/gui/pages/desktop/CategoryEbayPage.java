package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.pages.common.EbayPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryEbayPage extends EbayPageBase {

    @FindBy(css = "nav[role='navigation']")
    private ExtendedWebElement nav;

    @FindBy(css = "li.brwrvr__item-card--list")
    private List<ExtendedWebElement> items;

    public CategoryEbayPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNavigationDisplayed() {
        return nav.isVisible();
    }

    public List<ExtendedWebElement> getItems() {
        return items;
    }
}