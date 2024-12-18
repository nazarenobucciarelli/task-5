package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SearchResultSideBar extends AbstractUIObject {

    @FindBy(css = "ul.x-refine__left__nav li ul.x-refine__left__nav > li")
    private List<ExtendedWebElement> filterGroups;

    public SearchResultSideBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String selectRandomBrand() {
        List<ExtendedWebElement> checkBoxes = filterGroups.get(0).findExtendedWebElements(By.cssSelector("a"));
        int randomIndex = new Random().nextInt(checkBoxes.size());
        ExtendedWebElement checkBox = checkBoxes.get(randomIndex);
        String value = checkBox.getAttribute("href");
        String brandName = checkBox.findExtendedWebElement(By.cssSelector("input[aria-label]")).getAttribute("aria-label");
        while (value.contains("Unbranded")) {
            checkBox = checkBoxes.get(randomIndex);
            brandName = checkBox.findExtendedWebElement(By.cssSelector("input[aria-label]")).getAttribute("aria-label");
            checkBox.click();
            value = checkBox.getAttribute("href");
        }
        checkBox.click();
        return brandName;
    }
}
