package testScripts;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;

public class TC001_VerifyFlipkartLoginAndSearchScreen extends BaseClass{
	@Test(description = "Verify login and product search in flipkart")
	public void loginAndPurchaseCameraValidation() throws Throwable {
		iTestResult= Reporter.getCurrentTestResult();
		loginPage.loginToApp();
		System.out.println("Logged in successfully");
		homePage.searchProduct("camera");
		homePage.purchaseRandomCamera();
		homePage.verifyTheProductDetails();
		loginPage.logoutFlipkart();
	}

}
