package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Select extends AbstractUIObject {

    @FindBy(css = "option")
    private List<ExtendedWebElement> options;

    public Select(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<ExtendedWebElement> getOptions() {
        return options;
    }

    public void clickOption(Integer index) {
        options.get(index).click();
    }
}