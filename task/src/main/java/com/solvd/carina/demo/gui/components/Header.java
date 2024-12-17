package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.CategoryEbayPage;
import com.solvd.carina.demo.gui.pages.desktop.SearchResultsEbayPage;
import com.solvd.carina.demo.gui.pages.desktop.SignInEbayPage;
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

    public SearchResultsEbayPage clickSearchButton() {
        searchButton.click();
        return initPage(driver, SearchResultsEbayPage.class);
    }

    public CategoryEbayPage clickSearchButtonByCategory() {
        searchButton.click();
        return initPage(driver, CategoryEbayPage.class);
    }

    public ShopByCategoryModal clickShopByCategoryButton() {
        shopByCategoryButton.click();
        return modal;
    }

    public int getCartNumber() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(cartNumber.getText());
    }

    public SignInEbayPage clickSignInButton() {
        signInButton.click();
        try {
            Thread.sleep(20000); // To solve captcha
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return initPage(driver, SignInEbayPage.class);
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
