package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.pages.common.PageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPage extends PageBase {

    @FindBy(css = "nav[role='navigation']")
    private ExtendedWebElement nav;

    @FindBy(css = "li.brwrvr__item-card--list")
    private List<ExtendedWebElement> items;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNavigationDisplayed() {
        return nav.isVisible();
    }

    public List<ExtendedWebElement> getItems() {
        return items;
    }
}