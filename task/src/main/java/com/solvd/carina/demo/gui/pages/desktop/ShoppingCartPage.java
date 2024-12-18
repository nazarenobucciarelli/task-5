package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.CartProduct;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends PageBase {

    @FindBy(css = "div.cart-bucket")
    private List<CartProduct> cartProducts;

    @FindBy(css = "section[data-test-id='page-alerts']")
    private ExtendedWebElement pageAlert;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public List<String> getProductTitles() {
        return cartProducts.stream().map(CartProduct::getTitle).collect(Collectors.toList());
    }

    public boolean isPageAlertVisible() {
        waitUntil(webDriver -> pageAlert.isVisible(),5);
        return pageAlert.isPresent();
    }
}