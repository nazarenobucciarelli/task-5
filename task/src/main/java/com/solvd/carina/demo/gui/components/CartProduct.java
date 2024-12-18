package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.ShoppingCartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartProduct extends AbstractUIObject {

    @FindBy(css = "button[data-test-id='cart-remove-item']")
    private ExtendedWebElement removeButton;

    @FindBy(css = ".item-title a")
    private ExtendedWebElement title;

    public CartProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ShoppingCartPage clickRemoveButton() {
        removeButton.click();
        waitUntil(webDriver -> !removeButton.isElementPresent(),5);
        return new ShoppingCartPage(driver);
    }

    public String getTitle() {
        return title.getText();
    }
}