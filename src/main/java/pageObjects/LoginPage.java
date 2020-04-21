package pageObjects;

import base.BaseClass;
import base.ReadProperty;
import com.relevantcodes.extentreports.LogStatus;
import commons.LoginPageXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.io.IOException;

public class LoginPage implements LoginPageXpath {
    WebDriver driver = null;
    BaseClass baseClass = null;

    public LoginPage(BaseClass baseClass) {
        this.driver = baseClass.driver;
        this.baseClass = baseClass;
    }

    public void loginToApp() throws IOException {
        try{
        driver.findElement(userName).click();
        driver.findElement(userName).sendKeys(ReadProperty.readConfigProperty("username"));
        driver.findElement(password).sendKeys(ReadProperty.readConfigProperty("password"));
        driver.findElement(loginBtn).click();
        baseClass.test.log(LogStatus.PASS, "Login to application" + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
    } catch (Exception e) {
        baseClass.test.log(LogStatus.FAIL, "Failed login to application"  + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
        throw e;
    }
        Reporter.log("Logged in Successfully");
    }

    public void logoutFlipkart() {
       try{
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(String.format(userTab, "Gulnaaz")))).perform();
        driver.findElement(logout).click();
           baseClass.test.log(LogStatus.PASS, "Logged out from application"  + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
        Reporter.log("Logged out from flipkart application");
    } catch (Exception e) {
        baseClass.test.log(LogStatus.FAIL, "Failed logout from application"  + baseClass.test.addBase64ScreenShot(baseClass.getBase64Image()));
        throw e;
    }
    }
}
