package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {

    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressLineOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    private final By clickHereToLoginLink = By.className("showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");

    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");

    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By productName = By.cssSelector("td[class='product-name']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement e = waitForElementVisible(firstNameFld);
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameFld).clear();
        driver.findElement(lastNameFld).sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) throws InterruptedException {
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage selectState(String stateName) {
        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        driver.findElement(addressLineOneFld).clear();
        driver.findElement(addressLineOneFld).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(billingPostCodeFld).clear();
        driver.findElement(billingPostCodeFld).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterMail(String email) {
        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) throws InterruptedException {
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                selectState(billingAddress.getState()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostalCode()).
                enterMail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice() {
        WebElement e = waitLong.until(ExpectedConditions.visibilityOfElementLocated(successNotice));
        return e.getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }

    public CheckoutPage enterUserName(String userName) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(usernameFld));
        driver.findElement(usernameFld).sendKeys(userName);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        driver.findElement(passwordFld).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        driver.findElement(loginBtn).click();
        return this;
    }

    public CheckoutPage login(User user) {
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn();
    }

    private CheckoutPage waitForLoginBtnToDisappear() {
        waitLong.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(String userName, String password) {
        return enterUserName(userName).
                enterPassword(password).
                clickLoginBtn().waitForLoginBtnToDisappear();
//        waitForLoginBtnToDisappear().clickLoginBtn();
    }


    public CheckoutPage selectDirectBankTransfer() {
        WebElement e = waitLong.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }

    public String getProductName() throws Exception {
//        waitLong.until(ExpectedConditions.stalenessOf());
        int i = 5;
        while (i > 0) {
            try {
                return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
            } catch (StaleElementReferenceException e) {
                System.out.println("NOT FOUND, TRYING AGAIN " + e );
            }
            Thread.sleep(5000);
            i--;
        }
        throw new Exception("Element not found");
    }

}
