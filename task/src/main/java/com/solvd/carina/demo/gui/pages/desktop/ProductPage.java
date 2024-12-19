package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.DialogComponent;
import com.solvd.carina.demo.gui.components.SelectOptionModalComponent;
import com.solvd.carina.demo.gui.components.ShoppingCartOverlayComponent;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends PageBase {

    @FindBy(css = "div.x-atc-action")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//div[@data-testid='x-msku-evo']/div[not(@hidden)]")
    private List<ExtendedWebElement> selectOptionButtons;

    @FindBy(css = "h1.x-item-title__mainTitle span")
    private ExtendedWebElement productName;

    @FindBy(css = "span.listbox-button--expanded")
    private SelectOptionModalComponent selectOptionModalComponent;

    @FindBy(css = "div.confirm-dialog__window")
    private DialogComponent confirmationDialogComponent;

    @FindBy(css = "div[data-testid='ux-overlay'][aria-hidden='false']")
    private ShoppingCartOverlayComponent overlay;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomOptions() {
        for (ExtendedWebElement button : selectOptionButtons) {
            waitUntil(webDriver -> button.isVisible(), 5)
            SelectOptionModalComponent selectModal = clickOptionButton(button);
            selectModal.selectRandomOption();
            waitUntil(webDriver -> selectModal.isUIObjectPresent(), 5);
        }
    }

    public SelectOptionModalComponent clickOptionButton(ExtendedWebElement button) {
        button.click();
        return selectOptionModalComponent;
    }

    public ShoppingCartOverlayComponent clickAddToCartButton() {
        addToCartButton.click();
        return new ShoppingCartOverlayComponent(driver);
    }

    public boolean isAddToCartButtonPresent() {
        return addToCartButton.isPresent();
    }

    public String getProductName() {
        return productName.getText();
    }

    public DialogComponent getConfirmationDialog() {
        return confirmationDialogComponent;
    }

    public boolean isConfirmationDialogDisplayed() {
        waitUntil(webDriver -> confirmationDialogComponent.isUIObjectPresent(), 5);
        return confirmationDialogComponent.isUIObjectPresent();
    }
}