package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.Product;
import com.solvd.carina.demo.gui.components.SearchResultSideBar;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SearchResultsPage extends PageBase {

    @FindBy(css = ".srp-results li.s-item")
    private List<Product> productElements;

    @FindBy(css = "div.srp-rail__left")
    private SearchResultSideBar sideBar;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getProducts() {
        return productElements;
    }

    public ProductPage clickOnRandomProduct() {
            int randomIndex = new Random().nextInt(productElements.size());
            Product product = productElements.get(randomIndex);
            ProductPage productPage = product.openProductEbay();

            Set<String> windowHandles = driver.getWindowHandles();
            String currentWindowHandle = driver.getWindowHandle();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(currentWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            return productPage;
    }

    public SearchResultSideBar getSideBar() {
        return sideBar;
    }
}