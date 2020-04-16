package testScripts;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageObjects.FlipkartValidation;

public class DifferentBrowserTest extends BaseClass{

	FlipkartValidation flip= new FlipkartValidation();
	
	@Parameters("Browser")
	@Test
	public void loginAndPurchaseCameraValidation(String Browser) throws IOException, InterruptedException
	{
		BaseClass.webBrowser=Browser;
		System.out.println("Browser opened up");
		flip.loginToApp();
		System.out.println("Logged in successfully");
		flip.searchProduct("camera");
		Thread.sleep(3000);
		flip.purchaseRandomCamera();	
		BaseClass.captureScreenshot("CheckoutPage");
		flip.logoutFlipkart();
	}

}
