package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.CategoryPage;
import com.solvd.carina.demo.gui.pages.desktop.ProductListPage;
import com.solvd.carina.demo.gui.pages.desktop.SignInPage;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractUIObject implements ICustomTypePageFactory {

    @FindBy(css = "#gh-ac")
    private ExtendedWebElement searchBox;

    @FindBy(css = ".gh-spr")
    private ExtendedWebElement searchButton;

    @FindBy(css = "#gh-shop-a")
    private ExtendedWebElement shopByCategoryButton;

    @FindBy(id = "gh-minicart-hover")
    private ExtendedWebElement cartButton;

    @FindBy(css = "#gh-sbc-o")
    private ShopByCategoryModalComponent modal;

    @FindBy(xpath = "//span[@id='gh-ug']/a")
    private ExtendedWebElement signInButton;

    @FindBy(id = "gh-cat")
    private ExtendedWebElement categoriesSelect;

    @FindBy(id = "gh-as-a")
    private ExtendedWebElement advancedButton;

    @FindBy(id = "gh-logo")
    private ExtendedWebElement logo;

    @FindBy(xpath = "//span[@id='gh-ug']//a[contains(text(), 'register')]")
    private ExtendedWebElement registerButton;

    @FindBy(id = "gh-eb-Alerts")
    private ExtendedWebElement notificationButton;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeSearchBox(String text) {
        searchBox.type(text);
    }

    public ProductListPage clickSearchButton() {
        searchButton.click();
        return new ProductListPage(driver);
    }

    public CategoryPage clickSearchButtonByCategory() {
        searchButton.click();
        return new CategoryPage(driver);
    }

    public ShopByCategoryModalComponent clickShopByCategoryButton() {
        shopByCategoryButton.click();
        return new ShopByCategoryModalComponent(driver);
    }

    public SignInPage clickSignInButton() {
        signInButton.click();
        return new SignInPage(driver);
    }

    public SelectComponent openCategoriesSelect() {
        categoriesSelect.click();
        return new SelectComponent(driver);
    }

    public boolean areAllHeaderElementsDisplayed() {
        return signInButton.isVisible() && shopByCategoryButton.isVisible() && categoriesSelect.isVisible() &&
                advancedButton.isVisible() && logo.isVisible() && registerButton.isVisible() &&
                notificationButton.isVisible() && searchButton.isVisible() && searchBox.isVisible() &&
                cartButton.isVisible();
    }

}
