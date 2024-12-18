package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.components.*;
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
        Header header = homePage.getHeader();
        header.typeSearchBox("football jerseys");
        SearchResultsPage searchResultsPage = header.clickSearchButton();
        List<Product> products = searchResultsPage.getProducts();
        products.forEach(product -> {
            Assert.assertTrue(!product.getTitle().isEmpty() && !product.getPrice().isEmpty());
        });
    }

    @Test(enabled = false)
    public void testShoppingCartAdd() {
        List<String> productTitles = new ArrayList<>();

        ShoppingCartPage shoppingCartPage = addProductToShoppingCart(productTitles, "t-shirts");
        Header shoppingCartHeader = shoppingCartPage.getHeader();

        Assert.assertEquals(shoppingCartHeader.getCartNumber(), 1);
        Assert.assertEquals(shoppingCartPage.getProductTitles().size(), 1);
    }

    public ShoppingCartPage addProductToShoppingCart(List<String> productTitles, String search) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Header header = homePage.getHeader();
        header.typeSearchBox(search);
        SearchResultsPage searchResultsPage = header.clickSearchButton();
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
        productTitles.add(productPage.getProductName());

        productPage.selectRandomOptions();

        if (productPage.isConfirmationDialogPresent()) {
            Dialog dialog = productPage.getConfirmationDialog();
            dialog.clickConfirmButton();
        }

        return productPage.clickAddToCartButton();
    }

    @Test(enabled = true)
    public void testShoppingCartRemove() {
        List<String> productTitles = new ArrayList<>();
        AtomicReference<ShoppingCartPage> shoppingCartPage = new AtomicReference<>(addProductToShoppingCart(productTitles, "t-shirt"));
        AtomicReference<Header> shoppingCartHeader = new AtomicReference<>(shoppingCartPage.get().getHeader());
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
        Header header = homePage.getHeader();
        SignInPage signInPage = header.clickSignInButton();
        signInPage.typeUserId("invalidUserId");
        signInPage.clickSignInContinueBtn();
        signInPage.typePassword("invalidPassword");
        signInPage.clickSignInBtn();
        Assert.assertTrue(signInPage.isSignInErrorMsgDisplayed());
    }
    /*
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
}
