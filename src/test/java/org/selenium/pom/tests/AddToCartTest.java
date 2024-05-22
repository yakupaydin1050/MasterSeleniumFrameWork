package org.selenium.pom.tests;

import org.selenium.dataproviders.MyDataProvider;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.utils.JacksonUtils;
import org.testng.Assert;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().getProductThumbnail().
                clickAddToCartBtn(product.getName()).
                clickViewCart();

        System.out.println("Actual: " + cartPage.getProductName());
        System.out.println("Expected: " + product.getName());

        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test (dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeatureProducts(Product product) {
        CartPage cartPage = new HomePage(getDriver()).load().getProductThumbnail().
                clickAddToCartBtn(product.getName()).
                clickViewCart();

        System.out.println("product.getName() = " + product.getName());
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }



}
