package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCart extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                clickAddToCartBtn(product.getName()).
                clickViewCart();

        System.out.println("Actual: " + cartPage.getProductName());
        System.out.println("Expected: " + product.getName());

        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
