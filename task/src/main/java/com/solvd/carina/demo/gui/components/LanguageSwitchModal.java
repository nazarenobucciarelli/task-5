package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.HomeEbayPage;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LanguageSwitchModal extends AbstractUIObject implements ICustomTypePageFactory {
    @FindBy(css = "ul li")
    private List<ExtendedWebElement> languageOption;


    protected LanguageSwitchModal(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public HomeEbayPage clickOnRandomLanguageOption() {
        ExtendedWebElement firstOption = languageOption.get(0);
        String lang = firstOption.getAttribute("lang");
        System.out.println(lang);
        firstOption.click();
        return initPage(driver, HomeEbayPage.class);
    }
}
