package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.CartProductComponent;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends PageBase {

    @FindBy(css = "div.cart-bucket")
    private List<CartProductComponent> cartProductComponents;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartProductComponent> getCartProducts() {
        waitUntil(webDriver -> !cartProductComponents.isEmpty(),5);
        return cartProductComponents;
    }

}