package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.components.*;
import com.solvd.carina.demo.gui.pages.desktop.*;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GUIEbayTests implements IAbstractTest {

    @Test(enabled = false)
    public void testSearchResults() {
        HomeEbayPage homePage = new HomeEbayPage(getDriver());
        Header header = homePage.getHeader();
        header.typeSearchBox("football jerseys");
        SearchResultsEbayPage searchResultsPage = header.clickSearchButton();
        List<Product> products = searchResultsPage.getProducts();
        products.forEach(product -> {
            Assert.assertTrue(!product.getTitle().isEmpty() && !product.getPrice().isEmpty());
        });
    }

    @Test(enabled = false)
    public void testShoppingCartAdd() {
        List<String> productTitles = new ArrayList<>();

        ShoppingCartEbayPage shoppingCartPage = addProductToShoppingCart(productTitles, "t-shirts");
        Header shoppingCartHeader = shoppingCartPage.getHeader();

        Assert.assertEquals(shoppingCartHeader.getCartNumber(), 1);
        Assert.assertEquals(shoppingCartPage.getProductTitles().size(), 1);
    }

    public ShoppingCartEbayPage addProductToShoppingCart(List<String> productTitles, String search) {
        HomeEbayPage homePage = new HomeEbayPage(getDriver());
        Header header = homePage.getHeader();
        header.typeSearchBox(search);
        SearchResultsEbayPage searchResultsPage = header.clickSearchButton();
        ProductEbayPage productPage = searchResultsPage.clickOnRandomProduct();
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

    @Test(enabled = false)
    public void testShoppingCartRemove() {
        List<String> productTitles = new ArrayList<>();

        ShoppingCartEbayPage shoppingCartPage = addProductToShoppingCart(productTitles, "t-shirt");
        Header shoppingCartHeader = shoppingCartPage.getHeader();
        shoppingCartPage.getCartProducts().forEach(CartProduct::clickRemoveButton);

        Assert.assertEquals(shoppingCartHeader.getCartNumber(), 0);
        shoppingCartPage.getProductTitles().forEach(productTitle -> {
            Assert.assertFalse(productTitles.contains(productTitle));
        });
    }

    @Test(enabled = false)
    public void testWrongLoginAttempt() {
        HomeEbayPage homePage = new HomeEbayPage(getDriver());
        Header header = homePage.getHeader();
        SignInEbayPage signInPage = header.clickSignInButton();
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

    @Test(enabled = true)
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
    }
}
