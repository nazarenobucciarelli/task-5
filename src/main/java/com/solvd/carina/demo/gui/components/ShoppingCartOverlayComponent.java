package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.pages.desktop.ShoppingCartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class ShoppingCartOverlayComponent extends AbstractUIObject {

    @FindBy(css = "a[data-testid=\"ux-call-to-action\"]")
    private List<ExtendedWebElement> actionButtons;

    public ShoppingCartOverlayComponent(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage clickOnSeeInBasketButton() {
            Optional<ExtendedWebElement> seeInBasket = actionButtons.stream()
                    .filter(webElement -> webElement.getText().equals("See in basket"))
                    .findFirst();
            if (seeInBasket.isPresent()) {
                seeInBasket.get().click();
            return new ShoppingCartPage(driver);
            }
            return null;
    }
}
