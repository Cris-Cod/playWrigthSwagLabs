package test;

import Utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestEnterLogin extends BaseClass{

    LoginPage loginPage;
    ProductsPage productsPage;
    Utilities utilities;
    ProductDetailPage detailPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;
    CompletePage completePage;

    @BeforeMethod
    public void setUp(@Optional("https://www.saucedemo.com/") String url, @Optional("chrome") String browserName, @Optional("false") String headless){
        launchPlaywright(browserName, headless);
        launchApplication(url);
    }
    
    @Test
    public void loginTest(@Optional("standard_user") String username, @Optional("secret_sauce") String password, Method method){
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        utilities = new Utilities(page);
        String testName = method.getName();

        utilities.takeScreenShot(page, testName, "login");
        loginPage.loginUser(username, password);
        utilities.takeScreenShot(page, testName, "titleProducts");
        boolean isloginSucces = productsPage.isTitleVisible();
        Assert.assertTrue(isloginSucces);
        utilities.takeScreenShot(page, testName, "btnBurger");
        productsPage.clicKMenuHarguger();
        utilities.takeScreenShot(page, testName, "btnlogout");
        productsPage.clickBtnLogout();
        utilities.takeScreenShot(page, testName , "login2");
        boolean isLoginOut = loginPage.titleLogin();
        Assert.assertTrue(isLoginOut);
        utilities.adjuntarCapturasDeCarpeta(testName);

    }

    @Test
    public void loginBlockedUser(@Optional("locked_out_user") String username, @Optional("secret_sauce") String password, Method method){
        loginPage = new LoginPage(page);
        utilities = new Utilities(page);
        String testName = method.getName();
        String textError = "Epic sadface: Sorry, this user has been locked out.";

        String message = loginPage.userError(username, password);
        Assert.assertEquals(message, textError);
        utilities.takeScreenShot(page, testName, "block_user");
        utilities.adjuntarCapturasDeCarpeta(testName);
    }

    @Test
    public void loginRequiredPassword(@Optional("standard_user") String username, @Optional("") String password, Method method){
        loginPage = new LoginPage(page);
        utilities = new Utilities(page);
        String testName = method.getName();
        String textError = "Epic sadface: Password is required";
        String message = loginPage.userError(username, password);
        Assert.assertEquals(message, textError);
        utilities.takeScreenShot(page, testName, "password_required");
        utilities.adjuntarCapturasDeCarpeta(testName);
    }

    @Test
    public void loginWrongPassword(@Optional("standard_user") String username, @Optional("x1r57") String password, Method method){
        loginPage = new LoginPage(page);
        utilities = new Utilities(page);
        String testName = method.getName();
        String textError = "Epic sadface: Username and password do not match any user in this service";
        String message = loginPage.userError(username, password);
        Assert.assertEquals(message, textError);
        utilities.takeScreenShot(page, testName, "password_required");
        utilities.adjuntarCapturasDeCarpeta(testName);
    }

    @Test
    public void selectProduct(@Optional("standard_user") String username, @Optional("secret_sauce") String password, Method method){
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        utilities = new Utilities(page);
        detailPage = new ProductDetailPage(page);
        String testName = method.getName();
        String nameP = "Sauce Labs Backpack";

        utilities.takeScreenShot(page, testName, "login");
        loginPage.loginUser(username, password);
        utilities.takeScreenShot(page, testName, "titleProducts");
        boolean isloginSucces = productsPage.isTitleVisible();
        Assert.assertTrue(isloginSucces);
        utilities.takeScreenShot(page,testName,"select product");
        productsPage.selectProduct(nameP);
        utilities.takeScreenShot(page,testName,"name product detail");
        String titleP = detailPage.nameProductDetail(nameP);
        Assert.assertEquals(titleP, nameP);
        utilities.adjuntarCapturasDeCarpeta(testName);
    }

    @Test
    public void addProductCart(@Optional("standard_user") String username, @Optional("secret_sauce") String password, Method method){
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        utilities = new Utilities(page);
        detailPage = new ProductDetailPage(page);
        cartPage = new CartPage(page);
        String testName = method.getName();
        String nameP = "Sauce Labs Fleece Jacket";
        String priceP = "49.99";

        utilities.takeScreenShot(page, testName, "login");
        loginPage.loginUser(username, password);
        utilities.takeScreenShot(page, testName, "titleProducts");
        boolean isloginSucces = productsPage.isTitleVisible();
        Assert.assertTrue(isloginSucces);
        utilities.takeScreenShot(page,testName,"select product");
        productsPage.selectProduct(nameP);
        utilities.takeScreenShot(page,testName,"name product detail");
        String titleP = detailPage.nameProductDetail(nameP);
        Assert.assertEquals(titleP, nameP);
        utilities.takeScreenShot(page,testName,"btn add to cart");
        detailPage.selectBtnAddToCart();
        utilities.takeScreenShot(page,testName,"btn cart");
        productsPage.clickBtnCart();
        utilities.takeScreenShot(page,testName,"name product detail");
        String textProduct = cartPage.nameOneProduct(nameP);
        Assert.assertEquals(textProduct, nameP);
        utilities.takeScreenShot(page,testName,"price product detail");
        String priceProsuct = cartPage.priceOneProduct(priceP);
        Assert.assertEquals(priceProsuct, priceP);
        utilities.adjuntarCapturasDeCarpeta(testName);
    }

    @Test
    public void addSeveralProducts(@Optional("standard_user") String username, @Optional("secret_sauce") String password, Method method){
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        utilities = new Utilities(page);
        detailPage = new ProductDetailPage(page);
        cartPage = new CartPage(page);
        String testName = method.getName();
        List<String> productList = Arrays.asList("Sauce Labs Onesie", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");

        utilities.takeScreenShot(page, testName, "login");
        loginPage.loginUser(username, password);
        utilities.takeScreenShot(page, testName, "titleProducts");
        boolean isloginSucces = productsPage.isTitleVisible();
        Assert.assertTrue(isloginSucces);
        utilities.takeScreenShot(page, testName, "SelectProducts");
        productsPage.selectSeveralProducts(productList);
        utilities.takeScreenShot(page,testName,"btn cart");
        productsPage.clickBtnCart();
        utilities.takeScreenShot(page,testName,"names products detail");
        List<String> namesProductsCart = cartPage.validateNameProducts(productList);
        Assert.assertEqualsNoOrder(namesProductsCart, productList);
        utilities.adjuntarCapturasDeCarpeta(testName);
    }
    @Test
    public void buyProducts(@Optional("standard_user") String username, @Optional("secret_sauce") String password, Method method){
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        utilities = new Utilities(page);
        detailPage = new ProductDetailPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
        overviewPage = new OverviewPage(page);
        completePage = new CompletePage(page);
        String testName = method.getName();
        List<String> productList = Arrays.asList("Sauce Labs Onesie", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");

        utilities.takeScreenShot(page, testName, "login");
        loginPage.loginUser(username, password);
        utilities.takeScreenShot(page, testName, "titleProducts");
        boolean isloginSucces = productsPage.isTitleVisible();
        Assert.assertTrue(isloginSucces);
        utilities.takeScreenShot(page, testName, "SelectProducts");
        productsPage.selectSeveralProducts(productList);
        utilities.takeScreenShot(page,testName,"btn cart");
        productsPage.clickBtnCart();
        utilities.takeScreenShot(page,testName,"names products detail");
        List<String> namesProductsCart = cartPage.validateNameProducts(productList);
        Assert.assertEqualsNoOrder(namesProductsCart, productList);
        utilities.takeScreenShot(page,testName,"click button checkout");
        cartPage.clickBtnCheckout();
        utilities.takeScreenShot(page,testName,"title checkout");
        String titleCheckout = checkoutPage.setTextTitle();
        Assert.assertEquals(titleCheckout, "Checkout: Your Information");
        utilities.takeScreenShot(page,testName,"form checkout");
        checkoutPage.setForm("Rust", "Uki","1235");
        utilities.takeScreenShot(page,testName,"title overview");
        String titleOverView = overviewPage.setTextTile();
        Assert.assertEquals(titleOverView, "Checkout: Overview");
        utilities.takeScreenShot(page,testName,"text  shipping");
        String textShipping = overviewPage.setShipping();
        Assert.assertEquals(textShipping, "Free Pony Express Delivery!");
        utilities.takeScreenShot(page,testName,"text  total");
        String textTotal = overviewPage.setTotal();
        Assert.assertEquals(textTotal, "Total: $36.69");
        utilities.takeScreenShot(page,testName,"button  finish");
        overviewPage.setBtnFinish();
        utilities.takeScreenShot(page,testName,"title complete");
        String titleComplete = completePage.setTitlePage();
        Assert.assertEquals(titleComplete, "Checkout: Complete!");
        utilities.takeScreenShot(page,testName,"text purchase complete");
        String textThankYou = completePage.setTextThankYou();
        Assert.assertEquals(textThankYou, "Thank you for your order!");
        completePage.setBtnBackHome();
        utilities.takeScreenShot(page, testName, "titleProducts back purchase");
        boolean isloginSuccesBack = productsPage.isTitleVisible();
        Assert.assertTrue(isloginSuccesBack);
        utilities.adjuntarCapturasDeCarpeta(testName);
    }



    @AfterMethod
    public void browseClose() throws InterruptedException {
        closePlaywright();
        Thread.sleep(1000);
    }
}
