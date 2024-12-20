package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.ProductListComponent;
import com.solvd.carina.demo.gui.components.ProductListLeftSideBarComponent;
import com.solvd.carina.demo.gui.models.Product;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductListPage extends PageBase {

    @FindBy(css = ".srp-results li.s-item")
    private List<ProductListComponent> productListComponentElements;

    @FindBy(css = "div.srp-rail__left")
    private ProductListLeftSideBarComponent leftSideBar;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getProducts() {
        return productListComponentElements.stream()
                .map(productListComponent -> new Product(
                        productListComponent.getTitle(),
                        productListComponent.getPrice()))
                .collect(Collectors.toList());
    }

    public ProductPage clickOnRandomProduct() {
        int randomIndex = new Random().nextInt(productListComponentElements.size());
        ProductListComponent productListComponent = productListComponentElements.get(randomIndex);
        ProductPage productPage = productListComponent.openProductEbay();

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

    public ProductListLeftSideBarComponent getLeftSideBar() {
        return leftSideBar;
    }
}