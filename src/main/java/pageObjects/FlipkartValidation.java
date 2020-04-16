package pageObjects;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import base.BaseClass;
import commons.WebElementLocators.FlipkartLogin;
import commons.WebElementLocators.FlipkartSearch;

public class FlipkartValidation extends BaseClass implements FlipkartLogin, FlipkartSearch{

	public void loginToApp() throws IOException, InterruptedException
	{
		//driver.findElement(login).click();
		driver.findElement(userName).click();
		driver.findElement(userName).sendKeys(readConfigProperty("username"));
		driver.findElement(password).sendKeys(readConfigProperty("password"));
		Thread.sleep(2000);
		driver.findElement(loginBtn).click();
		Reporter.log("Logged in Successfully");
	}
	
	public void searchProduct(String product) throws InterruptedException
	{
		Thread.sleep(5000);
		driver.findElement(searchBox).click();
		driver.findElement(searchBox).sendKeys(product);
		driver.findElement(searchBox).sendKeys(Keys.ENTER);	
		System.out.println("Search result..");
		Reporter.log("Search result came up");
	}
	
	
	public void purchaseRandomCamera() throws InterruptedException
	{	
		List<WebElement> name = driver.findElements(nameOfProducts);
		List<WebElement> price= driver.findElements(priceOfProducts);
		
		Random rand= new Random();
		int randomValue = rand.nextInt(name.size());
		name.get(randomValue).click();
		System.out.println("Clicked on random product");
		Reporter.log("Clicked on Random product");
		String randomName = name.get(randomValue).getText();
		System.out.println("Name of the Product : "+randomName);
		String randomPrice= price.get(randomValue).getText();
		System.out.println("Price of the Product : "+randomPrice);
		BaseClass.switchToCheckoutWindow();
		String nameInCOScreen = driver.findElement(nameInCheckoutScreen).getText();
		System.out.println("Name of product in checkout screen : "+nameInCOScreen);
		//checkpoint for name of product from search screen to checkpoint screen
		Assert.assertTrue(nameInCOScreen.startsWith(randomName), "Name of product is not same in checkout screen");
		Reporter.log("Name of the product matches between search screen and checkout screen!");
		String priceInCOScreen = driver.findElement(priceInCheckoutScreen).getText();
		System.out.println("Name of product in checkout screen : "+priceInCOScreen);
		//checkpoint for price of product from search screen to checkpoint screen
		Assert.assertEquals(randomPrice,priceInCOScreen,"Price is not same");
		Reporter.log("Price of the product matches between search screen and checkout screen!");

	}
	
	public void logoutFlipkart()
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(userTab)).perform();
		driver.findElement(logout).click();
		Reporter.log("Logged out from flipkart application");
	}
	
}
