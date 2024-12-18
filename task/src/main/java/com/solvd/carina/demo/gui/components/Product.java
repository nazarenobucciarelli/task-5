package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.ProductPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractUIObject {

    @FindBy(css = ".s-item .s-item__title span")
    private ExtendedWebElement title;

    @FindBy(css = ".s-item__price")
    private ExtendedWebElement price;

    public Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public ProductPage openProductEbay() {
        price.scrollTo();
        title.click();
        return new ProductPage(driver);
    }
}