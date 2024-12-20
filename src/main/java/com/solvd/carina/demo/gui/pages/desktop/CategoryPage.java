package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.ProductCategoryComponent;
import com.solvd.carina.demo.gui.models.Product;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends PageBase {

    @FindBy(css = "nav[role='navigation']")
    private ExtendedWebElement nav;

    @FindBy(css = "li.brwrvr__item-card--list")
    private List<ProductCategoryComponent> items;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getProducts() {
        waitUntil(webDriver -> !items.isEmpty(),5);
        return items.stream()
                .map(productCategoryComponent -> new Product(
                        productCategoryComponent.getTitle(),
                        productCategoryComponent.getPrice()))
                .collect(Collectors.toList());
    }
}