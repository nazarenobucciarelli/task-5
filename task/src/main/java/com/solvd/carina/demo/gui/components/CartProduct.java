package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartProduct extends AbstractUIObject {

    @FindBy(css = "button[data-test-id='cart-remove-item']")
    private ExtendedWebElement removeButton;

    @FindBy(css = ".item-title a")
    private ExtendedWebElement title;

    public CartProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickRemoveButton() {
        removeButton.click();
    }

    public String getTitle() {
        return title.getText();
    }
}