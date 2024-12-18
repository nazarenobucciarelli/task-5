package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.pages.common.PageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends PageBase {

    @FindBy(css = ".vl-flyout-nav__container li a")
    private List<ExtendedWebElement> categories;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<ExtendedWebElement> getCategories() {
        return categories.stream().filter(category -> category.isVisible()
                && !category.getText().equals("Explore (New!)")).collect(Collectors.toList());
    }
}