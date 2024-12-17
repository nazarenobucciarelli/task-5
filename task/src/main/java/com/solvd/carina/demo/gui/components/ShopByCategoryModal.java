package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.CategoryEbayPage;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ShopByCategoryModal extends AbstractUIObject implements ICustomTypePageFactory {

    @FindBy(css = ".scnd")
    private List<ExtendedWebElement> categories;

    protected ShopByCategoryModal(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CategoryEbayPage clickRandomCategory() {
        int randomIndex = new Random().nextInt(categories.size());
        categories.get(randomIndex).click();
        return initPage(driver, CategoryEbayPage.class);
    }
}