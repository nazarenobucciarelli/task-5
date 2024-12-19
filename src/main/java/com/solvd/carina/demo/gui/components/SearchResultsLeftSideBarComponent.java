package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class SearchResultsLeftSideBarComponent extends AbstractUIObject {

    @FindBy(css = "ul.x-refine__left__nav li ul.x-refine__left__nav > li")
    private List<ExtendedWebElement> filterGroups;

    public SearchResultsLeftSideBarComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String selectRandomBrand() {
        List<ExtendedWebElement> checkBoxes = filterGroups.get(0).findExtendedWebElements(By.cssSelector("a"));
        int randomIndex = new Random().nextInt(checkBoxes.size());
        ExtendedWebElement checkBox = checkBoxes.get(randomIndex);

        while (true) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkBox);
            String value = checkBox.getAttribute("href");

            ExtendedWebElement inputElement = null;
            String brandName = null;
            try {
                inputElement = checkBox.findExtendedWebElement(By.cssSelector("input[aria-label]"));
                if (inputElement != null) {
                    brandName = inputElement.getAttribute("aria-label");
                }
            } catch (NoSuchElementException ex) {
                continue;
            }

            if (brandName == null) {
                continue;
            }

            if (!value.contains("Unbranded")) {
                checkBox.click();
                return brandName;
            }

            checkBoxes = filterGroups.get(0).findExtendedWebElements(By.cssSelector("a"));
            randomIndex = new Random().nextInt(checkBoxes.size());
            checkBox = checkBoxes.get(randomIndex);
        }
    }
}
