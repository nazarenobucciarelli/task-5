package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.Product;
import com.solvd.carina.demo.gui.components.SearchResultSideBar;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SearchResultsEbayPage extends AbstractPage {

    @FindBy(css = ".srp-results .s-item")
    private List<Product> productElements;

    @FindBy(css = "div.srp-rail__left")
    private SearchResultSideBar sideBar;

    protected SearchResultsEbayPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getProducts() {
        return productElements;
    }

    public ProductEbayPage clickOnRandomProduct() {
            int randomIndex = new Random().nextInt(productElements.size());
            productElements.get(randomIndex).findExtendedWebElement(By.cssSelector(".s-item__title")).click();

            Set<String> windowHandles = driver.getWindowHandles();
            String currentWindowHandle = driver.getWindowHandle();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(currentWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            return new ProductEbayPage(driver);
    }

    public SearchResultSideBar getSideBar() {
        return sideBar;
    }
}