package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.CategoryPage;
import com.solvd.carina.demo.gui.pages.desktop.SearchResultsPage;
import com.solvd.carina.demo.gui.pages.desktop.SignInPage;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject implements ICustomTypePageFactory {

    @FindBy(css = "#gh-ac")
    private ExtendedWebElement searchBox;

    @FindBy(css = ".gh-spr")
    private ExtendedWebElement searchButton;

    @FindBy(css = "#gh-shop-a")
    private ExtendedWebElement shopByCategoryButton;

    @FindBy(id = "gh-cart-n")
    private ExtendedWebElement cartNumber;

    @FindBy(css = "#gh-sbc-o")
    private ShopByCategoryModal modal;

    @FindBy(xpath = "//span[@id='gh-ug']/a")
    private ExtendedWebElement signInButton;

    @FindBy(id = "gh-eb-Geo-a-default")
    private ExtendedWebElement languageButton;

    @FindBy(id = "gh-eb-Geo-o")
    private LanguageSwitchModal languageSwitchModal;

    @FindBy(id = "gh-cat")
    private ExtendedWebElement allCategoriesButton;

    @FindBy(id = "gh-cat")
    private Select allCategoriesSelect;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeSearchBox(String text) {
        searchBox.type(text);
    }

    public SearchResultsPage clickSearchButton() {
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public CategoryPage clickSearchButtonByCategory() {
        searchButton.click();
        return new CategoryPage(driver);
    }

    public ShopByCategoryModal clickShopByCategoryButton() {
        shopByCategoryButton.click();
        return modal;
    }

    public int getCartNumber() {
        try{
            return Integer.parseInt(cartNumber.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public SignInPage clickSignInButton() {
        signInButton.click();
        try {
            Thread.sleep(20000); // To solve captcha
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new SignInPage(driver);
    }

    public LanguageSwitchModal clickLanguageMenuButton() {
        languageButton.click();
        return languageSwitchModal;

    }

    public Select clickAllCategoriesSelect() {
        allCategoriesButton.click();
        return allCategoriesSelect;
    }
}
