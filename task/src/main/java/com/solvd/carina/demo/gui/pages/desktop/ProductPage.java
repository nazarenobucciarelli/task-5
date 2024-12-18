package com.solvd.carina.demo.gui.pages.desktop;

import com.solvd.carina.demo.gui.components.Dialog;
import com.solvd.carina.demo.gui.components.SelectOptionModal;
import com.solvd.carina.demo.gui.pages.common.PageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends PageBase {

    @FindBy(css = "div.x-atc-action")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//div[@data-testid='x-msku-evo']/div[not(@hidden)]")
    private List<ExtendedWebElement> selectOptionButtons;

    @FindBy(css = "h1.x-item-title__mainTitle span")
    private ExtendedWebElement productName;

    @FindBy(css = "span.listbox-button--expanded")
    private SelectOptionModal selectOptionModal;

    @FindBy(css = "div.confirm-dialog__window")
    private Dialog confirmationDialog;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomOptions() {
        waitUntil(webDriver -> areSelectOptionButtonsPresent(),5);
        for (ExtendedWebElement button : selectOptionButtons) {
            SelectOptionModal selectModal = clickOptionButton(button);
            selectModal.selectRandomOption();
        }
    }

    private boolean areSelectOptionButtonsPresent() {
        return selectOptionButtons.stream().allMatch(ExtendedWebElement::isClickable);
    }

    public SelectOptionModal clickOptionButton(ExtendedWebElement button) {
        button.click();
        return selectOptionModal;
    }

    public ShoppingCartPage clickAddToCartButton() {
        addToCartButton.click();
        return new ShoppingCartPage(driver);
    }

    public boolean isAddToCartButtonPresent() {
        return addToCartButton.isPresent();
    }

    public String getProductName() {
        return productName.getText();
    }

    public boolean isConfirmationDialogPresent() {
        return confirmationDialog.isUIObjectPresent();
    }

    public Dialog getConfirmationDialog() {
        return confirmationDialog;
    }
}