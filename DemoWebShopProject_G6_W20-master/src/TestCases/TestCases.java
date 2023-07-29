package TestCases;

import Utilities.BaseDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;


public class TestCases extends BaseDriver {
    /**
     * Test Case 1: Login Test - Positive
     * ❖ Login into website with valid credentials.
     * ❖ Assert that your login is successful.
     **/

    @Test
    public void LoginTestPositive() {
        login();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

//        WebElement logout = driver.findElement(By.xpath("//a[.='Log out']"));
//        String actualText = logout.getText();
//        String expectedText = "Log out";
//        Assert.assertEquals(expectedText, actualText);

        WebElement logoutLink = driver.findElement(By.className("ico-logout"));
        Assert.assertTrue(logoutLink.isDisplayed(), "Login was not successful.");
    }

    @Test
    public void LoginTestNegative() {
        /**Test Case 2: Login Test - Negative
         ❖ Login into website with invalid credentials.
         ❖ Assert that your login is unsuccessful.**/

        driver.get("https://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.xpath("//a[.='Log in']"));
        login.click();

        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys("bella@gmail.com");

        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("1234");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@class=\"button-1 login-button\"]"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement unsuccessfulLogin = driver.findElement(By.xpath("//span[.='Login was unsuccessful. " +
                                                                     "Please correct the errors and try again.']"));
        Assert.assertTrue(unsuccessfulLogin.isDisplayed());

    }

    @Test
    public void PlaceAnOrder() {
        /**Test Case 3: Place and order
         ❖ Go to the site
         ❖ Enter Email and Password and click login
         ❖ Select a product from the HomePage and add to chart
         ❖ Complete checkout
         ❖ Assert that your purchase is successf**/
        login();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 500);");

        WebElement addToCart = driver.findElement(By.xpath("//input[@onclick=\"AjaxCart.addproducttocart_catalog" +
                                                           "('/addproducttocart/catalog/31/1/1    ');return false;\"]"));
        addToCart.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement shoppingCard = driver.findElement(By.xpath("//span[.='Shopping cart']"));
        shoppingCard.click();

        WebElement agreeBTn = driver.findElement(By.id("termsofservice"));
        agreeBTn.click();

        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement billingAddress =driver.findElement(By.id("BillingNewAddress_CountryId"));
        Select select = new Select(billingAddress);
        select.selectByIndex(1);

        WebElement city = driver.findElement(By.id("BillingNewAddress_City"));
        city.sendKeys("Georgia");

        WebElement address1 = driver.findElement(By.id("BillingNewAddress_Address1"));
        address1.sendKeys("555 Main Street");

        WebElement zipCode = driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
        zipCode.sendKeys("30004");

        WebElement phoneNumber = driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
        phoneNumber.sendKeys("4441112233");
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement continueBtn = driver.findElement(By.cssSelector("input[class=\"button-1 new-address-next-step-button\"]"));
        continueBtn.click();
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement continueBtn2 = driver.findElement(By.xpath("//*[@id=\"shipping-buttons-container\"]/input"));
        continueBtn2.click();
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement continueBtn3 = driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/input"));
        continueBtn3.click();
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement continueBtn4 = driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/input"));
        continueBtn4.click();
        WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement continueBtn5 = driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/input"));
        continueBtn5.click();
       WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(2));

       WebElement paymentMethod1= driver.findElement(By.id("paymentmethod_2"));
       paymentMethod1.click();
        WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement continueBtn6 = driver.findElement(By.xpath("//input[@onclick=\"PaymentMethod.save()\"]"));
        continueBtn6.click();

        WebDriverWait wait9 = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement confirm = driver.findElement(By.xpath("//input[@class=\"button-1 confirm-order-next-step-button\"]"));
        confirm.click();

        WebDriverWait wait10 = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement successMessage = driver.findElement(By.xpath("//strong[.='Your order has been successfully processed!']"));

        Assert.assertTrue(successMessage.isDisplayed());
    }
}
