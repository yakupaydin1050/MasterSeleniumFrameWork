package org.selenium.pom.tests;

import org.selenium.api.actions.CartApi;
import org.selenium.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : yakup.aydin
 * @date : 04.05.2024
 * @project : MasterSeleniumFramework
 */
public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(500);
        injectCookiesBrowser(cartApi.getCookies());
       checkoutPage.load();
        Thread.sleep(500);
        checkoutPage.
                clickHereToLoginLink().
                login(user);
        Thread.sleep(500);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));

    }
}
