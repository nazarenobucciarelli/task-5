package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.CartProduct;
import com.solvd.carina.demo.gui.pages.common.EbayPageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartEbayPage extends EbayPageBase {

    @FindBy(css = "div.cart-bucket")
    private List<CartProduct> cartProducts;

    public ShoppingCartEbayPage(WebDriver driver) {
        super(driver);
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public List<String> getProductTitles() {
        return cartProducts.stream().map(CartProduct::getTitle).collect(Collectors.toList());
    }
}