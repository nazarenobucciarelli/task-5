package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractUIObject {

    @FindBy(css = ".s-item__title span")
    private ExtendedWebElement title;

    @FindBy(css = ".s-item__price")
    private ExtendedWebElement price;

    protected Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public String getPrice() {
        return this.price.getText();
    }
}