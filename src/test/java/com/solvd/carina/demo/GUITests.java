package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.components.*;
import com.solvd.carina.demo.gui.models.Product;
import com.solvd.carina.demo.gui.pages.desktop.*;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class GUITests implements IAbstractTest {

    @Test(enabled = false)
    public void testSearchResults() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        HeaderComponent headerComponent = homePage.getHeader();
        headerComponent.typeSearchBox("football jerseys");
        ProductListPage searchResultsPage = headerComponent.clickSearchButton();
        List<Product> products = searchResultsPage.getProducts();
        products.forEach(product -> {
            Assert.assertTrue(!product.getTitle().isEmpty() &&
                    !product.getPrice().isEmpty(), "Not all products have a title and price");
        });
    }

    @Test(enabled = true)
    public void testShoppingCartAdd() {
        ShoppingCartPage shoppingCartPage = addProductToShoppingCart("t-shirts");
        Assert.assertEquals(shoppingCartPage.getCartProducts().size(), 1, "There should be one product");
    }
    /*

    @Test(enabled = true)
    public void testShoppingCartRemove() {
        List<String> productTitles = new ArrayList<>();
        AtomicReference<ShoppingCartPage> shoppingCartPage = new AtomicReference<>(addProductToShoppingCart(productTitles, "t-shirt"));
        AtomicReference<HeaderComponent> shoppingCartHeader = new AtomicReference<>(shoppingCartPage.get().getHeader());
        shoppingCartPage.get().getCartProducts().forEach(cartProduct -> {
            shoppingCartPage.set(cartProduct.clickRemoveButton());
            shoppingCartHeader.set(shoppingCartPage.get().getHeader());
        });
        if (shoppingCartPage.get().isPageAlertVisible()) {
            Assert.assertEquals(shoppingCartHeader.get().getCartNumber(), 0);
            shoppingCartPage.get().getProductTitles().forEach(productTitle -> {
                Assert.assertFalse(productTitles.contains(productTitle));
            });
        }
    }

    @Test(enabled = false)
    public void testWrongLoginAttempt() {
        HomePage homePage = new HomePage(getDriver());
        HeaderComponent headerComponent = homePage.getHeader();
        SignInPage signInPage = headerComponent.clickSignInButton();
        signInPage.typeUserId("invalidUserId");
        signInPage.clickSignInContinueBtn();
        signInPage.typePassword("invalidPassword");
        signInPage.clickSignInBtn();
        Assert.assertTrue(signInPage.isSignInErrorMsgDisplayed());
    }

    @Test(enabled = false)
    public void testSearchFilteringFunctionality() {
        HomeEbayPage homePage = new HomeEbayPage(getDriver());
        Header header = homePage.getHeader();
        header.typeSearchBox("guitars");
        SearchResultsEbayPage searchResultsEbayPage = header.clickSearchButton();
        SearchResultSideBar searchResultsSideBar = searchResultsEbayPage.getSideBar();
        String brandName = searchResultsSideBar.selectRandomBrand();
        AtomicInteger counter = new AtomicInteger();
        searchResultsEbayPage.getProducts().forEach(product -> {
            if (product.getTitle().toLowerCase().contains(brandName.toLowerCase())) {
                counter.getAndIncrement();
            }
        });
        Assert.assertTrue(counter.get() > 10);
    }

    @Test(enabled = false)
    public void testLanguageSwitchFunctionality() {
        HomeEbayPage homePage = new HomeEbayPage(getDriver());
        Header header = homePage.getHeader();
        List<String> categoriesBefore = homePage.getCategories().stream()
                .map(ExtendedWebElement::getText).collect(Collectors.toList());
        LanguageSwitchModal languageSwitchModal = header.clickLanguageMenuButton();
        HomeEbayPage homePageWithAnotherLanguage = languageSwitchModal.clickOnRandomLanguageOption();
        List<String> categoriesAfter = homePageWithAnotherLanguage.getCategories().stream()
                .map(ExtendedWebElement::getText).collect(Collectors.toList());
        categoriesAfter.forEach(element -> {
            Assert.assertFalse(categoriesBefore.contains(element));
        });
    }

    @Test(enabled = false)
    public void testEverythingElseCategoryShowResults() {
        HomeEbayPage homePage = new HomeEbayPage(getDriver());
        Header header = homePage.getHeader();
        Select select = header.clickAllCategoriesSelect();
        select.clickOption(select.getOptions().size() - 1);
        CategoryEbayPage categoryEbayPage = header.clickSearchButtonByCategory();
        Assert.assertFalse(categoryEbayPage.getItems().isEmpty());
    } */

    // Helper methods

    public ShoppingCartPage addProductToShoppingCart(String search) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        HeaderComponent headerComponent = homePage.getHeader();
        headerComponent.typeSearchBox(search);
        ProductListPage searchResultsPage = headerComponent.clickSearchButton();
        ProductPage productPage = searchResultsPage.clickOnRandomProduct();
        boolean isAddToCartButtonPresent = productPage.isAddToCartButtonPresent();
        while (!isAddToCartButtonPresent) {
            getDriver().close();

            Set<String> windowHandles = getDriver().getWindowHandles();
            String mainWindowHandle = windowHandles.iterator().next();
            getDriver().switchTo().window(mainWindowHandle);

            productPage = searchResultsPage.clickOnRandomProduct();
            isAddToCartButtonPresent = productPage.isAddToCartButtonPresent();
        }

        productPage.selectRandomOptions();

        ShoppingCartOverlayComponent overlay = productPage.clickAddToCartButton();

        if (productPage.isConfirmationDialogDisplayed()) {
            DialogComponent dialogComponent = productPage.getConfirmationDialog();
            dialogComponent.clickConfirmButton();
        }

        return overlay.clickOnSeeInBasketButton();
    }

    public SignInPage login(String username, String password) {
        HomePage homePage = new HomePage(getDriver());
        HeaderComponent headerComponent = homePage.getHeader();
        SignInPage signInPage = headerComponent.clickSignInButton();
        signInPage.typeUserId(username);
        signInPage.clickSignInContinueBtn();
        signInPage.typePassword(password);
        signInPage.clickSignInBtn();
        return signInPage;
    }
}
