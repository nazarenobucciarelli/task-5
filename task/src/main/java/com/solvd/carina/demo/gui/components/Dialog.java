package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.ShoppingCartEbayPage;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Dialog extends AbstractUIObject implements ICustomTypePageFactory {

    @FindBy(css = ".confirm-dialog__confirm")
    private ExtendedWebElement confirmButton;

    protected Dialog(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ShoppingCartEbayPage clickConfirmButton() {
            confirmButton.click();
            return initPage(driver, ShoppingCartEbayPage.class);
    }
}