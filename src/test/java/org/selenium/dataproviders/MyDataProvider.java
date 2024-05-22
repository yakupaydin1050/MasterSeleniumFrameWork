package org.selenium.dataproviders;

import org.selenium.pom.objects.Product;
import org.selenium.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

/**
 * @author : yakup.aydin
 * @date : 05.05.2024
 * @project : MasterSeleniumFramework
 */
public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson("products.json", Product[].class);
    }
}
