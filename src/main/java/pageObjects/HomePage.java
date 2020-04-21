package pageObjects;

import base.BaseClass;
import com.relevantcodes.extentreports.LogStatus;
import commons.HomePageXpath;
import commons.LoginPageXpath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.Random;

public class HomePage implements HomePageXpath, LoginPageXpath {
    WebDriver driver = null;
    BaseClass baseClass = null;
    String randomPrice = null;
    String randomName = null;

    public HomePage(BaseClass baseClass) {
        this.driver = baseClass.driver;
        this.baseClass = baseClass;
    }

    public void searchProduct(String product) throws Throwable {
        try {
            baseClass.waitForInvisibelityOfElement(loginBtn);
            baseClass.getVisibleElement(searchBox).sendKeys(product);
            baseClass.getVisibleElement(searchBtn).click();
            baseClass.test.log(LogStatus.PASS, "Searched product " + product + " " + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
        } catch (Exception e) {
            baseClass.test.log(LogStatus.FAIL, "Failed to search product " + product + " " + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
            throw e;
        }
    }

    public void purchaseRandomCamera() throws InterruptedException {
        try {
            List<WebElement> name = driver.findElements(nameOfProducts);
            List<WebElement> price = driver.findElements(priceOfProducts);
            Random rand = new Random();
            int randomValue = rand.nextInt(name.size());
            name.get(randomValue).click();
            System.out.println("Clicked on random product");
            Reporter.log("Clicked on Random product");
            randomName = name.get(randomValue).getText();
            System.out.println("Name of the Product : " + randomName);
            randomPrice = price.get(randomValue).getText();
            System.out.println("Price of the Product : " + randomPrice);
            baseClass.test.log(LogStatus.PASS, "Selected random product" + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
        } catch (Exception e) {
            baseClass.test.log(LogStatus.FAIL, "Failed to select random product" + " " + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
            throw e;
        }
    }

    public void verifyTheProductDetails() {
        try {
            baseClass.switchToCheckoutWindow();
            String nameInCOScreen = driver.findElement(nameInCheckoutScreen).getText();
            System.out.println("Name of product in checkout screen : " + nameInCOScreen);
            //checkpoint for name of product from search screen to checkpoint screen
            Assert.assertTrue(nameInCOScreen.startsWith(randomName), "Name of product is not same in checkout screen");
            Reporter.log("Name of the product matches between search screen and checkout screen!");
            String priceInCOScreen = driver.findElement(priceInCheckoutScreen).getText();
            System.out.println("Name of product in checkout screen : " + priceInCOScreen);
            //checkpoint for price of product from search screen to checkpoint screen
            Assert.assertEquals(randomPrice, priceInCOScreen, "Price is not same");
            Reporter.log("Price of the product matches between search screen and checkout screen!");
            baseClass.test.log(LogStatus.PASS, "Verified product details in product screen" + " " + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
        } catch (Exception e) {
            baseClass.test.log(LogStatus.FAIL, "Failed to verify product details in product screen  " + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
            throw e;
        }
    }

}
