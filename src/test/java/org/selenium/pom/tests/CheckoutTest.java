package org.selenium.pom.tests;

import org.selenium.api.actions.CartApi;
import org.selenium.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.utils.FakerUtils;
import org.selenium.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : yakup.aydin
 * @date : 05.05.2024
 * @project : MasterSeleniumFramework
 */
public class CheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesBrowser(cartApi.getCookies());

        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);

        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@gmail.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(500);
        injectCookiesBrowser(signUpApi.getCookies());
        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }
}
